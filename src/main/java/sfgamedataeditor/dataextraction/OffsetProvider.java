package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.datamapping.Mappings;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public enum OffsetProvider {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(OffsetProvider.class);

    private Map<Integer, List<Pair<Integer, Long>>> skillSchoolOffsetMap;
    private Map<Integer, List<Pair<Integer, Long>>> spellOffsetMap;
    private Map<String, Set<Integer>> spellSchoolsToSpellsMap;

    public Map<Integer, List<Pair<Integer, Long>>> getSkillSchoolsOffsets() {
        if (skillSchoolOffsetMap != null) {
            return skillSchoolOffsetMap;
        }

        createSkillOffsetMap();
        return skillSchoolOffsetMap;
    }

    private void createSkillOffsetMap() {
        TableCreationUtils.createSkillNameTable();
        TableCreationUtils.createSkillParametersTable();
        skillSchoolOffsetMap = new HashMap<>();

        List<Pair<Integer, Integer>> skillOffsets = DataOffsetProvider.INSTANCE.getSkillOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSkillDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(skillOffsets, dataLength);
        TableCreationUtils.addRecordsToSkillParametersTable(offsettedData);
    }

    public Map<Integer, List<Pair<Integer, Long>>> getSpellOffsets() {
        if (spellOffsetMap != null) {
            return spellOffsetMap;
        }

        createSpellOffsetMap();
        return spellOffsetMap;
    }

    private void createSpellOffsetMap() {
        TableCreationUtils.createSpellNameTable();
        TableCreationUtils.createSpellParametersTable();
        spellOffsetMap = new HashMap<>();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(offsets, dataLength);
        TableCreationUtils.addRecordsToSpellParametersTable(offsettedData);
    }

    public Map<String, Set<Integer>> getSpellSchoolsToSpellsMap() {
        if (spellSchoolsToSpellsMap != null) {
            return spellSchoolsToSpellsMap;
        }

        createSpellSchoolsToSpellsMap();

        return spellSchoolsToSpellsMap;
    }

    private void createSpellSchoolsToSpellsMap() {
        spellSchoolsToSpellsMap = new HashMap<>();
        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(offsets, dataLength);
        // TODO deal with schoolNames
    }

    private List<String> createSpellSchoolNames(byte[] buffer) {
        int schoolRequirement1 = buffer[4] & 0xFF;
        int subSchoolRequirement1 = buffer[5] & 0xFF;
        int schoolRequirement2 = buffer[7] & 0xFF;
        int subSchoolRequirement2 = buffer[8] & 0xFF;
        int schoolRequirement3 = buffer[10] & 0xFF;
        int subSchoolRequirement3 = buffer[11] & 0xFF;

        List<String> result = new ArrayList<>();
        addSchoolName(schoolRequirement1, subSchoolRequirement1, result);
        addSchoolName(schoolRequirement2, subSchoolRequirement2, result);
        addSchoolName(schoolRequirement3, subSchoolRequirement3, result);

        return result;
    }

    private void addSchoolName(int schoolRequirement, int subSchoolRequirement, List<String> result) {
        int school1 = schoolRequirement * 10 + subSchoolRequirement;
        if (!Mappings.INSTANCE.SPELL_SCHOOL_MAP.containsKey(school1)) {
            return;
        }

        result.add(Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(school1));
    }

    private List<Pair<byte[], Long>> readData(List<Pair<Integer, Integer>> dataOffsets, int dataLength) {
        List<Pair<byte[], Long>> result = new ArrayList<>();
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        try {
            for (Pair<Integer, Integer> pair : dataOffsets) {
                file.seek(pair.getKey());
                byte[] buffer = new byte[dataLength];
                while (file.getFilePointer() < pair.getValue()) {
                    file.read(buffer);
                    long offset = file.getFilePointer() - dataLength;
                    result.add(new Pair<>(buffer, offset));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    public void recreateAllMaps() {
        if (spellOffsetMap != null) {
            createSpellOffsetMap();
        }

        if (skillSchoolOffsetMap != null) {
            createSkillOffsetMap();
        }

        if (spellSchoolsToSpellsMap != null) {
            createSpellSchoolsToSpellsMap();
        }
    }
}
