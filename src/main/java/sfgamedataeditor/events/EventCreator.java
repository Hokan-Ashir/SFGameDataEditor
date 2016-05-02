package sfgamedataeditor.events;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class EventCreator {

    private static final Logger LOGGER = Logger.getLogger(EventCreator.class);

    private EventCreator() {
    }

    public static <T extends ShowViewEvent> T createEvent(Class<? extends AbstractView> childView,
                                                          Class<? extends AbstractView> parentView,
                                                          Class<T> eventClass) {
        ClassTuple<? extends AbstractView, ? extends AbstractView> tuple = new ClassTuple<>(childView, parentView);
        try {
            Constructor<T> declaredConstructor = eventClass.getDeclaredConstructor(ClassTuple.class);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(tuple);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }
}
