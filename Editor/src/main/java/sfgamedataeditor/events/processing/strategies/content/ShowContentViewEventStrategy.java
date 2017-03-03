package sfgamedataeditor.events.processing.strategies.content;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewPresenterPair;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.processing.strategies.EventProcessingStrategy;
import sfgamedataeditor.events.processing.strategies.content.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.events.processing.strategies.content.viewhierarchy.ViewHierarchyNode;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryPresenter;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

import java.util.ArrayList;
import java.util.List;

public class ShowContentViewEventStrategy implements EventProcessingStrategy<ShowContentViewEvent> {
    @Override
    public void process(ShowContentViewEvent event) {
        if (event.isShouldBeRecordedInHistory()) {
            updateEventHistory(event);
        }

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

    private void updateEventHistory(ShowContentViewEvent event) {
        EventHistory.INSTANCE.addEventToHistory(event);
        EventHistoryPresenter controller = (EventHistoryPresenter) ViewRegister.INSTANCE.getViews().get(EventHistoryView.class).getPresenter();
        controller.setRedoButtonStatus(EventHistory.INSTANCE.isRedoPossible());
        controller.setUndoButtonStatus(EventHistory.INSTANCE.isUndoPossible());
    }

    private void unRenderNode(ViewHierarchyNode renderedNode) {
        Class<? extends PresentableView> classViewToUnShow = renderedNode.getViewClass();
        ViewPresenterPair viewPresenterPair = ViewRegister.INSTANCE.getViews().get(classViewToUnShow);
        if (viewPresenterPair == null) {
            return;
        }

        AbstractPresenter controller = viewPresenterPair.getPresenter();
        if (controller == null) {
            return;
        }

        controller.unRenderView();
        renderedNode.setRenderedOnScreen(false);
    }
}
