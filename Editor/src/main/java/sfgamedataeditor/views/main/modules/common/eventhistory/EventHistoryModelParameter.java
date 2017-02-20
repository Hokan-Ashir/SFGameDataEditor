package sfgamedataeditor.views.main.modules.common.eventhistory;

public class EventHistoryModelParameter {
    private final boolean isRedoPossible;
    private final boolean isUndoPossible;

    public EventHistoryModelParameter(boolean isRedoPossible, boolean isUndoPossible) {
        this.isRedoPossible = isRedoPossible;
        this.isUndoPossible = isUndoPossible;
    }

    public boolean isRedoPossible() {
        return isRedoPossible;
    }

    public boolean isUndoPossible() {
        return isUndoPossible;
    }
}
