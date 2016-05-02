package sfgamedataeditor.events.types;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractMetaEvent {
    private Map<Class<? extends Event>, Event> eventMap = new LinkedHashMap<>();

    public AbstractMetaEvent() {
        addEvents();
    }

    protected abstract void addEvents();

    protected void addEvent(Event event) {
        eventMap.put(event.getClass(), event);
    }

    protected void addEvents(Event... events) {
        for (Event event : events) {
            eventMap.put(event.getClass(), event);
        }
    }

    public Collection<Event> getEventList() {
        return eventMap.values();
    }

    // TODO get rid of "isAssignableFrom" call
    public void setEventParameter(Class<? extends ShowViewEvent> eventClass, Object parameter) {
        if (!eventMap.containsKey(eventClass)) {
            throw new RuntimeException("No event class " + eventClass.getName() + " exists in metaevent " + getClass().getName());
        }

        if (!(ShowViewEvent.class.isAssignableFrom(eventClass))) {
            throw new RuntimeException("Class: " + eventClass.getName() + " not extends, nor implements " + ShowViewEvent.class.getName());
        }

        ((ShowViewEvent) eventMap.get(eventClass)).setObjectParameter(parameter);
    }

    public String getEventDescription() {
        StringBuilder builder = new StringBuilder();
        for (Event event : eventMap.values()) {
            builder.append(event.getDescription());
        }

        return builder.toString();
    }
}
