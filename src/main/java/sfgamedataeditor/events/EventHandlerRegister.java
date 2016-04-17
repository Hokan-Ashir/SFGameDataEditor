package sfgamedataeditor.events;

public enum EventHandlerRegister {
    INSTANCE;

    private EventBus eventBus = new EventBus();

    public void addEventHandler(Object eventHandler) {
        eventBus.addHandler(eventHandler);
    }

    public <T extends ShowViewEvent> void fireEvent(T event) {
        eventBus.fireEvent(event);
    }
}
