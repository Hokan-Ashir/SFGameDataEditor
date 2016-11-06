package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.objects.SpellSchoolName;
import sfgamedataeditor.databind.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum SpellParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SpellParametersTableService.class);

    public void createSpellParametersTable() {
        CommonTableService.INSTANCE.recreateTable(SpellParameters.class);
    }

    public void addRecordsToSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(SpellParameters.class, offsettedData);
    }

    public Set<Integer> getSpellLevels(int spellId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        try {
            Dao<SpellParameters, Integer> dao = DaoManager.createDao(connectionSource, SpellParameters.class);
            List<SpellParameters> parameters = dao.queryBuilder().selectColumns("requirementLevel1", "requirementLevel2", "requirementLevel3").where().eq("spellNameId", spellId).query();
            Set<Integer> spellLevels = new TreeSet<>();
            for (SpellParameters parameter : parameters) {
                Integer requirementLevel1 = parameter.requirementLevel1;
                Integer requirementLevel2 = parameter.requirementLevel2;
                Integer requirementLevel3 = parameter.requirementLevel3;
                if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
                    if (requirementLevel1 != 0) {
                        spellLevels.add(requirementLevel1);
                    }

                    if (requirementLevel2 != 0) {
                        spellLevels.add(requirementLevel2);
                    }

                    if (requirementLevel3 != 0) {
                        spellLevels.add(requirementLevel3);
                    }
                } else {
                    spellLevels.add(0);
                }
            }

            return spellLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public List<SpellParameters> getSpells(String spellSchoolName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        List<SpellParameters> spellParameters;
        try {
            SpellSchoolName schoolName = SpellSchoolNameTableService.INSTANCE.getSpellSchoolId(spellSchoolName);

            Integer spellSchoolId = schoolName.spellSchoolId;
            int spellSchoolClass = spellSchoolId / 10;
            int spellSchoolSubClass = spellSchoolId % 10;

            Dao<SpellParameters, Integer> dao = DaoManager.createDao(connectionSource, SpellParameters.class);
            Where<SpellParameters, Integer> where = dao.queryBuilder().where();
            Where<SpellParameters, Integer> spellRequirementClass1Where =
                    where.eq("requirementClass1", spellSchoolClass)
                            .and().eq("requirementSubClass1", spellSchoolSubClass);
            Where<SpellParameters, Integer> spellRequirementClass2Where =
                    where.eq("requirementClass2", spellSchoolClass)
                            .and().eq("requirementSubClass2", spellSchoolSubClass);
            Where<SpellParameters, Integer> spellRequirementClass3Where =
                    where.eq("requirementClass3", spellSchoolClass)
                            .and().eq("requirementSubClass3", spellSchoolSubClass);
            if (spellSchoolClass == 0 && spellSchoolSubClass == 0) {
                spellParameters = where.and(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            } else {
                spellParameters = where.or(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        return spellParameters;
    }

    public SpellParameters getSpellParameter(int selectedSpellId, int selectedLevel) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SpellParameters, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return new SpellParameters();
        }

        try {
            Where<SpellParameters, Integer> where = dao.queryBuilder().where();
            Where<SpellParameters, Integer> spellNameId = where.eq("spellNameId", selectedSpellId);
            Where<SpellParameters, Integer> or = where.or(where.eq("requirementLevel1", selectedLevel),
                    where.eq("requirementLevel2", selectedLevel),
                    where.eq("requirementLevel3", selectedLevel));
            where.and(spellNameId, or);
            return where.query().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return new SpellParameters();
        }
    }

    public List<SpellParameters> getAllSpellParameters() {
        return CommonTableService.INSTANCE.getAllTableData(SpellParameters.class);
    }

    public SpellParameters getSpellParameters(int spellTypeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SpellParameters, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            return dao.queryBuilder().where().eq("spellNameId", spellTypeId).query().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
