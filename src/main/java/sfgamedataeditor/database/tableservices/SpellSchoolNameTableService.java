package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.objects.SpellSchoolName;
import sfgamedataeditor.datamapping.Mappings;

import java.sql.SQLException;
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

    public List<SpellSchoolName> getAllSpellSchoolNames() {
        return CommonTableService.INSTANCE.getAllTableData(SpellSchoolName.class);
    }
}
