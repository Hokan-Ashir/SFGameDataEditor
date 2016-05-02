package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.Event;

public interface EventProcessingStrategy<T extends Event> {
    void process(T event);
}
