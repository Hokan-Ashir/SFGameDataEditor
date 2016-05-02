package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.views.common.AbstractView;

public class ClearViewEventStrategy implements EventProcessingStrategy<ClearViewEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(ClearViewEvent event) {
        AbstractView view = event.getView();
        view.clearView();
    }
}
