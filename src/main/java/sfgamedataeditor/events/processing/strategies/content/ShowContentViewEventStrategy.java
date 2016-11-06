package sfgamedataeditor.events.processing.strategies.content;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewControllerPair;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.processing.strategies.EventProcessingStrategy;
import sfgamedataeditor.events.processing.strategies.content.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.events.processing.strategies.content.viewhierarchy.ViewHierarchyNode;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;

import java.util.ArrayList;
import java.util.List;

public class ShowContentViewEventStrategy implements EventProcessingStrategy<ShowContentViewEvent> {
    @Override
    public void process(ShowContentViewEvent event) {
        EventHistory.INSTANCE.addEventToHistory(event);
        List<ViewHierarchyNode> renderedNodes = ViewHierarchy.INSTANCE.getRenderedNodes();
        for (ViewHierarchyNode renderedNode : renderedNodes) {
            unrenderNode(renderedNode);
        }

        List<ViewHierarchyNode> nodesToShow = ViewHierarchy.INSTANCE.getNodesToShow(event.getViewClass());
        List<Model> models = new ArrayList<>();
        models.add(event.getModel());
        for (int i = 1; i < nodesToShow.size(); ++i) {
            ViewHierarchyNode node = nodesToShow.get(i);
            if (node.getModelCreator() != null) {
                models.add(node.getModelCreator().createModel(models.get(i - 1)));
            }
        }

        for (int i = nodesToShow.size() - 1; i > -1; --i) {
            ViewHierarchyNode node = nodesToShow.get(i);
            EventProcessor.INSTANCE.process(new ShowViewEvent(node.getViewClass(), models.get(i)));
        }
    }

    private void unrenderNode(ViewHierarchyNode renderedNode) {
        Class<? extends ControllableView> classViewToUnShow = renderedNode.getViewClass();
        ViewControllerPair viewControllerPair = ViewRegister.INSTANCE.getViews().get(classViewToUnShow);
        if (viewControllerPair == null) {
            return;
        }

        AbstractController controller = viewControllerPair.getController();
        if (controller == null) {
            return;
        }

        controller.unRenderView();
        renderedNode.setRenderedOnScreen(false);
    }
}
