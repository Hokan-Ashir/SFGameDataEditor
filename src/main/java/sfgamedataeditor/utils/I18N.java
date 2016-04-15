package sfgamedataeditor.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public enum I18N {
    INSTANCE;

    private ResourceBundle bundle;

    public void loadBundleMessages(String fileName, Locale locale) {
        bundle = ResourceBundle.getBundle(fileName, locale);
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
