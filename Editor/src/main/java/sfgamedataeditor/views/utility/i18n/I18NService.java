package sfgamedataeditor.views.utility.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public enum I18NService {
    INSTANCE;

    private static final String COMMON_PROPERTIES_FILE_NAME = "common_gui";
    private static final String RACES_PROPERTIES_FILE_NAME = "races";
    private static final String CREATURE_NAMES_PROPERTIES_FILE_NAME = "creatures";
    private static final String CREATURES_GUI_PROPERTIES_FILE_NAME = "creatures_gui";
    private static final String SKILLS_GUI_PROPERTIES_FILE_NAME = "skills_gui";
    private static final String SPELLS_GUI_PROPERTIES_FILE_NAME = "spells_gui";
    private static final String ITEMS_PROPERTIES_FILE_NAME = "items";
    private static final String SPELL_NAME_MAPPING_FILE_NAME = "spells";
    private static final String ITEM_PIECES_MAPPING_FILE_NAME = "itemTypesMapping";
    private static final String SKILL_SUB_SCHOOL_MAPPING_FILE_NAME = "skillSubSchoolMapping";
    private static final String SKILL_SCHOOL_MAPPING_FILE_NAME = "skillSchoolMapping";
    private static final String ARMOR_GUI_PROPERTIES_FILE_NAME = "armor_gui";
    private static final String WEAPON_GUI_PROPERTIES_FILE_NAME = "weapon_gui";
    private static final String UNITS_GUI_PROPERTIES_FILE_NAME = "units_gui";
    private static final String ITEM_SETS_MAPPING_FILE_NAME = "itemSetsMapping";
    private static final String BUILDING_NAMES_MAPPING_FILE_NAME = "buildings";
    private static final String BUILDINGS_GUI_PROPERTIES_FILE_NAME = "buildings_gui";

    private final Map<I18NTypes, ResourceBundle> bundleMap = new HashMap<>();

    public void loadBundleMessages(Locale locale) {
        bundleMap.put(I18NTypes.COMMON, ResourceBundle.getBundle(COMMON_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.RACES, ResourceBundle.getBundle(RACES_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.CREATURES, ResourceBundle.getBundle(CREATURE_NAMES_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.CREATURES_GUI, ResourceBundle.getBundle(CREATURES_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SPELLS_GUI, ResourceBundle.getBundle(SPELLS_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SKILLS_GUI, ResourceBundle.getBundle(SKILLS_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.ITEMS, ResourceBundle.getBundle(ITEMS_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SPELLS_NAME_MAPPING, ResourceBundle.getBundle(SPELL_NAME_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.ITEM_TYPES_NAME_MAPPING, ResourceBundle.getBundle(ITEM_PIECES_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, ResourceBundle.getBundle(SKILL_SUB_SCHOOL_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.SKILL_SCHOOL_MAPPING, ResourceBundle.getBundle(SKILL_SCHOOL_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.ARMOR_GUI, ResourceBundle.getBundle(ARMOR_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.WEAPON_GUI, ResourceBundle.getBundle(WEAPON_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.UNITS_GUI, ResourceBundle.getBundle(UNITS_GUI_PROPERTIES_FILE_NAME, locale));
        bundleMap.put(I18NTypes.ITEM_SETS, ResourceBundle.getBundle(ITEM_SETS_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.BUILDING_NAMES_MAPPING, ResourceBundle.getBundle(BUILDING_NAMES_MAPPING_FILE_NAME, locale));
        bundleMap.put(I18NTypes.BUILDING_GUI, ResourceBundle.getBundle(BUILDINGS_GUI_PROPERTIES_FILE_NAME, locale));
    }

    public String getMessage(I18NTypes type, String key) {
        return bundleMap.get(type).getString(key);
    }

    public ResourceBundle getBundle(I18NTypes type) {
        return bundleMap.get(type);
    }
}
