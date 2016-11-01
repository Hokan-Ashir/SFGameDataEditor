package sfgamedataeditor.events.processing;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.events.types.ViewRenderedEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.RenderableView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ShowViewEventProcessingStrategy implements EventProcessingStrategy<ShowViewEvent> {

    private static final Logger LOGGER = Logger.getLogger(ShowViewEventProcessingStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ShowViewEvent event) {
        RenderableView view;
        Class<? extends RenderableView> classViewToShow = event.getClassViewToShow();
        Map<Class<? extends RenderableView>, ViewControllerPair> views = ViewRegister.INSTANCE.getViews();
        if (!views.containsKey(classViewToShow)) {
            try {
                view = (RenderableView) classViewToShow.getDeclaredConstructors()[0].newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                return;
            }

            AbstractController controller = null;
            Class<? extends AbstractController> controllerClass = view.getControllerClass();
            if (controllerClass != null) {
                try {
                    controller = (AbstractController) controllerClass.getDeclaredConstructors()[0].newInstance(view);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage(), e);
                    return;
                }

                controller.setModel(null);
            }

            ViewControllerPair pair = new ViewControllerPair(view, controller);
            views.put(classViewToShow, pair);
        } else {
            view = views.get(classViewToShow).getView();
        }

        AbstractController controller = views.get(classViewToShow).getController();
        if (controller != null) {
            controller.setModel(event.getModel());
            controller.updateView();
        }
        view.render();
        EventProcessor.INSTANCE.process(new ViewRenderedEvent(classViewToShow));
    }
}
