package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.*;

// TODO unfortunately there is NO database object-related how to figure what race belong non-statable, which do not have statId, creatures (units)
public enum UnitMapping {
    INSTANCE;

    private final Set<String> humanUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("537")); // "Armsman"
        add(getI18NUnitName("538")); // "Cleric"
        add(getI18NUnitName("1227")); // "Cleric (Upgrade)"
        add(getI18NUnitName("539")); // "Enchanter"
        add(getI18NUnitName("1225")); // "Enchanter (Upgrade)"
        add(getI18NUnitName("540")); // "Marksman"
        add(getI18NUnitName("1223")); // "Marksman (Upgrade)"
        add(getI18NUnitName("541")); // "Mentalist"
        add(getI18NUnitName("1224")); // "Mentalist (Upgrade)"
        add(getI18NUnitName("542")); // "Paladin"
        add(getI18NUnitName("1226")); // "Paladin (Upgrade)"
        add(getI18NUnitName("543")); // "Recruit"
        add(getI18NUnitName("544")); // "Scout"
    }};

    private final Set<String> elvesUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("545")); // "Windarcher"
        add(getI18NUnitName("1229")); // "Windarcher (Upgrade)"
        add(getI18NUnitName("546")); // "Druid"
        add(getI18NUnitName("1240")); // "Druid (Upgrade)"
        add(getI18NUnitName("547")); // "Healer"
        add(getI18NUnitName("1239")); // "Healer (Upgrade)"
        add(getI18NUnitName("548")); // "Wintermage"
        add(getI18NUnitName("1435")); // "Wintermage (Upgrade)"
        add(getI18NUnitName("549")); // "Protector"
        add(getI18NUnitName("1231")); // "Protector (Upgrade)"
        add(getI18NUnitName("550")); // "Ranger"
        add(getI18NUnitName("551")); // "Wanderer"
        add(getI18NUnitName("1230")); // "Wanderer (Upgrade)"
        add(getI18NUnitName("552")); // "Warder"
        add(getI18NUnitName("1232")); // "Warder (Upgrade)"
    }};

    private final Set<String> orcsUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("553")); // "Drummer"
        add(getI18NUnitName("1249")); // "Drummer (Upgrade)"
        add(getI18NUnitName("554")); // "Fighter"
        add(getI18NUnitName("555")); // "Firemaster"
        add(getI18NUnitName("1246")); // "Firemaster (Upgrade)"
        add(getI18NUnitName("556")); // "Spearman"
        add(getI18NUnitName("1247")); // "Spearman (Upgrade)"
        add(getI18NUnitName("557")); // "Thug"
        add(getI18NUnitName("558")); // "Totem"
        add(getI18NUnitName("1245")); // "Totem (Upgrade)"
        add(getI18NUnitName("559")); // "Veteran"
        add(getI18NUnitName("1248")); // "Veteran (Upgrade)"
        add(getI18NUnitName("560")); // "Hornblower"
        add(getI18NUnitName("1250")); // "Hornblower (Upgrade)"
    }};

    private final Set<String> darkElvesUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("561")); // "Assassin"
        add(getI18NUnitName("1294")); // "Assassin (Upgrade)"
        add(getI18NUnitName("562")); // "Battlemaster"
        add(getI18NUnitName("1297")); // "Battlemaster (Upgrade)"
        add(getI18NUnitName("563")); // "Darkblade"
        add(getI18NUnitName("564")); // "Deathknight"
        add(getI18NUnitName("1296")); // "Deathknight (Upgrade)"
        add(getI18NUnitName("565")); // "Havoc"
        add(getI18NUnitName("1299")); // "Havoc (Upgrade)"
        add(getI18NUnitName("566")); // "Necromancer"
        add(getI18NUnitName("1295")); // "Necromancer (Upgrage)"
        add(getI18NUnitName("567")); // "Sorcerer"
        add(getI18NUnitName("568")); // "Warlock"
        add(getI18NUnitName("1298")); // "Warlock (Upgrade)"
    }};

    private final Set<String> dwarvesUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("569")); // "Battlepriest"
        add(getI18NUnitName("1234")); // "Battlepriest (Upgrade)"
        add(getI18NUnitName("570")); // "Defender"
        add(getI18NUnitName("1238")); // "Defender (Upgrade)"
        add(getI18NUnitName("571")); // "Demolisher"
        add(getI18NUnitName("572")); // "Elder"
        add(getI18NUnitName("1235")); // "Elder (Upgrade)"
        add(getI18NUnitName("573")); // "Elite"
        add(getI18NUnitName("1236")); // "Elite (Upgrade)"
        add(getI18NUnitName("574")); // "Militia"
        add(getI18NUnitName("575")); // "Warrior"
        add(getI18NUnitName("1237")); // "Warrior (Upgrade)"
        add(getI18NUnitName("576")); // "Watchman"
    }};

    private final Set<String> trollsUnitsNames = new HashSet<String>() {{
        add(getI18NUnitName("577")); // "Bouncer"
        add(getI18NUnitName("1287")); // "Bouncer (Upgrade)"
        add(getI18NUnitName("578")); // "Champion"
        add(getI18NUnitName("1290")); // "Champion (Upgrade)"
        add(getI18NUnitName("579")); // "Destroyer"
        add(getI18NUnitName("1289")); // "Destroyer (Upgrade)"
        add(getI18NUnitName("580")); // "Devastator"
        add(getI18NUnitName("1286")); // "Devastator (Upgrade)"
        add(getI18NUnitName("581")); // "Hurler"
        add(getI18NUnitName("1288")); // "Hurler (Upgrade)"
        add(getI18NUnitName("582")); // "Rowdy"
        add(getI18NUnitName("583")); // "Smasher"
        add(getI18NUnitName("1284")); // "Smasher (Upgrade)"
        add(getI18NUnitName("584")); // "Thrower"
        add(getI18NUnitName("1285")); // "Thrower (Upgrade)"
    }};

    private String getI18NUnitName(String unitId) {
        return I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, unitId);
    }

    private final Map<String, Set<String>> unitRacesToUnitNamesMap = new HashMap<String, Set<String>>() {{
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"), humanUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"), elvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"), dwarvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"), orcsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"), trollsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"), darkElvesUnitsNames);
    }};

    public List<String> getUnitRacesList() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : unitRacesToUnitNamesMap.entrySet()) {
            result.add(entry.getKey());
        }

        return result;
    }

    public Set<String> getUnitNames(String raceName) {
        return unitRacesToUnitNamesMap.get(raceName);
    }

    public String getRaceName(String unitName) {
        for (Map.Entry<String, Set<String>> entry : unitRacesToUnitNamesMap.entrySet()) {
            Set<String> unitNames = entry.getValue();
            for (String name : unitNames) {
                if (name.equals(unitName)) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }
}
