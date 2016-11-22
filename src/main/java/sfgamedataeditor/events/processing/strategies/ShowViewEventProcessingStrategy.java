package sfgamedataeditor.events.processing.strategies;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.ViewCreator;
import sfgamedataeditor.events.processing.ViewControllerPair;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ShowViewEventProcessingStrategy implements EventProcessingStrategy<ShowViewEvent> {

    private static final Logger LOGGER = Logger.getLogger(ShowViewEventProcessingStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ShowViewEvent event) {
        ControllableView view;
        Class<? extends ControllableView> classViewToShow = event.getViewClass();
        Map<Class<? extends ControllableView>, ViewControllerPair> views = ViewRegister.INSTANCE.getViews();
        if (!views.containsKey(classViewToShow)) {
            view = (ControllableView) ViewCreator.INSTANCE.createView(classViewToShow, event.getModel());

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
        }

        AbstractController controller = views.get(classViewToShow).getController();
        if (controller != null) {
            controller.setModel(event.getModel());
            controller.updateView();
            controller.renderView();
        }
    }
}
