package sfgamedataeditor;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ViewRegister.class);

    private Map<ClassTuple, AbstractView> views = new HashMap<>();

    public void process(ShowViewEvent event) {
        AbstractView view = null;
        ClassTuple tuple = event.getTuple();
        if (!views.containsKey(tuple)) {
            try {
                // other way to do so, using more strict compilation checks is:
                // view = (AbstractView) tuple.getViewClass().getConstructor(tuple.getParentViewInstance().getClass()).newInstance(tuple.getParentViewInstance());
                // but, cause we can create templated objects with signature like:
                // class SomeClass<T extends AbstractView> extends AbstractView<T>
                // or
                // class SomeClass<T extends ModulesView> extends AbstractView<T>
                // to generify reflection calls, we simply use first declared constructor
                view = (AbstractView) tuple.getViewClass().getDeclaredConstructors()[0].newInstance(tuple.getParentViewInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
            }

            views.put(tuple, view);
        } else {
            view = views.get(tuple);
            tuple.getParentViewInstance().addChild(view);
        }

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
}
