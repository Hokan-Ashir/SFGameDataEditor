package sfgamedataeditor.events;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractMetaEvent {
    private Map<Class<? extends ShowViewEvent>, ShowViewEvent> eventMap = new LinkedHashMap<>();

    public AbstractMetaEvent() {
        addEvents();
    }

    protected abstract void addEvents();

    protected void addEvent(ShowViewEvent event) {
        eventMap.put(event.getClass(), event);
    }

    protected void addEvents(ShowViewEvent... events) {
        for (ShowViewEvent event : events) {
            eventMap.put(event.getClass(), event);
        }
    }

    public Collection<ShowViewEvent> getEventList() {
        return eventMap.values();
    }

    public void setEventParameter(Class<? extends ShowViewEvent> eventClass, Object parameter) {
        if (!eventMap.containsKey(eventClass)) {
            throw new RuntimeException("No event class " + eventClass.getName() + " exists in metaevent " + getClass().getName());
        }

        eventMap.get(eventClass).setObjectParameter(parameter);
    }
}
