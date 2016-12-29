package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO unfortunately there is NO database object-related how to figure what race belong non-statable, which do not have statId, creatures (units)
public enum UnitMapping {
    INSTANCE;

    private final List<String> humanUnitsNames = new ArrayList<String>() {{
        add("Armsman");
        add("Cleric");
        add("Enchanter");
        add("Marksman");
        add("Mentalist");
        add("Paladin");
        add("Recruit");
        add("Scout");
    }};

    private final List<String> elvesUnitsNames = new ArrayList<String>() {{
        add("Windarcher");
        add("Druid");
        add("Healer");
        add("Wintermage");
        add("Protector");
        add("Ranger");
        add("Wanderer");
        add("Warder");
    }};

    private final List<String> orcsUnitsNames = new ArrayList<String>() {{
        add("Drummer");
        add("Fighter");
        add("Firemaster - 1");
        add("Spearman");
        add("Thug");
        add("Totem");
        add("Veteran");
        add("Hornblower");
    }};

    private final List<String> darkElvesUnitsNames = new ArrayList<String>() {{
        add("Assassin");
        add("Battlemaster");
        add("Darkblade");
        add("Deathknight");
        add("Havoc");
        add("Necromancer");
        add("Sorcerer");
        add("Warlock");
    }};

    private final List<String> dwarvesUnitsNames = new ArrayList<String>() {{
        add("Battlepriest");
        add("Defender");
        add("Demolisher");
        add("Elder");
        add("Elite");
        add("Militia");
        add("Warrior");
        add("Watchman");
    }};

    private final List<String> trollsUnitsNames = new ArrayList<String>() {{
        add("Bouncer");
        add("Champion");
        add("Destroyer");
        add("Devastator");
        add("Hurler");
        add("Rowdy");
        add("Smasher");
        add("Thrower");
    }};

    private Map<String, List<String>> unitRacesToUnitNamesMap = new HashMap<String, List<String>>() {{
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"), humanUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"), elvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"), dwarvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"), orcsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"), trollsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"), darkElvesUnitsNames);
    }};

    public List<String> getUnitNames(String raceName) {
        return unitRacesToUnitNamesMap.get(raceName);
    }

    public String getRaceName(String unitName) {
        for (Map.Entry<String, List<String>> entry : unitRacesToUnitNamesMap.entrySet()) {
            List<String> unitNames = entry.getValue();
            for (String name : unitNames) {
                if (name.equals(unitName)) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }
}
