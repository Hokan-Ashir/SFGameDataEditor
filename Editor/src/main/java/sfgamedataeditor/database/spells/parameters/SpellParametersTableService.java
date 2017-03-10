package sfgamedataeditor.database.spells.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.*;

public enum SpellParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(SpellParametersObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(SpellParametersObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.SPELL_PARAMETERS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(SpellParametersTableService.class);

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
            Set<Requirements> requirements = getSpellRequirements(spellSchoolName);
            Dao<SpellParametersObject, Integer> dao = DaoManager.createDao(connectionSource, SpellParametersObject.class);
            Where<SpellParametersObject, Integer> where = dao.queryBuilder().where();
            where = decorateRequirementWhere(where, requirements);
            spellParameterObjects = where.query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        return spellParameterObjects;
    }

    private Where<SpellParametersObject, Integer> decorateRequirementWhere(Where<SpellParametersObject, Integer> where, Set<Requirements> spellRequirements) {
        for (Requirements spellRequirement : spellRequirements) {
            addPermutationWhere(where, spellRequirement);
        }
        where = where.or(spellRequirements.size());
        return where;
    }

    private Where<SpellParametersObject, Integer> addPermutationWhere(Where<SpellParametersObject, Integer> where,
                                                                      Requirements spellRequirements) {
        try {
            Where<SpellParametersObject, Integer> spellRequirementClass1Where =
                    where.eq("requirementClass1", spellRequirements.getRequirementClass(0))
                            .and().eq("requirementSubClass1", spellRequirements.getSubRequirementClass(0));
            Where<SpellParametersObject, Integer> spellRequirementClass2Where =
                    where.eq("requirementClass2", spellRequirements.getRequirementClass(1))
                            .and().eq("requirementSubClass2", spellRequirements.getSubRequirementClass(1));
            Where<SpellParametersObject, Integer> spellRequirementClass3Where =
                    where.eq("requirementClass3", spellRequirements.getRequirementClass(2))
                            .and().eq("requirementSubClass3", spellRequirements.getSubRequirementClass(2));
            where.and(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where);
            return where;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return where;
        }
    }

    private Set<Requirements> getSpellRequirements(String spellSchoolName) {
        String[] wholeSchoolNames = spellSchoolName.split(" & ");
        Requirements requirements = new Requirements();
        for (String wholeSchoolName : wholeSchoolNames) {
            String[] split = wholeSchoolName.split(" : ");
            String schoolName = split[0];
            String schoolNameKey = ViewTools.getKeyStringByPropertyValue(schoolName, I18NTypes.COMMON);
            Integer schoolNameId = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.SKILL_SCHOOL_MAPPING, schoolNameKey));

            if (split.length == 2) {
                String subSchoolName = split[1];
                String subSchoolNameKey = ViewTools.getKeyStringByPropertyValue(subSchoolName, I18NTypes.COMMON);
                Integer subSchoolNameId = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.SKILL_SUB_SCHOOL_MAPPING, subSchoolNameKey));
                requirements.addRequirementSubClass(subSchoolNameId);
            }

            requirements.addRequirementClass(schoolNameId);
        }

        return Requirements.getAllRequirements(requirements);
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

    public Set<String> getAllSpellSchoolNames() {
        List<SpellParametersObject> allTableData = CommonTableService.INSTANCE.getAllTableData(SpellParametersObject.class);
        Set<String> names = new TreeSet<>();
        for (SpellParametersObject parametersObject : allTableData) {
            String wholeName = getSpellSchoolName(parametersObject);
            names.add(wholeName);
        }

        return names;
    }

    public String getSpellSchoolName(SpellParametersObject parametersObject) {
        Set<String> innerNames = new TreeSet<>();
        Integer requirementClass1 = parametersObject.requirementClass1;
        Integer requirementSubClass1 = parametersObject.requirementSubClass1;
        String requirementSchoolName1 = getSchoolName(requirementClass1, requirementSubClass1);
        innerNames.add(requirementSchoolName1);

        Integer requirementClass2 = parametersObject.requirementClass2;
        Integer requirementSubClass2 = parametersObject.requirementSubClass2;
        String requirementSchoolName2 = getSchoolName(requirementClass2, requirementSubClass2);
        innerNames.add(requirementSchoolName2);

        Integer requirementClass3 = parametersObject.requirementClass3;
        Integer requirementSubClass3 = parametersObject.requirementSubClass3;
        String requirementSchoolName3 = getSchoolName(requirementClass3, requirementSubClass3);
        innerNames.add(requirementSchoolName3);

        if (innerNames.size() > 1) {
            innerNames.remove(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "other"));
        }

        Iterator<String> iterator = innerNames.iterator();
        String wholeName = iterator.next();
        while (iterator.hasNext()) {
            wholeName += " & " + iterator.next();
        }
        return wholeName;
    }

    private String getSchoolName(Integer requirementClass1, Integer requirementSubClass1) {
        String schoolType = ViewTools.getKeyStringByPropertyValue(String.valueOf(requirementClass1), I18NTypes.SKILL_SCHOOL_MAPPING);
        String skillSchoolName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, schoolType);
        String subSkillSchoolName = null;
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SUB_SCHOOL_MAPPING);
        for (String key : bundle.keySet()) {
            if (key.startsWith(schoolType)) {
                String value = bundle.getString(key);
                if (value.equals(String.valueOf(requirementSubClass1))) {
                    subSkillSchoolName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key);
                    break;
                }
            }
        }

        String schoolName = skillSchoolName;
        if (subSkillSchoolName != null) {
            schoolName += " : " + subSkillSchoolName;
        }

        return schoolName;
    }

    private interface WhereDecorator<T, ID> {
        Where<T, ID> decorateWhere(Where<T, ID> where);
    }

    public List<SpellParametersObject> getSpellParametersBySpellSchool(int spellTypeId, int spellLevel) {
        SpellParametersObject spellParameter = getSpellParameterBySpellIdAndLevel(spellTypeId, spellLevel);
        String spellSchoolName = SpellParametersTableService.INSTANCE.getSpellSchoolName(spellParameter);
        return getSpells(spellSchoolName);
    }
}
