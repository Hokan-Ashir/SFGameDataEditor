package sfgamedataeditor.fieldwrapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MappedColumn {
    String name();
    Class<?> daoClass();
}
