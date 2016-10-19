package sfgamedataeditor.fieldwrapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
public @interface MappedColumn {
    String name();
}
