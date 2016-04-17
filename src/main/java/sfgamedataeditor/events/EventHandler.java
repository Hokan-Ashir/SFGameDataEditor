package sfgamedataeditor.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This is the base type for every {@link EventHandler} that handles {@link ShowViewEvent}s.
 * A {@link EventHandler} must be registered to the {@link EventBus} to receive {@link ShowViewEvent}s.
 * This is done by {@link EventBus#addHandler(Object)}
 *
 * @author Hannes Dorfmann
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EventHandler {

}
