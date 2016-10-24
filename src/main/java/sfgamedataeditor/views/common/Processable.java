package sfgamedataeditor.views.common;

import sfgamedataeditor.events.types.AbstractMetaEvent;

public interface Processable<T extends AbstractMetaEvent> {
    Class<T> getMetaEventClass();
}
