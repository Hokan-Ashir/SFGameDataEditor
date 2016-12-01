package sfgamedataeditor.dataextraction;

import sfgamedataeditor.dataextraction.offsets.*;
import sfgamedataeditor.views.utility.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DataOffsetProvider {
    INSTANCE;

    private final Map<DTOOffsetTypes, AbstractOffsetHolder> holderMap = new HashMap<>();

    DataOffsetProvider() {
        holderMap.put(DTOOffsetTypes.SKILL_PARAMETERS, new SkillsOffsetHolder());
        holderMap.put(DTOOffsetTypes.SPELL_PARAMETERS, new SpellsOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_PARAMETERS, new CreaturesOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_COMMON_PARAMETERS, new CreaturesCommonsOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_SPELLS, new CreatureSpellsOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_EQUIPMENT, new CreatureEquipmentOffsetHolder());
    }

    public List<Pair<Integer, Integer>> getOffsets(DTOOffsetTypes types) {
        return holderMap.get(types).getOffsets();
    }

    public int getDataLength(DTOOffsetTypes types) {
        return holderMap.get(types).getDataLength();
    }
}
