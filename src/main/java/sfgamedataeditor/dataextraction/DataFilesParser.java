package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
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
import sfgamedataeditor.database.spells.school.names.SpellSchoolNameTableService;
import sfgamedataeditor.views.utility.Pair;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public enum DataFilesParser {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(DataFilesParser.class);

    public void extractAllDataFromFile(RandomAccessFile file) {
        SpellNameTableService.INSTANCE.createSpellNameTable();

        // not multithreaded, cause will be slower with additional threads - database is bottleneck
        List<TableCreationService> services = new ArrayList<TableCreationService>() {{
            add(SkillParametersTableService.INSTANCE);
            add(SpellParametersTableService.INSTANCE);
            add(CreatureParametersTableService.INSTANCE);
            add(CreatureCommonParametersTableService.INSTANCE);
            add(CreatureSpellTableService.INSTANCE);
            add(CreatureEquipmentTableService.INSTANCE);
            add(CreatureCorpseLootTableService.INSTANCE);
            add(CreatureBuildingsTableService.INSTANCE);
            add(CreatureResourcesTableService.INSTANCE);
            add(CreatureSkillTableService.INSTANCE);
            add(ItemEffectsTableService.INSTANCE);
            add(ItemSpellEffectsTableService.INSTANCE);
            add(ItemRequirementsTableService.INSTANCE);
            add(WeaponParametersTableService.INSTANCE);
            add(ArmorParametersTableService.INSTANCE);
            add(ItemPriceParametersTableService.INSTANCE);
            add(MerchantInventoryTableService.INSTANCE);
            add(MerchantInventoryItemsTableService.INSTANCE);
            add(BuildingsTableService.INSTANCE);
            add(BuildingsRequirementsTableService.INSTANCE);
        }};

        for (TableCreationService service : services) {
            service.createTable();

            DTOOffsetTypes dtoOffsetType = service.getDTOOffsetType();
            List<Pair<Integer, Integer>> skillOffsets = DataOffsetProvider.INSTANCE.getOffsets(dtoOffsetType);
            int dataLength = DataOffsetProvider.INSTANCE.getDataLength(dtoOffsetType);
            List<Pair<byte[], Long>> offsettedData = readData(file, skillOffsets, dataLength);
            service.addRecordsToTable(offsettedData);
        }

        SpellSchoolNameTableService.INSTANCE.createSpellSchoolNameTable();
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
}
