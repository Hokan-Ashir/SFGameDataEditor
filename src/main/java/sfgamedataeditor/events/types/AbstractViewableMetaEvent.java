package sfgamedataeditor.events.types;

public abstract class AbstractViewableMetaEvent<T extends AbstractViewableMetaEvent> extends AbstractMetaEvent {
    public abstract T createParentMetaEvent();
}
