package sfgamedataeditor.views.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractView<T extends AbstractView> {
    private T parentView;
    private List<AbstractView> children = new ArrayList<>();

    public AbstractView(T parentView) {
        this.parentView = parentView;
        if (parentView != null) {
            parentView.getChildren().add(this);
        }
    }

    public List<AbstractView> getChildren() {
        return children;
    }

    public T getParentView() {
        return parentView;
    }

    public abstract void show();

    public void updateData() {
        for (AbstractView child : children) {
            child.updateData();
        }
    }
}
