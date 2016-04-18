package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.datamapping.SpellRequirementTuple;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public enum OffsetProvider {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(OffsetProvider.class);

    private Map<Integer, List<Pair<Integer, Long>>> skillSchoolOffsetMap;
    private Map<Integer, List<Pair<Integer, Long>>> spellOffsetMap;
    private Map<SpellRequirementTuple, Set<Integer>> requirementTupleSetMap;

    public Map<Integer, List<Pair<Integer, Long>>> getSkillSchoolsOffsets() {
        if (skillSchoolOffsetMap != null) {
            return skillSchoolOffsetMap;
        }

        skillSchoolOffsetMap = new HashMap<>();

        Operation operation = new Operation<Map<Integer, List<Pair<Integer, Long>>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
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
        return skillSchoolOffsetMap;
    }

    public Map<Integer, List<Pair<Integer, Long>>> getSpellOffsets() {
        if (spellOffsetMap != null) {
            return spellOffsetMap;
        }

        spellOffsetMap = new HashMap<>();

        Operation operation = new Operation<Map<Integer, List<Pair<Integer, Long>>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
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
        return spellOffsetMap;
    }

    public Map<SpellRequirementTuple, Set<Integer>> getRequirementToSpellMap() {
        if (requirementTupleSetMap != null) {
            return requirementTupleSetMap;
        }

        requirementTupleSetMap = new HashMap<>();

        Operation operation = new Operation<Map<SpellRequirementTuple, Set<Integer>>>() {
            @Override
            public void process(byte[] buffer, long offset, Map<SpellRequirementTuple, Set<Integer>> resultMap) {
                int spellType = buffer[2] & 0xFF;
                SpellRequirementTuple requirementTuple = new SpellRequirementTuple(convertToInteger(buffer[4]),
                        convertToInteger(buffer[5]), convertToInteger(buffer[7]), convertToInteger(buffer[8]),
                        convertToInteger(buffer[10]), convertToInteger(buffer[11]));
                Set<Integer> spellList;
                if (!requirementTupleSetMap.containsKey(requirementTuple)) {
                    spellList = new TreeSet<>();
                } else {
                    spellList = requirementTupleSetMap.get(requirementTuple);
                }

                spellList.add(spellType);
                requirementTupleSetMap.put(requirementTuple, spellList);
            }
        };

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        readData(offsets, dataLength, requirementTupleSetMap, operation);

        return requirementTupleSetMap;
    }

    private Integer convertToInteger(byte value) {
        if (value == 0) {
            return null;
        } else {
            return (int) value;
        }
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

    private interface Operation<T> {
        void process(byte[] buffer, long offset, T resultMap);
    }
}
