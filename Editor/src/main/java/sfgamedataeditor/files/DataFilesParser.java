package sfgamedataeditor.files;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsTableService;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsTableService;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.ObjectDataMappingService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootTableService;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.herospells.HeroSpellTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsTableService;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.database.file.storage.FileStorageObject;
import sfgamedataeditor.database.file.storage.FileStorageService;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.effects.ItemEffectsTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.database.merchants.inventory.MerchantInventoryTableService;
import sfgamedataeditor.database.merchants.items.MerchantInventoryItemsTableService;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootTableService;
import sfgamedataeditor.database.player.level.stats.PlayerLevelStatsTableService;
import sfgamedataeditor.database.skill.parameters.SkillParametersTableService;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.utility.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public enum DataFilesParser {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(DataFilesParser.class);
    private final List<TableCreationService> services = new ArrayList<TableCreationService>() {{
        add(TextTableService.INSTANCE);
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
        add(HeroSpellTableService.INSTANCE);
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
        add(BuildingsArmyRequirementsTableService.INSTANCE);
        add(ChestCorpseLootTableService.INSTANCE);
        add(PlayerLevelStatsTableService.INSTANCE);
    }};

    public void extractAllDataFromFile(RandomAccessFile file) {
        SpellNameTableService.INSTANCE.createSpellNameTable();

        // not multithreaded, cause will be slower with additional threads - database is bottleneck
        for (TableCreationService service : services) {
            service.createTable();

            Pair<Integer, Integer> offsetInterval = service.getOffsetInterval();
            int dataLength = service.getDataLength();
            List<Pair<byte[], Long>> offsettedData = readData(file, offsetInterval, dataLength);
            service.addRecordsToTable(offsettedData);
        }
    }

    public void reloadLocalizedTexts() {
        RandomAccessFile file = FileUtils.createTemporaryModificationFile();
        TextTableService service = TextTableService.INSTANCE;
        service.createTable();
        Pair<Integer, Integer> offsetInterval = service.getOffsetInterval();
        int dataLength = service.getDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(file, offsetInterval, dataLength);
        service.addRecordsToTable(offsettedData);
    }

    private List<Pair<byte[], Long>> readData(RandomAccessFile file, Pair<Integer, Integer> offsetInterval, int dataLength) {
        List<Pair<byte[], Long>> result = new ArrayList<>();
        try {
            file.seek(offsetInterval.getKey());
            byte[] buffer = new byte[dataLength];
            while (file.getFilePointer() < offsetInterval.getValue()) {
                file.read(buffer);
                long offset = file.getFilePointer() - dataLength;
                result.add(new Pair<>(buffer.clone(), offset));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    public void dropDatabaseChangesIntoModificationFile() {
        FileStorageObject fileStorage = FileStorageService.INSTANCE.getFileStorage();
        String[] split = fileStorage.pathToGameDataCff.split("/");
        String originalFileName = split[split.length - 1];
        String originalFileDirectory = fileStorage.pathToGameDataCff.replaceAll(originalFileName, "");
        String modificationFileName = originalFileName + FileUtils.MOD_FILE_EXTENSION;

        Path originalFilePath = Paths.get(originalFileDirectory + originalFileName);
        Path modificationFilePath = Paths.get(originalFileDirectory + modificationFileName);
        try {
            Files.copy(originalFilePath, modificationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        RandomAccessFile file;
        try {
            file = new RandomAccessFile(originalFileDirectory + modificationFileName, "rw");
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        for (TableCreationService service : services) {
            List<? extends OffsetableObject> allTableData = CommonTableService.INSTANCE.getAllTableData(service.getDTOClass());
            for (OffsetableObject offsetableObject : allTableData) {
                Long offset = offsetableObject.getOffset();
                try {
                    file.seek(offset);
                    byte[] bytes = ObjectDataMappingService.INSTANCE.serializeObject(offsetableObject);
                    file.write(bytes);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

        try {
            file.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
