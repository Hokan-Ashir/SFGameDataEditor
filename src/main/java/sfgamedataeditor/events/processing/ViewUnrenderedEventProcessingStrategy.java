package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.ViewUnrenderedEvent;
import sfgamedataeditor.mvc.ShowViewDispatcher;

public class ViewUnrenderedEventProcessingStrategy implements EventProcessingStrategy<ViewUnrenderedEvent> {

    @Override
    public void process(ViewUnrenderedEvent event) {
        ShowViewDispatcher.INSTANCE.unregisterUnRenderedView(event.getClassViewUnrendered());
    }
}
