package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;

public class ShowViewEvent extends ViewEvent {
    private final Model model;
    private boolean isShouldBeRecordedInHistory = true;

    public ShowViewEvent(Class<? extends ControllableView> classViewToShow, Model model) {
        super(classViewToShow);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public boolean isShouldBeRecordedInHistory() {
        return isShouldBeRecordedInHistory;
    }

    public void setShouldBeRecordedInHistory(boolean shouldBeRecordedInHistory) {
        isShouldBeRecordedInHistory = shouldBeRecordedInHistory;
    }
}
