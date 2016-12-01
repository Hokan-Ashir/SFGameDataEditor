package sfgamedataeditor.dataextraction;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SkillsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SpellsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.creatures.*;
import sfgamedataeditor.dataextraction.offsets.creatures.production.CreatureBuildingsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.creatures.production.CreatureResourcesOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.items.ItemEffectsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.items.ItemRequirementsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.items.ItemSpellEffectsOffsetHolder;
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
        holderMap.put(DTOOffsetTypes.CREATURE_CORPSE_LOOT, new CreatureCorpseLootOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_BUILDINGS, new CreatureBuildingsOffsetHolder());
        holderMap.put(DTOOffsetTypes.CREATURE_RESOURCES, new CreatureResourcesOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_EFFECTS, new ItemEffectsOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_SPELL_EFFECTS, new ItemSpellEffectsOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_REQUIREMENTS, new ItemRequirementsOffsetHolder());
    }

    public List<Pair<Integer, Integer>> getOffsets(DTOOffsetTypes types) {
        return holderMap.get(types).getOffsets();
    }

    public int getDataLength(DTOOffsetTypes types) {
        return holderMap.get(types).getDataLength();
    }
}
