package sfgamedataeditor.views.utility.i18n;

import i18nbase.objects.I18NObject;
import i18nbase.objects.Service;
import i18nbase.objects.armor_gui.ArmorGUI;
import i18nbase.objects.armor_gui.ArmorGUI_DE;
import i18nbase.objects.armor_gui.ArmorGUI_FR;
import i18nbase.objects.armor_gui.ArmorGUI_RU;
import i18nbase.objects.buildings.Buildings;
import i18nbase.objects.buildings.Buildings_DE;
import i18nbase.objects.buildings.Buildings_FR;
import i18nbase.objects.buildings.Buildings_RU;
import i18nbase.objects.buildings_gui.BuildingsGUI;
import i18nbase.objects.buildings_gui.BuildingsGUI_DE;
import i18nbase.objects.buildings_gui.BuildingsGUI_FR;
import i18nbase.objects.buildings_gui.BuildingsGUI_RU;
import i18nbase.objects.common_gui.CommonGUI;
import i18nbase.objects.common_gui.CommonGUI_DE;
import i18nbase.objects.common_gui.CommonGUI_FR;
import i18nbase.objects.common_gui.CommonGUI_RU;
import i18nbase.objects.creatures.Creatures;
import i18nbase.objects.creatures.Creatures_DE;
import i18nbase.objects.creatures.Creatures_FR;
import i18nbase.objects.creatures.Creatures_RU;
import i18nbase.objects.creatures_gui.CreaturesGUI;
import i18nbase.objects.creatures_gui.CreaturesGUI_DE;
import i18nbase.objects.creatures_gui.CreaturesGUI_FR;
import i18nbase.objects.creatures_gui.CreaturesGUI_RU;
import i18nbase.objects.items.Items;
import i18nbase.objects.items.Items_DE;
import i18nbase.objects.items.Items_FR;
import i18nbase.objects.items.Items_RU;
import i18nbase.objects.itemsets.ItemSets;
import i18nbase.objects.itemsets.ItemSets_DE;
import i18nbase.objects.itemsets.ItemSets_FR;
import i18nbase.objects.itemsets.ItemSets_RU;
import i18nbase.objects.itemtypes.ItemTypes;
import i18nbase.objects.races.Races;
import i18nbase.objects.races.Races_DE;
import i18nbase.objects.races.Races_FR;
import i18nbase.objects.races.Races_RU;
import i18nbase.objects.skills_gui.SkillsGUI;
import i18nbase.objects.skills_gui.SkillsGUI_DE;
import i18nbase.objects.skills_gui.SkillsGUI_FR;
import i18nbase.objects.skills_gui.SkillsGUI_RU;
import i18nbase.objects.skillschoolmapping.SkillSchoolMapping;
import i18nbase.objects.skillssubschoolmapping.SkillSubSchoolMapping;
import i18nbase.objects.spells.Spells;
import i18nbase.objects.spells_gui.SpellsGUI;
import i18nbase.objects.spells_gui.SpellsGUI_DE;
import i18nbase.objects.spells_gui.SpellsGUI_FR;
import i18nbase.objects.spells_gui.SpellsGUI_RU;
import i18nbase.objects.units_gui.UnitsGUI;
import i18nbase.objects.units_gui.UnitsGUI_DE;
import i18nbase.objects.units_gui.UnitsGUI_FR;
import i18nbase.objects.units_gui.UnitsGUI_RU;
import i18nbase.objects.weapons_gui.WeaponsGUI;
import i18nbase.objects.weapons_gui.WeaponsGUI_DE;
import i18nbase.objects.weapons_gui.WeaponsGUI_FR;
import i18nbase.objects.weapons_gui.WeaponsGUI_RU;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public enum I18NService {
    INSTANCE;

    private final Map<I18NTypes, Class<? extends I18NObject>> testMap = new HashMap<>();
    private static final String FRENCH_LANGUAGE = "fr";
    private static final String GERMAN_LANGUAGE = "de";
    private static final String RUSSIAN_LANGUAGE = "ru";

    public void loadBundleMessages(Locale locale) {
        switch (locale.getLanguage()) {
            case FRENCH_LANGUAGE:
                setFrenchLocale();
                break;
            case GERMAN_LANGUAGE:
                setGermanLocale();
                break;
            case RUSSIAN_LANGUAGE:
                setRussianLocale();
            default:
                setEnglishLocale();
                break;
        }
    }

    private void setEnglishLocale() {
        testMap.put(I18NTypes.COMMON, CommonGUI.class);
        testMap.put(I18NTypes.RACES, Races.class);
        testMap.put(I18NTypes.CREATURES, Creatures.class);
        testMap.put(I18NTypes.CREATURES_GUI, CreaturesGUI.class);
        testMap.put(I18NTypes.SPELLS_GUI, SpellsGUI.class);
        testMap.put(I18NTypes.SKILLS_GUI, SkillsGUI.class);
        testMap.put(I18NTypes.ITEMS, Items.class);
        testMap.put(I18NTypes.SPELLS_NAME_MAPPING, Spells.class);
        testMap.put(I18NTypes.ITEM_PIECES_NAME_MAPPING, ItemTypes.class);
        testMap.put(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, SkillSubSchoolMapping.class);
        testMap.put(I18NTypes.SKILL_SCHOOL_MAPPING, SkillSchoolMapping.class);
        testMap.put(I18NTypes.ARMOR_GUI, ArmorGUI.class);
        testMap.put(I18NTypes.WEAPON_GUI, WeaponsGUI.class);
        testMap.put(I18NTypes.UNITS_GUI, UnitsGUI.class);
        testMap.put(I18NTypes.ITEM_SETS, ItemSets.class);
        testMap.put(I18NTypes.BUILDING_NAMES_MAPPING, Buildings.class);
        testMap.put(I18NTypes.BUILDING_GUI, BuildingsGUI.class);
    }

    private void setGermanLocale() {
        testMap.put(I18NTypes.COMMON, CommonGUI_DE.class);
        testMap.put(I18NTypes.RACES, Races_DE.class);
        testMap.put(I18NTypes.CREATURES, Creatures_DE.class);
        testMap.put(I18NTypes.CREATURES_GUI, CreaturesGUI_DE.class);
        testMap.put(I18NTypes.SPELLS_GUI, SpellsGUI_DE.class);
        testMap.put(I18NTypes.SKILLS_GUI, SkillsGUI_DE.class);
        testMap.put(I18NTypes.ITEMS, Items_DE.class);
        testMap.put(I18NTypes.SPELLS_NAME_MAPPING, Spells.class);
        testMap.put(I18NTypes.ITEM_PIECES_NAME_MAPPING, ItemTypes.class);
        testMap.put(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, SkillSubSchoolMapping.class);
        testMap.put(I18NTypes.SKILL_SCHOOL_MAPPING, SkillSchoolMapping.class);
        testMap.put(I18NTypes.ARMOR_GUI, ArmorGUI_DE.class);
        testMap.put(I18NTypes.WEAPON_GUI, WeaponsGUI_DE.class);
        testMap.put(I18NTypes.UNITS_GUI, UnitsGUI_DE.class);
        testMap.put(I18NTypes.ITEM_SETS, ItemSets_DE.class);
        testMap.put(I18NTypes.BUILDING_NAMES_MAPPING, Buildings_DE.class);
        testMap.put(I18NTypes.BUILDING_GUI, BuildingsGUI_DE.class);
    }

    private void setFrenchLocale() {
        testMap.put(I18NTypes.COMMON, CommonGUI_FR.class);
        testMap.put(I18NTypes.RACES, Races_FR.class);
        testMap.put(I18NTypes.CREATURES, Creatures_FR.class);
        testMap.put(I18NTypes.CREATURES_GUI, CreaturesGUI_FR.class);
        testMap.put(I18NTypes.SPELLS_GUI, SpellsGUI_FR.class);
        testMap.put(I18NTypes.SKILLS_GUI, SkillsGUI_FR.class);
        testMap.put(I18NTypes.ITEMS, Items_FR.class);
        testMap.put(I18NTypes.SPELLS_NAME_MAPPING, Spells.class);
        testMap.put(I18NTypes.ITEM_PIECES_NAME_MAPPING, ItemTypes.class);
        testMap.put(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, SkillSubSchoolMapping.class);
        testMap.put(I18NTypes.SKILL_SCHOOL_MAPPING, SkillSchoolMapping.class);
        testMap.put(I18NTypes.ARMOR_GUI, ArmorGUI_FR.class);
        testMap.put(I18NTypes.WEAPON_GUI, WeaponsGUI_FR.class);
        testMap.put(I18NTypes.UNITS_GUI, UnitsGUI_FR.class);
        testMap.put(I18NTypes.ITEM_SETS, ItemSets_FR.class);
        testMap.put(I18NTypes.BUILDING_NAMES_MAPPING, Buildings_FR.class);
        testMap.put(I18NTypes.BUILDING_GUI, BuildingsGUI_FR.class);
    }

    private void setRussianLocale() {
        testMap.put(I18NTypes.COMMON, CommonGUI_RU.class);
        testMap.put(I18NTypes.RACES, Races_RU.class);
        testMap.put(I18NTypes.CREATURES, Creatures_RU.class);
        testMap.put(I18NTypes.CREATURES_GUI, CreaturesGUI_RU.class);
        testMap.put(I18NTypes.SPELLS_GUI, SpellsGUI_RU.class);
        testMap.put(I18NTypes.SKILLS_GUI, SkillsGUI_RU.class);
        testMap.put(I18NTypes.ITEMS, Items_RU.class);
        testMap.put(I18NTypes.SPELLS_NAME_MAPPING, Spells.class);
        testMap.put(I18NTypes.ITEM_PIECES_NAME_MAPPING, ItemTypes.class);
        testMap.put(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, SkillSubSchoolMapping.class);
        testMap.put(I18NTypes.SKILL_SCHOOL_MAPPING, SkillSchoolMapping.class);
        testMap.put(I18NTypes.ARMOR_GUI, ArmorGUI_RU.class);
        testMap.put(I18NTypes.WEAPON_GUI, WeaponsGUI_RU.class);
        testMap.put(I18NTypes.UNITS_GUI, UnitsGUI_RU.class);
        testMap.put(I18NTypes.ITEM_SETS, ItemSets_RU.class);
        testMap.put(I18NTypes.BUILDING_NAMES_MAPPING, Buildings_RU.class);
        testMap.put(I18NTypes.BUILDING_GUI, BuildingsGUI_RU.class);
    }

    public String getMessage(I18NTypes type, String key) {
        try {
            return Service.INSTANCE.getValue(testMap.get(type), key);
        } catch (Exception e) {
            return null;
        }
    }

    public String getMessageByValue(I18NTypes type, String value) {
        return Service.INSTANCE.getKey(testMap.get(type), value);
    }

    public List<? extends I18NObject> getI18NObjects(I18NTypes type) {
        return Service.INSTANCE.getAllObjects(testMap.get(type));
    }
}
