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
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

import java.util.ArrayList;
import java.util.List;

public class ShowContentViewEventStrategy implements EventProcessingStrategy<ShowContentViewEvent> {
    @Override
    public void process(ShowContentViewEvent event) {
        EventHistory.INSTANCE.addEventToHistory(event);
        ViewRegister.INSTANCE.getView(EventHistoryView.class).setRedoButtonStatus(EventHistory.INSTANCE.isRedoPossible());
        ViewRegister.INSTANCE.getView(EventHistoryView.class).setUndoButtonStatus(EventHistory.INSTANCE.isUndoPossible());
        List<ViewHierarchyNode> renderedNodes = ViewHierarchy.INSTANCE.getRenderedNodes();
        for (ViewHierarchyNode renderedNode : renderedNodes) {
            unRenderNode(renderedNode);
        }

        List<ViewHierarchyNode> nodesToShow = ViewHierarchy.INSTANCE.getNodesToShow(event.getViewClass());
        List<Model> models = new ArrayList<>();
        models.add(event.getModel());
        for (int i = 1; i < nodesToShow.size(); ++i) {
            ViewHierarchyNode node = nodesToShow.get(i - 1);
            models.add(node.getModelCreator().createModel(models.get(i - 1)));
        }

        for (int i = nodesToShow.size() - 1; i > -1; --i) {
            ViewHierarchyNode node = nodesToShow.get(i);
            node.setRenderedOnScreen(true);
            EventProcessor.INSTANCE.process(new ShowViewEvent(node.getViewClass(), models.get(i)));
        }
    }

    private void unRenderNode(ViewHierarchyNode renderedNode) {
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
