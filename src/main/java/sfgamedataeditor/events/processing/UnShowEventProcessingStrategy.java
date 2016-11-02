package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.UnShowViewEvent;
import sfgamedataeditor.events.types.ViewUnrenderedEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.ControllableView;

public class UnShowEventProcessingStrategy implements EventProcessingStrategy<UnShowViewEvent> {
    @Override
    public void process(UnShowViewEvent event) {
        Class<? extends ControllableView> classViewToUnShow = event.getClassViewToUnShow();
        ViewControllerPair viewControllerPair = ViewRegister.INSTANCE.getViews().get(classViewToUnShow);
        if (viewControllerPair == null) {
            return;
        }

        AbstractController controller = viewControllerPair.getController();
        if (controller == null) {
            return;
        }

        controller.unRenderView();
        EventProcessor.INSTANCE.process(new ViewUnrenderedEvent(classViewToUnShow));
    }
}
