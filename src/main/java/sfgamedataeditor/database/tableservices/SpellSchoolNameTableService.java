package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.objects.SpellSchoolName;
import sfgamedataeditor.datamapping.Mappings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public enum  SpellSchoolNameTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SpellSchoolNameTableService.class);

    public void createSpellSchoolNameTable() {
        CommonTableService.INSTANCE.recreateTable(SpellSchoolName.class);
        fillSpellSchoolTable();
    }

    private void fillSpellSchoolTable() {
        final Set<String> spellSchoolsNames = createSpellSchoolsNames();

        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellSchoolName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellSchoolName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (String spellSchoolName : spellSchoolsNames) {
                        dao.create(new SpellSchoolName(spellSchoolName));
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private Set<String> createSpellSchoolsNames() {
        Set<String> spellSchoolsNames = new HashSet<>();
        List<SpellParameters> allSpellParameters = SpellParametersTableService.INSTANCE.getAllSpellParameters();
        for (SpellParameters allSpellParameter : allSpellParameters) {
            extractSpellSchoolNamesFromSpell(allSpellParameter, spellSchoolsNames);
        }

        return spellSchoolsNames;
    }

    private void extractSpellSchoolNamesFromSpell(SpellParameters spellParameter, Set<String> spellSchoolsNames) {
        List<String> possibleNonZeroSchoolNames = getPossibleNonZeroSchoolNames(spellParameter);
        if (!possibleNonZeroSchoolNames.isEmpty()) {
            spellSchoolsNames.addAll(possibleNonZeroSchoolNames);
        } else {
            // add spell school for "Other" spells, that is which have only zeros in school/subSchool requirement fields
            spellSchoolsNames.add(Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(0));
        }
    }

    private List<String> getPossibleNonZeroSchoolNames(SpellParameters spellParameter) {
        int schoolRequirement1 = spellParameter.requirementClass1;
        int subSchoolRequirement1 = spellParameter.requirementSubClass1;
        int schoolRequirement2 = spellParameter.requirementClass2;
        int subSchoolRequirement2 = spellParameter.requirementSubClass2;
        int schoolRequirement3 = spellParameter.requirementClass3;
        int subSchoolRequirement3 = spellParameter.requirementSubClass3;

        List<String> result = new ArrayList<>();
        addNonZeroSchoolName(schoolRequirement1, subSchoolRequirement1, result);
        addNonZeroSchoolName(schoolRequirement2, subSchoolRequirement2, result);
        addNonZeroSchoolName(schoolRequirement3, subSchoolRequirement3, result);

        return result;
    }

    private void addNonZeroSchoolName(int schoolRequirement, int subSchoolRequirement, List<String> result) {
        int schoolId = schoolRequirement * 10 + subSchoolRequirement;
        if (schoolId == 0) {
            return;
        }

        result.add(Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(schoolId));
    }

    public List<SpellSchoolName> getAllSpellSchoolNames() {
        return CommonTableService.INSTANCE.getAllTableData(SpellSchoolName.class);
    }
}
