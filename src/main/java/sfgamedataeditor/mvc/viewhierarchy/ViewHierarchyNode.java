package sfgamedataeditor.mvc.viewhierarchy;

import sfgamedataeditor.views.common.RenderableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewHierarchyNode {
    private final List<ViewHierarchyNode> children = new ArrayList<>();
    private final Class<? extends RenderableView> viewClass;
    private final ViewHierarchyNode parentNode;

    public ViewHierarchyNode(ViewHierarchyNode parentNode, Class<? extends RenderableView> viewClass) {
        this.viewClass = viewClass;
        this.parentNode = parentNode;
    }

    public ViewHierarchyNode getParentNode() {
        return parentNode;
    }

    public Class<? extends RenderableView> getViewClass() {
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
