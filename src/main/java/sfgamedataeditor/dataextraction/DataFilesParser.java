package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.DTODecorator;
import sfgamedataeditor.database.creatures.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.skillparameters.SkillParametersTableService;
import sfgamedataeditor.database.spellname.SpellNameTableService;
import sfgamedataeditor.database.spellparameters.SpellParametersTableService;
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
        CreatureParametersTableService.INSTANCE.createCreatureParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureParametersTableService.INSTANCE.addRecordsToCreatureParametersTable(offsettedData);
    }

    public void extractCommonCreatureParametersDataFromFile(RandomAccessFile file) {
        CreatureCommonParametersTableService.INSTANCE.createCommonCreatureParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getOffsets(DTOOffsetTypes.CREATURE_COMMON_PARAMETERS);
        int dataLength = DataOffsetProvider.INSTANCE.getDataLength(DTOOffsetTypes.CREATURE_COMMON_PARAMETERS);
        List<Pair<byte[], Long>> offsettedData = readData(file, offsets, dataLength);
        CreatureCommonParametersTableService.INSTANCE.addRecordsToCommonCreatureParametersTable(offsettedData, new CreaturesObjectDecorator());
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
