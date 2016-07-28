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

        Operation operation = new Operation<Map<Integer, List<Pair<Integer, Long>>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
                TableCreationUtils.addRecordToSkillParametersTable(buffer, offset);
                int skillType = buffer[0] & 0xFF;
                List<Pair<Integer, Long>> offsets;
                if (!resultMap.containsKey(skillType)) {
                    offsets = new ArrayList<>();
                } else {
                    offsets = resultMap.get(skillType);
                }

                offsets.add(new Pair<>(buffer[1] & 0xFF, offset));
                resultMap.put(skillType, offsets);
            }
        };

        List<Pair<Integer, Integer>> skillOffsets = DataOffsetProvider.INSTANCE.getSkillOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSkillDataLength();
        readData(skillOffsets, dataLength, skillSchoolOffsetMap, operation);
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

        Operation operation = new Operation<Map<Integer, List<Pair<Integer, Long>>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
                TableCreationUtils.addRecordToSpellParametersTable(buffer, offset);
                int spellType = buffer[2] & 0xFF;
                // TODO triple spell requirements may not have same level, nor it can be
                // placed in first requirement tuple
                int spellLevel = buffer[6] & 0xFF;
                List<Pair<Integer, Long>> offsets;
                if (!resultMap.containsKey(spellType)) {
                    offsets = new ArrayList<>();
                } else {
                    offsets = resultMap.get(spellType);
                }

                offsets.add(new Pair<>(spellLevel, offset));
                resultMap.put(spellType, offsets);
            }
        };

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        readData(offsets, dataLength, spellOffsetMap, operation);
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

        Operation operation = new Operation<Map<String, Set<Integer>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<String, Set<Integer>> resultMap) {
                int spellType = buffer[2] & 0xFF;
                List<String> spellSchoolNames = createSpellSchoolNames(buffer);
                for (String spellSchoolName : spellSchoolNames) {
                    Set<Integer> spellList;
                    if (!resultMap.containsKey(spellSchoolName)) {
                        spellList = new TreeSet<>();
                    } else {
                        spellList = resultMap.get(spellSchoolName);
                    }

                    spellList.add(spellType);
                    resultMap.put(spellSchoolName, spellList);
                }
            }
        };

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        readData(offsets, dataLength, spellSchoolsToSpellsMap, operation);
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

    private <T> void readData(List<Pair<Integer, Integer>> dataOffsets, int dataLength, T data, Operation<T> operation) {
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        try {
            for (Pair<Integer, Integer> pair : dataOffsets) {
                file.seek(pair.getKey());
                byte[] buffer = new byte[dataLength];
                while (file.getFilePointer() < pair.getValue()) {
                    file.read(buffer);
                    long offset = file.getFilePointer() - dataLength;
                    operation.process(buffer, offset, data);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
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

    private interface Operation<T> {
        void process(byte[] buffer, long offset, T resultMap);
    }
}
