package sfgamedataeditor.views.utility.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public enum I18NService {
    INSTANCE;

    private static final String COMMON_PROPERTIES_FILE_NAME = "messages";
    private static final String RACES_PROPERTIES_FILE_NAME = "races";
    private static final String CREATURE_NAMES_PROPERTIES_FILE_NAME = "creature_names";
    private static final String CREATURES_GUI_PROPERTIES_FILE_NAME = "creatures_gui";
    private static final String SKILLS_GUI_PROPERTIES_FILE_NAME = "skills_gui";
    private static final String SPELLS_GUI_PROPERTIES_FILE_NAME = "spells_gui";
    private static final String ITEMS_PROPERTIES_FILE_NAME = "items";

    private final Map<I18NTypes, ResourceBundle> bundleMap = new HashMap<>();

    public void loadBundleMessages(Locale locale) {
        bundleMap.put(I18NTypes.COMMON, ResourceBundle.getBundle(COMMON_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.RACES, ResourceBundle.getBundle(RACES_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.CREATURES, ResourceBundle.getBundle(CREATURE_NAMES_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.CREATURES_GUI, ResourceBundle.getBundle(CREATURES_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SPELLS_GUI, ResourceBundle.getBundle(SPELLS_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SKILLS_GUI, ResourceBundle.getBundle(SKILLS_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.ITEMS, ResourceBundle.getBundle(ITEMS_PROPERTIES_FILE_NAME, locale));
    }

    public String getMessage(I18NTypes type, String key) {
        return bundleMap.get(type).getString(key);
    }

    public ResourceBundle getBundle(I18NTypes type) {
        return bundleMap.get(type);
    }
}
