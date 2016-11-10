package sfgamedataeditor.events.processing.strategies.content.viewhierarchy;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ControllableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewHierarchyNode {
    private final List<ViewHierarchyNode> children = new ArrayList<>();
    private final Class<? extends ControllableView> viewClass;
    private final ViewHierarchyNode parentNode;
    private boolean renderedOnScreen;
    private ModelCreator modelCreator;

    public ViewHierarchyNode(ViewHierarchyNode parentNode, Class<? extends ControllableView> viewClass, ModelCreator modelCreator) {
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

    public Class<? extends ControllableView> getViewClass() {
        return viewClass;
    }

    public List<ViewHierarchyNode> getChildren() {
        return children;
    }

    public boolean addChild(ViewHierarchyNode node) {
        return children.add(node);
    }

    public boolean addChildren(ViewHierarchyNode... nodes) {
        return children.addAll(Arrays.asList(nodes));
    }
}