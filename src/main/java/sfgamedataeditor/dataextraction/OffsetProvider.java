package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.datamapping.Mappings;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public enum OffsetProvider {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(OffsetProvider.class);

    private Set<String> spellSchoolsToSpellsMap;

    public void extractSkillsDataFromFile() {
        TableCreationUtils.createSkillNameTable();
        TableCreationUtils.createSkillParametersTable();

        List<Pair<Integer, Integer>> skillOffsets = DataOffsetProvider.INSTANCE.getSkillOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSkillDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(skillOffsets, dataLength);
        TableCreationUtils.addRecordsToSkillParametersTable(offsettedData);
    }

    public void extractSpellsDataFromFile() {
        TableCreationUtils.createSpellNameTable();
        TableCreationUtils.createSpellParametersTable();

        List<Pair<Integer, Integer>> offsets = DataOffsetProvider.INSTANCE.getSpellOffsets();
        int dataLength = DataOffsetProvider.INSTANCE.getSpellDataLength();
        List<Pair<byte[], Long>> offsettedData = readData(offsets, dataLength);
        TableCreationUtils.addRecordsToSpellParametersTable(offsettedData);
    }

    public Set<String> getSpellSchoolsNames() {
        if (spellSchoolsToSpellsMap != null) {
            return spellSchoolsToSpellsMap;
        }

        spellSchoolsToSpellsMap = createSpellSchoolsNames();
        return spellSchoolsToSpellsMap;
    }

    private Set<String> createSpellSchoolsNames() {
        Set<String> spellSchoolsNames = new HashSet<>();
        List<SpellParameters> allSpellParameters = TableCreationUtils.getAllSpellParameters();
        for (SpellParameters allSpellParameter : allSpellParameters) {
            extractSpellSchoolNamesFromSpell(allSpellParameter, spellSchoolsNames);
        }

        return spellSchoolsNames;
    }

    private void extractSpellSchoolNamesFromSpell(SpellParameters spellParameter, Set<String> spellSchoolsNames) {
        int schoolRequirement1 = spellParameter.requirementClass1;
        int subSchoolRequirement1 = spellParameter.requirementSubClass1;
        int schoolRequirement2 = spellParameter.requirementClass2;
        int subSchoolRequirement2 = spellParameter.requirementSubClass2;
        int schoolRequirement3 = spellParameter.requirementClass3;
        int subSchoolRequirement3 = spellParameter.requirementSubClass3;

        addSchoolName(schoolRequirement1, subSchoolRequirement1, spellSchoolsNames);
        addSchoolName(schoolRequirement2, subSchoolRequirement2, spellSchoolsNames);
        addSchoolName(schoolRequirement3, subSchoolRequirement3, spellSchoolsNames);
    }

    private void addSchoolName(int schoolRequirement, int subSchoolRequirement, Set<String> spellSchoolsNames) {
        int schoolId = schoolRequirement * 10 + subSchoolRequirement;
        if (!Mappings.INSTANCE.SPELL_SCHOOL_MAP.containsKey(schoolId)) {
            return;
        }

        spellSchoolsNames.add(Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(schoolId));
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
                    result.add(new Pair<>(buffer.clone(), offset));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }

    public void recreateAllMaps() {
        extractSkillsDataFromFile();
        extractSpellsDataFromFile();
        if (spellSchoolsToSpellsMap != null) {
            createSpellSchoolsNames();
        }
    }
}
