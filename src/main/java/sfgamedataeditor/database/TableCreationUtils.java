package sfgamedataeditor.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.*;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.utils.I18N;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public final class TableCreationUtils {

    private static final Logger LOGGER = Logger.getLogger(TableCreationUtils.class);

    private TableCreationUtils() {}

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
//        TODO make batch requests
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

    public static void createSkillParametersTable() {
        recreateTable(SkillParameters.class);
    }

    public static void addRecordToSkillParametersTable(byte[] buffer, long offset) {
        addRecordToTable(SkillParameters.class, buffer, offset);
    }

    public static void addRecordToSpellParametersTable(byte[] buffer, long offset) {
        addRecordToTable(SpellParameters.class, buffer, offset);
    }

    private static <T extends OffsetableObject> void addRecordToTable(Class<T> ormClass, byte[] buffer, long offset) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ormClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        T object = createObject(ormClass, buffer);
        object.setOffset(offset);
        try {
            dao.create(object);
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
            TableUtils.dropTable(connectionSource, ormObjectClass, false);
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
            String databaseUrl = "jdbc:h2:file:./database;DB_CLOSE_ON_EXIT=FALSE";
            connectionSource =
                    new JdbcConnectionSource(databaseUrl);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return connectionSource;
    }
}
