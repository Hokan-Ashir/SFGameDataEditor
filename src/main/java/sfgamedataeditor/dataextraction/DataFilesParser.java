package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.DTODecorator;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootTableService;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsTableService;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.effects.ItemEffectsTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.database.merchants.inventory.MerchantInventoryTableService;
import sfgamedataeditor.database.merchants.items.MerchantInventoryItemsTableService;
import sfgamedataeditor.database.skill.parameters.SkillParametersTableService;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public enum DataFilesParser {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(DataFilesParser.class);

    public void extractSkillsDataFromFile(RandomAccessFile file) {
        SkillParametersTableService.INSTANCE.createSkillParametersTable();

        List<Pair<Integer, Integer>> skillOffsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.SKILL_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.SKILL_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, skillOffsets, dataLength);
        SkillParametersTableService.INSTANCE.addRecordsToSkillParametersTable(offsettedData);
    }

    public void extractSpellsDataFromFile(RandomAccessFile file) {
        SpellNameTableService.INSTANCE.createSpellNameTable();
        SpellParametersTableService.INSTANCE.createSpellParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.SPELL_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.SPELL_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        SpellParametersTableService.INSTANCE.addRecordsToSpellParametersTable(offsettedData);
    }

    public void extractCreaturesDataFromFile(RandomAccessFile file) {
        extractCommonCreatureParametersDataFromFile(file);
        extractCreatureSpellParametersDataFromFile(file);
        extractCreatureEquipmentParametersDataFromFile(file);
        extractCreatureCorpseLootParametersDataFromFile(file);
        extractCreatureBuildingsParametersDataFromFile(file);
        extractCreatureResourcesParametersDataFromFile(file);
        extractCreatureParametersDataFromFile(file);
        extractCreatureSkillsParametersDataFromFile(file);
    }

    private void extractCreatureParametersDataFromFile(RandomAccessFile file) {
        CreatureParametersTableService.INSTANCE.createCreatureParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureParametersTableService.INSTANCE.addRecordsToCreatureParametersTable(offsettedData);
    }

    private void extractCommonCreatureParametersDataFromFile(RandomAccessFile file) {
        CreatureCommonParametersTableService.INSTANCE.createCommonCreatureParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_COMMON_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_COMMON_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureCommonParametersTableService.INSTANCE.addRecordsToCommonCreatureParametersTable(offsettedData, new CreaturesObjectDecorator());
    }

    private void extractCreatureSpellParametersDataFromFile(RandomAccessFile file) {
        CreatureSpellTableService.INSTANCE.createCreatureSpellParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_SPELLS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_SPELLS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureSpellTableService.INSTANCE.addRecordsToCreatureSpellParametersTable(offsettedData);
    }

    private void extractCreatureEquipmentParametersDataFromFile(RandomAccessFile file) {
        CreatureEquipmentTableService.INSTANCE.createCreatureEquipmentParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_EQUIPMENT);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_EQUIPMENT);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureEquipmentTableService.INSTANCE.addRecordsToCreatureEquipmentParametersTable(offsettedData);
    }

    private void extractCreatureCorpseLootParametersDataFromFile(RandomAccessFile file) {
        CreatureCorpseLootTableService.INSTANCE.createCreatureCorpseLootParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_CORPSE_LOOT);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_CORPSE_LOOT);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureCorpseLootTableService.INSTANCE.addRecordsToCreatureCorpseLootParametersTable(offsettedData);
    }

    private void extractCreatureBuildingsParametersDataFromFile(RandomAccessFile file) {
        CreatureBuildingsTableService.INSTANCE.createCreatureBuildingsParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_BUILDINGS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_BUILDINGS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureBuildingsTableService.INSTANCE.addRecordsToCreatureBuildingParametersTable(offsettedData);
    }

    private void extractCreatureResourcesParametersDataFromFile(RandomAccessFile file) {
        CreatureResourcesTableService.INSTANCE.createCreatureResourcesParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_RESOURCES);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_RESOURCES);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureResourcesTableService.INSTANCE.addRecordsToCreatureResourcesParametersTable(offsettedData);
    }

    private void extractCreatureSkillsParametersDataFromFile(RandomAccessFile file) {
        CreatureSkillTableService.INSTANCE.createCreatureSkillParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_SKILLS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_RESOURCES);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureSkillTableService.INSTANCE.addRecordsToCreatureSkillParametersTable(offsettedData);
    }

    public void extractItemDataFromFile(RandomAccessFile file) {
        extractItemEffectsDataFromFile(file);
        extractItemSpellEffectsDataFromFile(file);
        extractItemRequirementsDataFromFile(file);
        extractWeaponParametersDataFromFile(file);
        extractArmorParametersDataFromFile(file);
        extractItemPricesDataFromFile(file);
    }

    private void extractItemEffectsDataFromFile(RandomAccessFile file) {
        ItemEffectsTableService.INSTANCE.createItemEffectsTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.ITEM_EFFECTS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.ITEM_EFFECTS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        ItemEffectsTableService.INSTANCE.addRecordsToItemEffectsTable(offsettedData);
    }

    private void extractItemSpellEffectsDataFromFile(RandomAccessFile file) {
        ItemSpellEffectsTableService.INSTANCE.createItemSpellEffectsTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.ITEM_SPELL_EFFECTS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.ITEM_SPELL_EFFECTS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        ItemSpellEffectsTableService.INSTANCE.addRecordsToItemSpellEffectsTable(offsettedData);
    }

    private void extractItemRequirementsDataFromFile(RandomAccessFile file) {
        ItemRequirementsTableService.INSTANCE.createItemRequirementsTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.ITEM_REQUIREMENTS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.ITEM_REQUIREMENTS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        ItemRequirementsTableService.INSTANCE.addRecordsToItemRequirementsTable(offsettedData);
    }

    private void extractWeaponParametersDataFromFile(RandomAccessFile file) {
        WeaponParametersTableService.INSTANCE.createWeaponParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.WEAPON_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.WEAPON_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        WeaponParametersTableService.INSTANCE.addRecordsToWeaponParametersTable(offsettedData);
    }

    private void extractArmorParametersDataFromFile(RandomAccessFile file) {
        ArmorParametersTableService.INSTANCE.createArmorParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.ARMOR_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.ARMOR_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        ArmorParametersTableService.INSTANCE.addRecordsToArmorParametersTable(offsettedData);
    }

    private void extractItemPricesDataFromFile(RandomAccessFile file) {
        ItemPriceParametersTableService.INSTANCE.createItemPriceParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.ITEM_PRICES);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.ITEM_PRICES);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        ItemPriceParametersTableService.INSTANCE.addRecordsToItemPriceParametersTable(offsettedData);
    }

    public void extractMerchantsDataFromFile(RandomAccessFile file) {
        extractMerchantInventoryDataFromFile(file);
        extractMerchantInventoryItemsDataFromFile(file);
    }

    private void extractMerchantInventoryDataFromFile(RandomAccessFile file) {
        MerchantInventoryTableService.INSTANCE.createMerchantInventoryTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.MERCHANT_INVENTORY);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.MERCHANT_INVENTORY);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        MerchantInventoryTableService.INSTANCE.addRecordsToMerchantInventoryTable(offsettedData);
    }

    private void extractMerchantInventoryItemsDataFromFile(RandomAccessFile file) {
        MerchantInventoryItemsTableService.INSTANCE.createMerchantInventoryItemsTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.MERCHANT_INVENTORY_ITEMS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.MERCHANT_INVENTORY_ITEMS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        MerchantInventoryItemsTableService.INSTANCE.addRecordsToMerchantInventoryItemsTable(offsettedData);
    }

    private List<Pair<byte[], Long>> readData(RandomAccessFile file, List<Pair<Integer, Integer>> dataOffsets, int dataLength) {
        List<Pair<byte[], Long>> result = new ArrayList<>();
        try {
            for (Pair<Integer, Integer> pair : dataOffsets) {
                file.seek(pair.getKey());
                byte[] buffer = new byte[dataLength];
                while (file.getFilePointer() < pair.getValue()) {
                    file.read(buffer);
                    long offset = file.getFilePointer() - dataLength;
                    result.add(new Pair<>(buffer.clone(), offset));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    public void recreateAllMaps() {
        // TODO check necessity and recreate tables if really needed
//        extractSkillsDataFromFile();
//        extractSpellsDataFromFile();
//        TableCreationUtils.createSpellSchoolNameTable();
    }

    private List<Pair<Long, Long>> getSpellSequencesOffsets(RandomAccessFile file) {
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.SPELL_PARAMETERS);
        int postfixLength = 8;
        boolean foundSequence = false;
        byte[] buffer = new byte[postfixLength];
        List<Pair<Long, Long>> offsets = new ArrayList<>();
        try {
            while (file.getFilePointer() < file.length()) {
                if (foundSequence) {
                    file.skipBytes(dataLength - postfixLength);
                }
                file.read(buffer);

                if (file.getFilePointer() >= file.length()) {
                    break;
                }

                if (!isSuit(buffer)) {
                    if (foundSequence) {
                        long endOfSequence = file.getFilePointer() - dataLength;
                        file.seek(endOfSequence);
                        offsets.get(offsets.size() - 1).setValue(endOfSequence);
                    } else {
                        file.seek(file.getFilePointer() - postfixLength);
                    }
                    file.seek(file.getFilePointer() + 1);

                    foundSequence = false;
                } else {
                    if (!foundSequence) {
                        long beginningOfSequence = file.getFilePointer() - dataLength;
                        offsets.add(new Pair<>(beginningOfSequence, 0L));
                    }
                    foundSequence = true;
                }

            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            file.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return offsets;
    }

    private boolean isSuit(byte[] buffer) {
        return buffer[0] == 0x0
                && buffer[1] == 0x0
                && buffer[2] == 0x0
                && buffer[3] == 0x0
                && buffer[4] == 0x64
                && buffer[5] == 0x0
                && buffer[6] == 0x0
                && buffer[7] == 0x0;
    }

    private static final class CreaturesObjectDecorator implements DTODecorator<CreaturesCommonParameterObject> {

        @Override
        public CreaturesCommonParameterObject decorateObject(CreaturesCommonParameterObject object) {
            Integer creatureId = object.creatureId;
            object.name = I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, String.valueOf(creatureId));
            return object;
        }
    }
}
