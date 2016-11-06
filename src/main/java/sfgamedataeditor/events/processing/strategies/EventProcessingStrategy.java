package sfgamedataeditor.events.processing.strategies;

import sfgamedataeditor.events.types.Event;

public interface EventProcessingStrategy<T extends Event> {
    void process(T event);
}
