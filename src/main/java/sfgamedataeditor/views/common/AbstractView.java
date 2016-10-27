package sfgamedataeditor.views.common;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractView<T extends AbstractView> implements IView {
    private final T parentView;
    private List<AbstractView<T>> children = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(AbstractView.class);

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
        LOGGER.info("Updated data in: " + getClass().getName() + " with " + (data == null ? "" : data.getClass().getName()));
    }

    public void clearAllComponents() {
        LOGGER.info("Clear all components in: " + getClass().getName());
    }

    public void repaint() {
        LOGGER.info("Repaint in: " + getClass().getName());
    }

    public void addChildView(AbstractView view) {
        LOGGER.info("Added child view: " + view.getClass().getName());
    }
}
