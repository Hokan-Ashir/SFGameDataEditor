package sfgamedataeditor.events.processing.strategies.content.viewhierarchy;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.mvc.objects.PresentableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewHierarchyNode {
    private final List<ViewHierarchyNode> children = new ArrayList<>();
    private final Class<? extends PresentableView> viewClass;
    private final ViewHierarchyNode parentNode;
    private boolean renderedOnScreen;
    private final ModelCreator modelCreator;

    public ViewHierarchyNode(ViewHierarchyNode parentNode, Class<? extends PresentableView> viewClass, ModelCreator modelCreator) {
        this.viewClass = viewClass;
        this.parentNode = parentNode;
        renderedOnScreen = false;
        this.modelCreator = modelCreator;
    }

    public boolean isRenderedOnScreen() {
        return renderedOnScreen;
    }

    public ModelCreator getModelCreator() {
        return modelCreator;
    }

    public void setRenderedOnScreen(boolean renderedOnScreen) {
        this.renderedOnScreen = renderedOnScreen;
    }

    public ViewHierarchyNode getParentNode() {
        return parentNode;
    }

    public Class<? extends PresentableView> getViewClass() {
        return viewClass;
    }

    public List<ViewHierarchyNode> getChildren() {
        return children;
    }

    public void addChild(ViewHierarchyNode node) {
        children.add(node);
    }

    public void addChildren(ViewHierarchyNode... nodes) {
        children.addAll(Arrays.asList(nodes));
    }
}
