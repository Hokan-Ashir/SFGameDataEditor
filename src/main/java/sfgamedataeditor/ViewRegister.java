package sfgamedataeditor;

import org.apache.log4j.Logger;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.MainView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ViewRegister.class);

    private Map<ClassTuple, AbstractView> views = new HashMap<ClassTuple, AbstractView>() {{
        put(new ClassTuple<>(NullView.class, AbstractView.class), new NullView(null));
    }};

    public void process(AbstractMetaEvent event) {
        for (ShowViewEvent showViewEvent : event.getEventList()) {
            process(showViewEvent);
        }
    }

    public void process(ShowViewEvent event) {
        AbstractView view = null;
        ClassTuple tuple = event.getTuple();
        AbstractView parentViewInstance = getParentViewInstance(tuple);
        if (!views.containsKey(tuple)) {
            try {
                // other way to do so, using more strict compilation checks is:
                // view = (AbstractView) tuple.getViewClass().getConstructor(tuple.getParentViewInstance().getClass()).newInstance(tuple.getParentViewInstance());
                // but, cause we can create templated objects with signature like:
                // class SomeClass<T extends AbstractView> extends AbstractView<T>
                // or
                // class SomeClass<T extends ModulesView> extends AbstractView<T>
                // to generify reflection calls, we simply use first declared constructor
                view = (AbstractView) tuple.getViewClass().getDeclaredConstructors()[0].newInstance(parentViewInstance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
            }

            views.put(tuple, view);
        } else {
            view = views.get(tuple);
            parentViewInstance.addChild(view);
        }

        runPostProcessingMethods(view);

        AbstractView superParent = view.getParentView();
        if (superParent != null) {
            // TODO simplify this call, what if all that Applicaton
            // needs is repaint one single inner-inner view, no need
            // to repaint all other views as well
            // TODO also maybe replace with EventHandlerRegister.INSTANCE.fireEvent(view.getEventToShowThisView())
            // for all inner views
            showAllViewHierarchy(superParent);
        }
        view.updateData(event.getObjectParameter());
    }

    private void runPostProcessingMethods(AbstractView view) {
        for (Method method : view.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostProcess.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(view);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    private AbstractView getParentViewInstance(ClassTuple tuple) {
        for (AbstractView abstractView : views.values()) {
            if (abstractView.getClass().equals(tuple.getParentViewClass())) {
                return abstractView;
            }
        }
        throw new RuntimeException("No parent view with class name " + tuple.getParentViewClass().getName() + " exists");
    }

    private <T extends AbstractView> void showAllViewHierarchy(AbstractView<T> parent) {
        parent.clearAllComponents();
        for (AbstractView child : parent.getChildren()) {
            parent.addChildView(child);
            showAllViewHierarchy(child);
        }
        parent.repaint();
    }

    // TODO find more appropriate method-parameter signature
    public AbstractView getView(ClassTuple tuple) {
        if (views.containsKey(tuple)) {
            return views.get(tuple);
        }

        return null;
    }

    public void updateAllCurrentViews() {
        OffsetProvider.INSTANCE.recreateAllMaps();
        // TODO make this use-case work:
        // user selected Fire/Fireball-1 and change its spell requirements to
        // Elemental magic/Ice-1, made sfmod-file, then load it,
        // cause all maps in Mappings class stays the same
        // Fireball-1's requrements still considered as Elemental magic/Fire-1
        MainView view = (MainView) getView(new ClassTuple<>(MainView.class, NullView.class));
        updateDataRecursively(view);
    }

    private <T extends AbstractView> void updateDataRecursively(AbstractView<T> parent) {
        parent.updateData(null);
        for (AbstractView<T> tAbstractView : parent.getChildren()) {
            updateDataRecursively(tAbstractView);
        }
    }
}
