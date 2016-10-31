package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.events.types.Event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Linkable {

    Class<Event> eventClass();
}
