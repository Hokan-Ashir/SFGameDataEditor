package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.events.AbstractMetaEvent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Linkable {

    Class<AbstractMetaEvent> eventClass();
}
