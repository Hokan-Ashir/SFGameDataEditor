package sfgamedataeditor.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public final class I18N {
    private static ResourceBundle BUNDLE;

    private I18N() {

    }

    public static void loadBundleMessages(String fileName, Locale locale) {
        I18N.BUNDLE = ResourceBundle.getBundle(fileName, locale);
    }

    public static String getMessage(String key) {
        return BUNDLE.getString(key);
    }
}
