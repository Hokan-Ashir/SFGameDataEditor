package sfgamedataeditor.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.*;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.utils.I18N;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public final class TableCreationUtils {

    private static final Logger LOGGER = Logger.getLogger(TableCreationUtils.class);

    private TableCreationUtils() {
    }

    public static void createSpellNameTable() {
        recreateTable(SpellName.class);
        fillSpellNameTableWithPredefinedNames();
    }

    private static void fillSpellNameTableWithPredefinedNames() {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final List<SpellName> spellNames = new ArrayList<>();
//        TODO make inner map, not static for all app instance, cause we need to instantiate it only once
        for (Map.Entry<Integer, String> integerStringEntry : SpellMap.SPELLMAP.entrySet()) {
            String i18nName = I18N.INSTANCE.getMessage(integerStringEntry.getValue() + "." + SpellMap.NAME_ATTRIBUTE);
            SpellName spellName = new SpellName(integerStringEntry.getKey(), i18nName);
            spellNames.add(spellName);
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (SpellName spellName : spellNames) {
                        dao.create(spellName);
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

    public static void createSkillNameTable() {
        recreateTable(SkillName.class);

        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SkillName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final Map<Integer, String> skillTypeToNameMap = new HashMap<Integer, String>() {{
            put(1, I18N.INSTANCE.getMessage("lightCombatArts"));
            put(2, I18N.INSTANCE.getMessage("heavyCombatArts"));
            put(3, I18N.INSTANCE.getMessage("archery"));
            put(4, I18N.INSTANCE.getMessage("whiteMagic"));
            put(5, I18N.INSTANCE.getMessage("elementalMagic"));
            put(6, I18N.INSTANCE.getMessage("mindMagic"));
            put(7, I18N.INSTANCE.getMessage("blackMagic"));
        }};

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Map.Entry<Integer, String> integerStringEntry : skillTypeToNameMap.entrySet()) {
                        SkillName spellName = new SkillName(integerStringEntry.getKey(), integerStringEntry.getValue());
                        dao.create(spellName);
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

    public static <T extends OffsetableObject> void updateObject(OffsetableObject objectToSave, Class<T> tClass) {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            Dao<T, Integer> dao = DaoManager.createDao(connectionSource, tClass);
            dao.update((T) objectToSave);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static Set<Integer> getSpellLevels(int spellId) {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            Dao<SpellParameters, Integer> dao = DaoManager.createDao(connectionSource, SpellParameters.class);
            List<SpellParameters> parameters = dao.queryBuilder().selectColumns("requirementLevel1", "requirementLevel2", "requirementLevel3").where().eq("spellNameId", spellId).query();
            Set<Integer> spellLevels = new TreeSet<>();
            for (SpellParameters parameter : parameters) {
                Integer requirementLevel1 = parameter.requirementLevel1;
                if (requirementLevel1 != 0) {
                    spellLevels.add(requirementLevel1);
                }

                Integer requirementLevel2 = parameter.requirementLevel2;
                if (requirementLevel2 != 0) {
                    spellLevels.add(requirementLevel2);
                }

                Integer requirementLevel3 = parameter.requirementLevel3;
                if (requirementLevel3 != 0) {
                    spellLevels.add(requirementLevel3);
                }
            }

            return spellLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public static List<SpellParameters> getSpells(String spellSchoolName) {
        ConnectionSource connectionSource = getConnectionSource();
        List<SpellParameters> spellParameters = Collections.emptyList();
        try {
            Map<Integer, String> spellSchoolMap = Mappings.INSTANCE.SPELL_SCHOOL_MAP;
            for (Map.Entry<Integer, String> integerStringEntry : spellSchoolMap.entrySet()) {
                if (!integerStringEntry.getValue().equals(spellSchoolName)) {
                    continue;
                }

                Integer spellSchoolId = integerStringEntry.getKey();
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
                spellParameters = where.or(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        return spellParameters;
    }

    public static List<SpellParameters> getAllSpellParameters() {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            return DaoManager.createDao(connectionSource, SpellParameters.class).queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static List<SkillParameters> getSkillParameters(int skillSchoolId, int skillLevel) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<SkillParameters, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            return dao.queryBuilder().where().eq("skillTypeId", skillSchoolId).and().eq("level", skillLevel).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static List<SpellParameters> getSpellParameters(int selectedSpellId, int selectedLevel) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<SpellParameters, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            Where<SpellParameters, Integer> where = dao.queryBuilder().where();
            Where<SpellParameters, Integer> spellNameId = where.eq("spellNameId", selectedSpellId);
            Where<SpellParameters, Integer> or = where.or(where.eq("requirementLevel1", selectedLevel),
                    where.eq("requirementLevel2", selectedLevel),
                    where.eq("requirementLevel3", selectedLevel));
            where.and(spellNameId, or);
            return where.query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static void createSkillParametersTable() {
        recreateTable(SkillParameters.class);
    }

    public static void addRecordsToSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
        addRecordsToTable(SkillParameters.class, offsettedData);
    }

    public static void addRecordsToSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        addRecordsToTable(SpellParameters.class, offsettedData);
    }

    private static <T extends OffsetableObject> void addRecordsToTable(final Class<T> ormClass, final List<Pair<byte[], Long>> offsetedData) {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ormClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Pair<byte[], Long> longPair : offsetedData) {
                        T object = createObject(ormClass, longPair.getKey());
                        object.setOffset(longPair.getValue());
                        dao.create(object);
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

    private static <T> T createObject(Class<T> objectClass, byte[] buffer) {
        T object;
        try {
            object = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        ObjectDataMappingService.INSTANCE.fillObjectWithData(object, buffer);
        return object;
    }

    public static void createSpellParametersTable() {
        recreateTable(SpellParameters.class);
    }

    private static void recreateTable(Class<?> ormObjectClass) {
        ConnectionSource connectionSource = getConnectionSource();
        if (connectionSource == null) return;

        try {
            TableUtils.dropTable(connectionSource, ormObjectClass, true);
            TableUtils.createTableIfNotExists(connectionSource, ormObjectClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private static ConnectionSource getConnectionSource() {
        ConnectionSource connectionSource;
        try {
            String databaseUrl = "jdbc:h2:file:./sfeditorDatabase;DB_CLOSE_ON_EXIT=FALSE";
            connectionSource =
                    new JdbcConnectionSource(databaseUrl);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return connectionSource;
    }
}
