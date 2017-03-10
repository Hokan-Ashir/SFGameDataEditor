package sfgamedataeditor.views.main.modules.common.eventhistory;

class EventHistoryModelParameter {
    private final boolean isRedoPossible;
    private final boolean isUndoPossible;

    public EventHistoryModelParameter(boolean isRedoPossible, boolean isUndoPossible) {
        this.isRedoPossible = isRedoPossible;
        this.isUndoPossible = isUndoPossible;
    }

    boolean isRedoPossible() {
        return isRedoPossible;
    }

    boolean isUndoPossible() {
        return isUndoPossible;
    }
}
