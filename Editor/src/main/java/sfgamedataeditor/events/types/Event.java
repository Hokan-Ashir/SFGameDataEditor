package sfgamedataeditor.events.types;

public class Event {
    private String description;

    public String getDescription() {
        return description;
    }

    // TODO override in child events, maybe make abstract
    public void setDescription(String description) {
        this.description = description;
    }
}
