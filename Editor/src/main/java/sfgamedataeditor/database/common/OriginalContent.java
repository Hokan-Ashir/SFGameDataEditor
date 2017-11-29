package sfgamedataeditor.database.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalContent {
    int number();
    boolean isSource();
}
