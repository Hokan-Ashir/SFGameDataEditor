package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.IDataConstraint;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ObjectToOffsetExtractor {

    public static final int NUMBER_OF_ABILITY_SCHOOLS = 7;
    public static final int NUMBER_OF_ABILITY_SUBSCHOOLS = 3;
    public static final int NUMBER_OF_ABILITY_LEVELS = 20;
    private static final Logger LOGGER = Logger.getLogger(ObjectToOffsetExtractor.class);
    private static final int SKILL_DATA_BEGIN_OFFSET = 0x03F85FD4;
    private static final int SKILL_DATA_END_OFFSET = 0x03F864BF;
    private static final int SKILL_SCHOOL_LEVEL_DATA_LENGTH = 0x2;

    private ObjectToOffsetExtractor() {

    }

    public static Map<Integer, List<Pair<Integer, Long>>> getSkillSchoolsOffsets() {
        Map<Integer, List<Pair<Integer, Long>>> result = new HashMap<>();
        RandomAccessFile file = FilesContainer.getModificationFile();

        List<IDataConstraint> constraints = getSkillRequirementsConstraints();
        try {
            file.seek(SKILL_DATA_BEGIN_OFFSET);
            boolean reachedSkillDataEnd = false;
            byte[] buffer = new byte[constraints.size()];
            while (true) {
                for (int i = 0; i < constraints.size(); i++) {
                    buffer[i] = file.readByte();
                    if (constraints.get(i).isValid(buffer[i])) {
                        continue;
                    } else {
                        file.seek(file.getFilePointer() - i + 1);
                        i = -1;
                    }

                    if (file.getFilePointer() >= SKILL_DATA_END_OFFSET) {
                        reachedSkillDataEnd = true;
                        break;
                    }
                }

                if (reachedSkillDataEnd) {
                    break;
                } else {
                    long skillOffset = file.getFilePointer() - constraints.size() + SKILL_SCHOOL_LEVEL_DATA_LENGTH;
                    int skillType = buffer[0] & 0xFF;
                    List<Pair<Integer, Long>> offsets;
                    if (!result.containsKey(skillType)) {
                        offsets = new ArrayList<>();
                    } else {
                        offsets = result.get(skillType);
                    }

                    offsets.add(new Pair<>(buffer[1] & 0xFF, skillOffset));
                    result.put(skillType, offsets);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    private static List<IDataConstraint> getSkillRequirementsConstraints() {
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
}
