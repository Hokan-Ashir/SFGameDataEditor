package sfgamedataeditor.events.processing.strategies;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.ViewConfigurator;
import sfgamedataeditor.events.processing.ViewPresenterPair;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ShowViewEventProcessingStrategy implements EventProcessingStrategy<ShowViewEvent> {

    private static final Logger LOGGER = Logger.getLogger(ShowViewEventProcessingStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ShowViewEvent event) {
        Class<? extends PresentableView> classViewToShow = event.getViewClass();
        Map<Class<? extends PresentableView>, ViewPresenterPair> views = ViewRegister.INSTANCE.getViews();
        if (!views.containsKey(classViewToShow)) {
            if (!createViewAndController(classViewToShow, views)) {
                return;
            }
        }

        updateViewModelAndConfiguration(event, classViewToShow, views);
    }

    private void updateViewModelAndConfiguration(ShowViewEvent event, Class<? extends PresentableView> classViewToShow,
                                                 Map<Class<? extends PresentableView>, ViewPresenterPair> views) {
        Model model = event.getModel();
        ViewPresenterPair viewPresenterPair = views.get(classViewToShow);
        ViewConfigurator.INSTANCE.updateViewConfiguration(viewPresenterPair.getView(), model);

        AbstractPresenter controller = viewPresenterPair.getPresenter();
        if (controller != null) {
            controller.setModel(model);
            controller.updateView();
            viewPresenterPair.getView().localize();
            controller.renderView();
        }
    }

    private boolean createViewAndController(Class<? extends PresentableView> classViewToShow,
                                            Map<Class<? extends PresentableView>, ViewPresenterPair> views) {
        PresentableView view;
        try {
            view = classViewToShow.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        AbstractPresenter controller = null;
        Class<? extends AbstractPresenter> controllerClass = view.getPresenterClass();
        if (controllerClass != null) {
            try {
                controller = (AbstractPresenter) controllerClass.getDeclaredConstructors()[0].newInstance(view);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }

            controller.setModel(null);
        }

        ViewPresenterPair pair = new ViewPresenterPair(view, controller);
        views.put(classViewToShow, pair);
        return true;
    }
}
