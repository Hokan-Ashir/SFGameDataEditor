package sfgamedataeditor.database.spellparameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.spellschoolname.SpellSchoolNameObject;
import sfgamedataeditor.database.spellschoolname.SpellSchoolNameTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum SpellParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SpellParametersTableService.class);

    public void createSpellParametersTable() {
        CommonTableService.INSTANCE.recreateTable(SpellParametersObject.class);
    }

    public void addRecordsToSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(SpellParametersObject.class, offsettedData);
    }

    public Set<Integer> getSpellLevels(int spellId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        try {
            Dao<SpellParametersObject, Integer> dao = DaoManager.createDao(connectionSource, SpellParametersObject.class);
            List<SpellParametersObject> parameters = dao.queryBuilder().selectColumns("requirementLevel1", "requirementLevel2", "requirementLevel3").where().eq("spellNameId", spellId).query();
            Set<Integer> spellLevels = new TreeSet<>();
            for (SpellParametersObject parameter : parameters) {
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

    public List<SpellParametersObject> getSpells(String spellSchoolName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        List<SpellParametersObject> spellParameterObjects;
        try {
            SpellSchoolNameObject schoolName = SpellSchoolNameTableService.INSTANCE.getSpellSchoolId(spellSchoolName);

            Integer spellSchoolId = schoolName.spellSchoolId;
            int spellSchoolClass = spellSchoolId / 10;
            int spellSchoolSubClass = spellSchoolId % 10;

            Dao<SpellParametersObject, Integer> dao = DaoManager.createDao(connectionSource, SpellParametersObject.class);
            Where<SpellParametersObject, Integer> where = dao.queryBuilder().where();
            Where<SpellParametersObject, Integer> spellRequirementClass1Where =
                    where.eq("requirementClass1", spellSchoolClass)
                            .and().eq("requirementSubClass1", spellSchoolSubClass);
            Where<SpellParametersObject, Integer> spellRequirementClass2Where =
                    where.eq("requirementClass2", spellSchoolClass)
                            .and().eq("requirementSubClass2", spellSchoolSubClass);
            Where<SpellParametersObject, Integer> spellRequirementClass3Where =
                    where.eq("requirementClass3", spellSchoolClass)
                            .and().eq("requirementSubClass3", spellSchoolSubClass);
            if (spellSchoolClass == 0 && spellSchoolSubClass == 0) {
                spellParameterObjects = where.and(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            } else {
                spellParameterObjects = where.or(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        return spellParameterObjects;
    }

    public SpellParametersObject getSpellParameterBySpellIdAndLevel(final int selectedSpellId, final int selectedLevel) {
        return getSpellParameters(new WhereDecorator<SpellParametersObject, Integer>() {
            @Override
            public Where<SpellParametersObject, Integer> decorateWhere(Where<SpellParametersObject, Integer> where) {
                try {
                Where<SpellParametersObject, Integer> spellNameId = where.eq("spellNameId", selectedSpellId);
                Where<SpellParametersObject, Integer> or;
                    or = where.or(where.eq("requirementLevel1", selectedLevel),
                            where.eq("requirementLevel2", selectedLevel),
                            where.eq("requirementLevel3", selectedLevel));
                    where.and(spellNameId, or);
                    return where;
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }

                return null;
            }
        });
    }

    public List<SpellParametersObject> getAllSpellParameters() {
        return CommonTableService.INSTANCE.getAllTableData(SpellParametersObject.class);
    }

    public SpellParametersObject getSpellParametersBySpellType(final int spellTypeId) {
        return getSpellParameters(new WhereDecorator<SpellParametersObject, Integer>() {
            @Override
            public Where<SpellParametersObject, Integer> decorateWhere(Where<SpellParametersObject, Integer> where) {
                try {
                    return where.eq("spellNameId", spellTypeId);
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                    return null;
                }
            }
        });
    }

    public SpellParametersObject getSpellParametersBySpellNumber(final int spellNumber) {
        return getSpellParameters(new WhereDecorator<SpellParametersObject, Integer>() {
            @Override
            public Where<SpellParametersObject, Integer> decorateWhere(Where<SpellParametersObject, Integer> where) {
                try {
                    return where.eq("spellNumber", spellNumber);
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                    return null;
                }
            }
        });
    }

    private SpellParametersObject getSpellParameters(WhereDecorator<SpellParametersObject, Integer> decorator) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SpellParametersObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return new SpellParametersObject();
        }

        try {
            Where<SpellParametersObject, Integer> where = dao.queryBuilder().where();
            where = decorator.decorateWhere(where);
            return where.query().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return new SpellParametersObject();
        }
    }

    private interface WhereDecorator<T, ID> {
        Where<T, ID> decorateWhere(Where<T, ID> where);
    }

    public List<SpellParametersObject> getSpellParametersBySpellSchool(int spellTypeId, int spellLevel) {
        SpellParametersObject spellParameter = getSpellParameterBySpellIdAndLevel(spellTypeId, spellLevel);
        String spellSchoolName = SpellSchoolNameTableService.INSTANCE.getSpellSchoolName(spellParameter);
        return getSpells(spellSchoolName);
    }
}
