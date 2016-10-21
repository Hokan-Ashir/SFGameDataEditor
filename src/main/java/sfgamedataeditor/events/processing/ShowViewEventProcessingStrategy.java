package sfgamedataeditor.events.processing;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ShowViewEventProcessingStrategy implements EventProcessingStrategy<ShowViewEvent> {

    private static final Logger LOGGER = Logger.getLogger(ShowViewEventProcessingStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ShowViewEvent event) {
        AbstractView view;
        ClassTuple tuple = event.getTuple();
        AbstractView parentViewInstance = getParentViewInstance(tuple);
        Map<ClassTuple<?, ?>, AbstractView> views = ViewRegister.INSTANCE.getViews();
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
                return;
            }

            views.put(tuple, view);
        } else {
            view = views.get(tuple);
            parentViewInstance.addChild(view);
        }

        runPostProcessingMethods(view);

        AbstractView parentView = view.getParentView();
        if (parentView != null) {
            // TODO simplify this call, what if all that Applicaton
            // needs is repaint one single inner-inner view, no need
            // to repaint all other views as well
            // TODO also maybe replace with EventHandlerRegister.INSTANCE.fireEvent(view.getEventToShowThisView())
            // for all inner views
            showAllViewHierarchy(parentView);
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
        Map<ClassTuple<?, ?>, AbstractView> views = ViewRegister.INSTANCE.getViews();
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
}
