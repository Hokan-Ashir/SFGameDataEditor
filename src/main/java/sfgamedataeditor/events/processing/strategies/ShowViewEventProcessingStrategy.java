package sfgamedataeditor.events.processing.strategies;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.ViewConfigurator;
import sfgamedataeditor.events.processing.ViewControllerPair;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ShowViewEventProcessingStrategy implements EventProcessingStrategy<ShowViewEvent> {

    private static final Logger LOGGER = Logger.getLogger(ShowViewEventProcessingStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ShowViewEvent event) {
        Class<? extends ControllableView> classViewToShow = event.getViewClass();
        Map<Class<? extends ControllableView>, ViewControllerPair> views = ViewRegister.INSTANCE.getViews();
        if (!views.containsKey(classViewToShow)) {
            if (!createViewAndController(classViewToShow, views)) {
                return;
            }
        }

        updateViewModelAndConfiguration(event, classViewToShow, views);
    }

    private void updateViewModelAndConfiguration(ShowViewEvent event, Class<? extends ControllableView> classViewToShow,
                                                 Map<Class<? extends ControllableView>, ViewControllerPair> views) {
        Model model = event.getModel();
        ViewControllerPair viewControllerPair = views.get(classViewToShow);
        ViewConfigurator.INSTANCE.updateViewConfiguration(viewControllerPair.getView(), model);

        AbstractController controller = viewControllerPair.getController();
        if (controller != null) {
            controller.setModel(model);
            controller.updateView();
            controller.renderView();
        }
    }

    private boolean createViewAndController(Class<? extends ControllableView> classViewToShow,
                                            Map<Class<? extends ControllableView>, ViewControllerPair> views) {
        ControllableView view;
        try {
            view = classViewToShow.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        AbstractController controller = null;
        Class<? extends AbstractController> controllerClass = view.getControllerClass();
        if (controllerClass != null) {
            try {
                controller = (AbstractController) controllerClass.getDeclaredConstructors()[0].newInstance(view);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }

            controller.setModel(null);
        }

        ViewControllerPair pair = new ViewControllerPair(view, controller);
        views.put(classViewToShow, pair);
        return true;
    }
}
