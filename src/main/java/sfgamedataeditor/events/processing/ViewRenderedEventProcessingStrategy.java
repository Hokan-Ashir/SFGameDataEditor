package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.ViewRenderedEvent;
import sfgamedataeditor.mvc.ShowViewDispatcher;

public class ViewRenderedEventProcessingStrategy implements EventProcessingStrategy<ViewRenderedEvent> {

    @Override
    public void process(ViewRenderedEvent event) {
        ShowViewDispatcher.INSTANCE.registerRenderedView(event.getClassViewRendered());
    }
}
