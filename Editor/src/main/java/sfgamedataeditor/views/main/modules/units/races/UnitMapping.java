package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// TODO unfortunately there is NO database object-related how to figure what race belong non-statable, which do not have statId, creatures (units)
public enum UnitMapping {
    INSTANCE;

    // 537 // "Armsman"
    // 538 // "Cleric"
    // 1227 // "Cleric (Upgrade)"
    // 539 // "Enchanter"
    // 1225 // "Enchanter (Upgrade)"
    // 540 // "Marksman"
    // 1223 // "Marksman (Upgrade)"
    // 541 // "Mentalist"
    // 1224 // "Mentalist (Upgrade)"
    // 542 // "Paladin"
    // 1226 // "Paladin (Upgrade)"
    // 543 // "Recruit"
    // 544 // "Scout"
    // 2236 // "Star mage"
    private final List<ObjectTuple> humanUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            537, 538, 1227, 539, 1225, 540, 1223, 541, 1224, 542, 1226, 543, 544, 2236});

    // 545 // "Windarcher"
    // 1229 // "Windarcher (Upgrade)"
    // 546 // "Druid"
    // 1240 // "Druid (Upgrade)"
    // 547 // "Healer"
    // 1239 // "Healer (Upgrade)"
    // 548 // "Wintermage"
    // 1435 // "Wintermage (Upgrade)"
    // 549 // "Protector"
    // 1231 // "Protector (Upgrade)"
    // 550 // "Ranger"
    // 551 // "Wanderer"
    // 1230 // "Wanderer (Upgrade)"
    // 552 // "Warder"
    // 1232 // "Warder (Upgrade)"
    // 2238 // "Stormbringer"
    // 2222 // "Pixie"
    private final List<ObjectTuple> elvesUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            545, 1229, 546, 1240, 547, 1239, 548, 1435, 549, 1231, 550, 551, 1230, 552, 1232, 2238, 2222});

    // 553 // "Drummer"
    // 1249 // "Drummer (Upgrade)"
    // 554 // "Fighter"
    // 555 // "Firemaster"
    // 1246 // "Firemaster (Upgrade)"
    // 556 // "Spearman"
    // 1247 // "Spearman (Upgrade)"
    // 557 // "Thug"
    // 558 // "Totem"
    // 1245 // "Totem (Upgrade)"
    // 559 // "Veteran"
    // 1248 // "Veteran (Upgrade)"
    // 560 // "Hornblower"
    // 1250 // "Hornblower (Upgrade)"
    // 2244 // "Inferno"
    // 2224 // "War boar horde"
    private final List<ObjectTuple> orcsUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            553, 1249, 554, 555, 1246, 556, 1247, 557, 558, 1245, 559, 1248, 560, 1250, 2244, 2224});

    // 561 // "Assassin"
    // 1294 // "Assassin (Upgrade)"
    // 562 // "Battlemaster"
    // 1297 // "Battlemaster (Upgrade)"
    // 563 // "Darkblade"
    // 564 // "Deathknight"
    // 1296 // "Deathknight (Upgrade)"
    // 565 // "Havoc"
    // 1299 // "Havoc (Upgrade)"
    // 566 // "Necromancer"
    // 1295 // "Necromancer (Upgrage)"
    // 567 // "Sorcerer"
    // 568 // "Warlock"
    // 1298 // "Warlock (Upgrade)"
    // 2249 // "Harbinger of chaos"
    private final List<ObjectTuple> darkElvesUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            561, 1294, 562, 1297, 563, 564, 1296, 565, 1299, 566, 1295, 567, 568, 1298, 2249});

    // 569 // "Battlepriest"
    // 1234 // "Battlepriest (Upgrade)"
    // 570 // "Defender"
    // 1238 // "Defender (Upgrade)"
    // 571 // "Demolisher"
    // 572 // "Elder"
    // 1235 // "Elder (Upgrade)"
    // 573 // "Elite"
    // 1236 // "Elite (Upgrade)"
    // 574 // "Militia"
    // 575 // "Warrior"
    // 1237 // "Warrior (Upgrade)"
    // 576 // "Watchman"
    // 2221 // "Stone rams"
    private final List<ObjectTuple> dwarvesUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            569, 1234, 570, 1238, 571, 572, 1235, 573, 1236, 574, 575, 1237, 576, 2221});

    // 577 // "Bouncer"
    // 1287 // "Bouncer (Upgrade)"
    // 578 // "Champion"
    // 1290 // "Champion (Upgrade)"
    // 579 // "Destroyer"
    // 1289 // "Destroyer (Upgrade)"
    // 580 // "Devastator"
    // 1286 // "Devastator (Upgrade)"
    // 581 // "Hurler"
    // 1288 // "Hurler (Upgrade)"
    // 582 // "Rowdy"
    // 583 // "Smasher"
    // 1284 // "Smasher (Upgrade)"
    // 584 // "Thrower"
    // 1285 // "Thrower (Upgrade)"
    // 2245 // "Walking fortress"
    private final List<ObjectTuple> trollsUnitsNames = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[] {
            577, 1287, 578, 1290, 579, 1289, 580, 1286, 581, 1288, 582, 583, 1284, 584, 1285, 2245});

    private final Map<ObjectTuple, List<ObjectTuple>> unitRacesToUnitNamesMap = new TreeMap<ObjectTuple, List<ObjectTuple>>() {{
        put(createRaceTuple("races.humans", 1), humanUnitsNames);
        put(createRaceTuple("races.dwarves", 2), dwarvesUnitsNames);
        put(createRaceTuple( "races.elves", 3), elvesUnitsNames);
        put(createRaceTuple( "races.trolls", 4), trollsUnitsNames);
        put(createRaceTuple("races.orcs", 5), orcsUnitsNames);
        put(createRaceTuple("races.dark.elves", 6), darkElvesUnitsNames);
    }};

    public ObjectTuple createRaceTuple(String race18Nkey, int raceId) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, race18Nkey), raceId);
    }

    public List<ObjectTuple> getUnitRacesList() {
        List<ObjectTuple> result = new ArrayList<>();
        for (Map.Entry<ObjectTuple, List<ObjectTuple>> entry : unitRacesToUnitNamesMap.entrySet()) {
            result.add(entry.getKey());
        }

        return result;
    }

    public List<ObjectTuple> getUnitNames(ObjectTuple tuple) {
        return unitRacesToUnitNamesMap.get(tuple);
    }

    public ObjectTuple getRaceTuple(Integer unitId) {
        for (Map.Entry<ObjectTuple, List<ObjectTuple>> entry : unitRacesToUnitNamesMap.entrySet()) {
            List<ObjectTuple> tuples = entry.getValue();
            for (ObjectTuple tuple : tuples) {
                if (tuple.getObjectId().equals(unitId)) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    public ObjectTuple getUnitTuple(Integer unitId) {
        for (Map.Entry<ObjectTuple, List<ObjectTuple>> entry : unitRacesToUnitNamesMap.entrySet()) {
            List<ObjectTuple> tuples = entry.getValue();
            for (ObjectTuple tuple : tuples) {
                if (tuple.getObjectId().equals(unitId)) {
                    return tuple;
                }
            }
        }

        return null;
    }
}
