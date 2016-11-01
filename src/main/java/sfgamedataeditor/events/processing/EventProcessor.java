package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.*;

import java.util.HashMap;
import java.util.Map;

public enum EventProcessor {
    INSTANCE;

    private Map<Class<? extends Event>, EventProcessingStrategy> strategyMap = new HashMap<Class<? extends Event>, EventProcessingStrategy>() {{
        put(ShowViewEvent.class, new ShowViewEventProcessingStrategy());
        put(ViewRenderedEvent.class, new ViewRenderedEventProcessingStrategy());
        put(UnShowViewEvent.class, new UnShowEventProcessingStrategy());
        put(ViewUnrenderedEvent.class, new ViewUnrenderedEventProcessingStrategy());
        put(UpdateViewModelEvent.class, new UpdateViewModelEventStrategy());
    }};

    public void process(Event event) {
        EventProcessingStrategy eventProcessingStrategy = null;
        Class<? extends Event> eventClass = event.getClass();
        for (Map.Entry<Class<? extends Event>, EventProcessingStrategy> entry : strategyMap.entrySet()) {
            if (entry.getKey().equals(eventClass)) {
                eventProcessingStrategy = entry.getValue();
                break;
            }
        }

        if (eventProcessingStrategy == null) {
            throw new RuntimeException("No event processing strategy associated with " + eventClass + " class");
        }

        eventProcessingStrategy.process(event);
    }
}
