package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.processing.strategies.EventProcessingStrategy;
import sfgamedataeditor.events.processing.strategies.ShowViewEventProcessingStrategy;
import sfgamedataeditor.events.processing.strategies.UpdateViewContentEventProcessingStrategy;
import sfgamedataeditor.events.processing.strategies.content.ShowContentViewEventStrategy;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.events.types.UpdateViewContentEvent;

import java.util.HashMap;
import java.util.Map;

public enum EventProcessor {
    INSTANCE;

    private final Map<Class<? extends Event>, EventProcessingStrategy> strategyMap = new HashMap<Class<? extends Event>, EventProcessingStrategy>() {{
        put(ShowViewEvent.class, new ShowViewEventProcessingStrategy());
        put(ShowContentViewEvent.class, new ShowContentViewEventStrategy());
        put(UpdateViewContentEvent.class, new UpdateViewContentEventProcessingStrategy());
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
