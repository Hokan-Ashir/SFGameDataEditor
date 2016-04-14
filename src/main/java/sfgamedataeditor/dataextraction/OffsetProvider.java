package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.IDataConstraint;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.datamapping.SpellRequirementTuple;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public enum OffsetProvider {
    INSTANCE;

    public static final int NUMBER_OF_ABILITY_SCHOOLS = 7;
    public static final int NUMBER_OF_ABILITY_SUBSCHOOLS = 3;
    public static final int NUMBER_OF_ABILITY_LEVELS = 20;
    private static final Logger LOGGER = Logger.getLogger(OffsetProvider.class);
    private static final int SKILL_SCHOOL_LEVEL_DATA_LENGTH = 0x2;
    private static final int SPELL_DATA_LENGTH = 76;
    private static final int SPELL_NUMBER_DATA_LENGTH = 0x2;

    private Map<Integer, List<Pair<Integer, Long>>> skillSchoolOffsetMap;
    private Map<Integer, List<Pair<Integer, Long>>> spellOffsetMap;
    private Map<SpellRequirementTuple, Set<Integer>> requirementTupleSetMap;

    public Map<Integer, List<Pair<Integer, Long>>> getSkillSchoolsOffsets() {
        if (skillSchoolOffsetMap != null) {
            return skillSchoolOffsetMap;
        }

        final List<IDataConstraint> constraints = getSkillRequirementsConstraints();
        IOperation operation = new IOperation() {
            @Override
            public void operation(byte[] buffer, RandomAccessFile file, int constraintsSize, int singleDataObjectSize, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
                long skillOffset = 0;
                try {
                    skillOffset = file.getFilePointer() - constraintsSize + singleDataObjectSize;
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                int skillType = buffer[0] & 0xFF;
                List<Pair<Integer, Long>> offsets;
                if (!resultMap.containsKey(skillType)) {
                    offsets = new ArrayList<>();
                } else {
                    offsets = resultMap.get(skillType);
                }

                offsets.add(new Pair<>(buffer[1] & 0xFF, skillOffset));
                resultMap.put(skillType, offsets);
            }
        };

        int skillDataBeginOffset = DataOffsetProvider.INSTANCE.getSkillDataBeginOffset();
        int skillDataEndOffset = DataOffsetProvider.INSTANCE.getSkillDataEndOffset();
        skillSchoolOffsetMap = extractDataFromFile(constraints, operation, skillDataBeginOffset, skillDataEndOffset, SKILL_SCHOOL_LEVEL_DATA_LENGTH);
        return skillSchoolOffsetMap;
    }

    private List<IDataConstraint> getSkillRequirementsConstraints() {
        List<IDataConstraint> constraints = new ArrayList<>();
        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value >= 0 && value <= NUMBER_OF_ABILITY_SCHOOLS;
            }
        });

        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value >= 0 && value <= NUMBER_OF_ABILITY_LEVELS;
            }
        });

        for (int i = 0; i < 7; ++i) {
            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return true;
                }
            });
        }

        return constraints;
    }

    public Map<Integer, List<Pair<Integer, Long>>> getSpellOffsets() {
        if (spellOffsetMap != null) {
            return spellOffsetMap;
        }

        final Map<Integer, Pair<String, List<String>>> spellMap = SpellMap.INSTANCE.getSpellMap();
        List<IDataConstraint> constraints = getSpellDataConstraints(spellMap);
        IOperation operation = new IOperation() {
            @Override
            public void operation(byte[] buffer, RandomAccessFile file, int constraintsSize, int singleDataObjectSize, Map<Integer, List<Pair<Integer, Long>>> resultMap) {
                long spellOffset = 0;
                try {
                    spellOffset = file.getFilePointer() - constraintsSize - singleDataObjectSize;
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                int spellType = buffer[0] & 0xFF;
                int spellLevel = buffer[4];
                List<Pair<Integer, Long>> offsets;
                if (!resultMap.containsKey(spellType)) {
                    offsets = new ArrayList<>();
                } else {
                    offsets = resultMap.get(spellType);
                }

                offsets.add(new Pair<>(spellLevel, spellOffset));
                resultMap.put(spellType, offsets);
                try {
                    file.seek(spellOffset + SPELL_DATA_LENGTH);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        };

        int spellDataBeginOffset = DataOffsetProvider.INSTANCE.getSpellDataBeginOffset();
        int spellDataEndOffset = DataOffsetProvider.INSTANCE.getSpellDataEndOffset();
        spellOffsetMap = extractDataFromFile(constraints, operation, spellDataBeginOffset, spellDataEndOffset, SPELL_NUMBER_DATA_LENGTH);
        return spellOffsetMap;
    }

    private Map<Integer, List<Pair<Integer, Long>>> extractDataFromFile(List<IDataConstraint> constraints, IOperation operation,
                                                                        int dataBeginOffset, int dataEndOffset, int singleDataObjectSize) {
        Map<Integer, List<Pair<Integer, Long>>> result = new HashMap<>();
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        try {
            file.seek(dataBeginOffset);
            byte[] buffer = new byte[constraints.size()];
            while (true) {
                boolean reachedSpellDataEnd = populateBufferWithData(constraints, dataEndOffset, file, buffer);
                if (reachedSpellDataEnd) {
                    break;
                } else {
                    operation.operation(buffer, file, constraints.size(), singleDataObjectSize, result);

                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    public Map<SpellRequirementTuple, Set<Integer>> getRequirementToSpellMap() {
        if (requirementTupleSetMap != null) {
            return requirementTupleSetMap;
        }

        int spellDataBeginOffset = DataOffsetProvider.INSTANCE.getSpellDataBeginOffset();
        int spellDataEndOffset = DataOffsetProvider.INSTANCE.getSpellDataEndOffset();
        final Map<Integer, Pair<String, List<String>>> spellMap = SpellMap.INSTANCE.getSpellMap();
        List<IDataConstraint> constraints = getSpellDataConstraints(spellMap);
        requirementTupleSetMap = new HashMap<>();
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        try {
            file.seek(spellDataBeginOffset);
            byte[] buffer = new byte[constraints.size()];
            while (true) {
                boolean reachedSpellDataEnd = populateBufferWithData(constraints, spellDataEndOffset, file, buffer);
                if (reachedSpellDataEnd) {
                    break;
                } else {
                    long spellOffset = 0;
                    try {
                        spellOffset = file.getFilePointer() - constraints.size() - SPELL_NUMBER_DATA_LENGTH;
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                    int spellType = buffer[0] & 0xFF;
                    SpellRequirementTuple requirementTuple = new SpellRequirementTuple(convertToInteger(buffer[2]),
                            convertToInteger(buffer[3]), convertToInteger(buffer[5]), convertToInteger(buffer[6]),
                            convertToInteger(buffer[8]), convertToInteger(buffer[9]));
                    Set<Integer> spellList;
                    if (!requirementTupleSetMap.containsKey(requirementTuple)) {
                        spellList = new TreeSet<>();
                    } else {
                        spellList = requirementTupleSetMap.get(requirementTuple);
                    }

                    spellList.add(spellType);
                    requirementTupleSetMap.put(requirementTuple, spellList);

                    try {
                        file.seek(spellOffset + SPELL_DATA_LENGTH);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return requirementTupleSetMap;
    }

    private boolean populateBufferWithData(List<IDataConstraint> constraints, int dataEndOffset, RandomAccessFile file, byte[] buffer) throws IOException {
        boolean reachedSpellDataEnd = false;
        for (int i = 0; i < constraints.size(); i++) {
            buffer[i] = file.readByte();
            if (constraints.get(i).isValid(buffer[i])) {
                continue;
            } else {
                file.seek(file.getFilePointer() - i + 1);
                i = -1;
            }

            if (file.getFilePointer() >= dataEndOffset) {
                reachedSpellDataEnd = true;
                break;
            }
        }

        return reachedSpellDataEnd;
    }

    private Integer convertToInteger(byte value) {
        if (value == 0) {
            return null;
        } else {
            return (int) value;
        }
    }

    private List<IDataConstraint> getSpellDataConstraints(final Map<Integer, Pair<String, List<String>>> spellMap) {
        List<IDataConstraint> constraints = new ArrayList<>();
        addSpellTypeConstraints(spellMap, constraints);
        addSpellRequirementConstraints(constraints);
        addZeroTrailConstraints(constraints);

        // TODO try to avoid this constraint of non-zero mana usage
        // this constraint needed for correct parsing, cause in cases
        // spell number 01 00
        // spell type   01 00
        // spell school 05
        // ...
        // all constraints works and instead of getting "spell type" at first place
        // you can get spell number, which is useless for spell data binding
        addNonZeroManaUsageConstraint(constraints);

        return constraints;
    }

    private void addNonZeroManaUsageConstraint(List<IDataConstraint> constraints) {
        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value != 0;
            }
        });
    }

    private void addZeroTrailConstraints(List<IDataConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value == 0;
                }
            });
        }
    }

    private void addSpellRequirementConstraints(List<IDataConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= OffsetProvider.NUMBER_OF_ABILITY_SCHOOLS && value >= 0;
                }
            });

            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= OffsetProvider.NUMBER_OF_ABILITY_SUBSCHOOLS && value >= 0;
                }
            });

            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= OffsetProvider.NUMBER_OF_ABILITY_LEVELS && value >= 0;
                }
            });
        }
    }

    private void addSpellTypeConstraints(final Map<Integer, Pair<String, List<String>>> spellMap,
                                         List<IDataConstraint> constraints) {
        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return spellMap.containsKey(value & 0xFF);
            }
        });

        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value == 0;
            }
        });
    }

    private interface IOperation {
        void operation(byte[] buffer, RandomAccessFile file, int constraintsSize, int singleDataObjectSize, Map<Integer, List<Pair<Integer, Long>>> resultMap);
    }
}
