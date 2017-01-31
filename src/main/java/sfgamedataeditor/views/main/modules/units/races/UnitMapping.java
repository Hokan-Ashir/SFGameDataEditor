package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO unfortunately there is NO database object-related how to figure what race belong non-statable, which do not have statId, creatures (units)
public enum UnitMapping {
    INSTANCE;

    private final Set<String> humanUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "537")); // "Armsman"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "538")); // "Cleric"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1227")); // "Cleric (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "539")); // "Enchanter"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1225")); // "Enchanter (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "540")); // "Marksman"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1223")); // "Marksman (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "541")); // "Mentalist"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1224")); // "Mentalist (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "542")); // "Paladin"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1226")); // "Paladin (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "543")); // "Recruit"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "544")); // "Scout"
    }};

    private final Set<String> elvesUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "545")); // "Windarcher"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1229")); // "Windarcher (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "546")); // "Druid"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1240")); // "Druid (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "547")); // "Healer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1239")); // "Healer (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "548")); // "Wintermage"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1435")); // "Wintermage (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "549")); // "Protector"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1231")); // "Protector (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "550")); // "Ranger"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "551")); // "Wanderer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1230")); // "Wanderer (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "552")); // "Warder"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1232")); // "Warder (Upgrade)"
    }};

    private final Set<String> orcsUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "553")); // "Drummer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1249")); // "Drummer (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "554")); // "Fighter"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "555")); // "Firemaster"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1246")); // "Firemaster (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "556")); // "Spearman"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1247")); // "Spearman (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "557")); // "Thug"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "558")); // "Totem"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1245")); // "Totem (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "559")); // "Veteran"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1248")); // "Veteran (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "560")); // "Hornblower"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1250")); // "Hornblower (Upgrade)"
    }};

    private final Set<String> darkElvesUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "561")); // "Assassin"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1294")); // "Assassin (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "562")); // "Battlemaster"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1297")); // "Battlemaster (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "563")); // "Darkblade"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "564")); // "Deathknight"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1296")); // "Deathknight (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "565")); // "Havoc"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1299")); // "Havoc (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "566")); // "Necromancer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1295")); // "Necromancer (Upgrage)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "567")); // "Sorcerer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "568")); // "Warlock"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1298")); // "Warlock (Upgrade)"
    }};

    private final Set<String> dwarvesUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "569")); // "Battlepriest"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1234")); // "Battlepriest (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "570")); // "Defender"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1238")); // "Defender (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "571")); // "Demolisher"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "572")); // "Elder"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1235")); // "Elder (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "573")); // "Elite"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1236")); // "Elite (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "574")); // "Militia"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "575")); // "Warrior"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1237")); // "Warrior (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "576")); // "Watchman"
    }};

    private final Set<String> trollsUnitsNames = new HashSet<String>() {{
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "577")); // "Bouncer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1287")); // "Bouncer (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "578")); // "Champion"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1290")); // "Champion (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "579")); // "Destroyer"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1289")); // "Destroyer (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "580")); // "Devastator"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1286")); // "Devastator (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "581")); // "Hurler"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1288")); // "Hurler (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "582")); // "Rowdy"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "583")); // "Smasher"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1284")); // "Smasher (Upgrade)"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "584")); // "Thrower"
        add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, "1285")); // "Thrower (Upgrade)"
    }};

    private final Map<String, Set<String>> unitRacesToUnitNamesMap = new HashMap<String, Set<String>>() {{
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"), humanUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"), elvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"), dwarvesUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"), orcsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"), trollsUnitsNames);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"), darkElvesUnitsNames);
    }};

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
