package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.commonevents.ShowViewEvent;

import java.util.HashMap;
import java.util.Map;

public enum EventProcessor {
    INSTANCE;

    private Map<Class<? extends Event>, EventProcessingStrategy> strategyMap = new HashMap<Class<? extends Event>, EventProcessingStrategy>() {{
        put(ShowViewEvent.class, new ShowViewEventProcessingStrategy());
    }};

    public void process(Event event) {
        EventProcessingStrategy eventProcessingStrategy = null;
        Class<? extends Event> eventClass = event.getClass();
        for (Map.Entry<Class<? extends Event>, EventProcessingStrategy> entry : strategyMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(eventClass)) {
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
