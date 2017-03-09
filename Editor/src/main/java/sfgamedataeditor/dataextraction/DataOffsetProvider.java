package sfgamedataeditor.dataextraction;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.buildings.BuildingsArmyRequirementsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.buildings.BuildingsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.buildings.BuildingsRequirementsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.creatures.*;
import sfgamedataeditor.dataextraction.offsets.creatures.production.CreatureBuildingsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.creatures.production.CreatureResourcesOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.items.*;
import sfgamedataeditor.dataextraction.offsets.merchants.MerchantInventoryItemsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.merchants.MerchantInventoryOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.skills.SkillsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.spells.SpellsOffsetHolder;
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
        holderMap.put(DTOOffsetTypes.CREATURE_SKILLS, new CreatureSkillsOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_EFFECTS, new ItemEffectsOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_SPELL_EFFECTS, new ItemSpellEffectsOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_REQUIREMENTS, new ItemRequirementsOffsetHolder());
        holderMap.put(DTOOffsetTypes.WEAPON_PARAMETERS, new WeaponParametersOffsetHolder());
        holderMap.put(DTOOffsetTypes.ARMOR_PARAMETERS, new ArmorParametersOffsetHolder());
        holderMap.put(DTOOffsetTypes.ITEM_PRICES, new ItemPriceParametersOffsetHolder());
        holderMap.put(DTOOffsetTypes.MERCHANT_INVENTORY, new MerchantInventoryOffsetHolder());
        holderMap.put(DTOOffsetTypes.MERCHANT_INVENTORY_ITEMS, new MerchantInventoryItemsOffsetHolder());
        holderMap.put(DTOOffsetTypes.BUILDINGS, new BuildingsOffsetHolder());
        holderMap.put(DTOOffsetTypes.BUILDINGS_REQUIREMENTS, new BuildingsRequirementsOffsetHolder());
        holderMap.put(DTOOffsetTypes.BUILDINGS_ARMY_REQUIREMENTS, new BuildingsArmyRequirementsOffsetHolder());
        holderMap.put(DTOOffsetTypes.HERO_SPELLS, new HeroSpellsOffsetHolder());
        holderMap.put(DTOOffsetTypes.CHEST_CORPSE_LOOT, new CreatureCorpseLootOffsetHolder());
    }

    public List<Pair<Integer, Integer>> getOffsets(DTOOffsetTypes types) {
        return holderMap.get(types).getOffsets();
    }

    public int getDataLength(DTOOffsetTypes types) {
        return holderMap.get(types).getDataLength();
    }
}
