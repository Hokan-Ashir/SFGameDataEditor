package sfgamedataeditor.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GUIElement {
    int GUIElementId();
    String[] DTOColumnNames() default {};
    Class<?> DTOClass() default void.class;
}
