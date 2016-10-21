package sfgamedataeditor.views.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractView<T extends AbstractView> implements IView {
    private final T parentView;
    private List<AbstractView<T>> children = new ArrayList<>();

    public AbstractView(T parentView) {
        this.parentView = parentView;
        if (parentView != null) {
            parentView.<T>getChildren().add(this);
        }
    }

    public List<AbstractView<T>> getChildren() {
        return children;
    }

    public boolean addChild(AbstractView<T> child) {
        if (children.contains(child)) {
            return false;
        }

        return children.add(child);
    }

    public T getParentView() {
        return parentView;
    }

    public void clearView() {
        children.clear();
        clearAllComponents();
        repaint();
    }

    public void updateData(Object data) {
        //System.out.println("Updated data in: " + getClass().getName() + " with " + data.getClass().getName());
    }

    public void clearAllComponents() {
        //System.out.println("Clear all components in: " + getClass().getName());
    }

    public void repaint() {
        //System.out.println("Repaint in: " + getClass().getName());
    }

    public void addChildView(AbstractView view) {
        //System.out.println("Added cild: " + view.getClass().getName());
    }
}
