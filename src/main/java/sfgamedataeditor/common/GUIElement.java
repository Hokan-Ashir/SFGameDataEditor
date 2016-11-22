package sfgamedataeditor.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GUIElement {
    int GUIElementId();
    String DTOColumnName();
    Class<?> DTOClass();
}
