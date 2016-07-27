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
import java.util.HashMap;
import java.util.Map;

public final class TableCreationUtils {

    private static final Logger LOGGER = Logger.getLogger(TableCreationUtils.class);

    private TableCreationUtils() {}

    public static void createSpellNameTable() {
        createTable(SpellName.class);
        fillSpellNameTableWithPredefinedNames();
    }

    private static void fillSpellNameTableWithPredefinedNames() {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<SpellName, String> dao;
        try {
             dao = DaoManager.createDao(connectionSource, SpellName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

//        TODO make batch requests
//        TODO make inner map, not static for all app instance, cause we need to instantiate it only once
        for (Map.Entry<Integer, String> integerStringEntry : SpellMap.SPELLMAP.entrySet()) {
            String i18nName = I18N.INSTANCE.getMessage(integerStringEntry.getValue() + "." + SpellMap.NAME_ATTRIBUTE);
            SpellName spellName = new SpellName(integerStringEntry.getKey(), i18nName);
            try {
                dao.create(spellName);
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
    }

    public static void createSkillNameTable() {
        createTable(SkillName.class);

        ConnectionSource connectionSource = getConnectionSource();
        Dao<SkillName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        Map<Integer, String> skillTypeToNameMap = new HashMap<Integer, String>() {{
            put(1, I18N.INSTANCE.getMessage("lightCombatArts"));
            put(2, I18N.INSTANCE.getMessage("heavyCombatArts"));
            put(3, I18N.INSTANCE.getMessage("archery"));
            put(4, I18N.INSTANCE.getMessage("whiteMagic"));
            put(5, I18N.INSTANCE.getMessage("elementalMagic"));
            put(6, I18N.INSTANCE.getMessage("mindMagic"));
            put(7, I18N.INSTANCE.getMessage("blackMagic"));
        }};

//        TODO make batch requests
        for (Map.Entry<Integer, String> integerStringEntry : skillTypeToNameMap.entrySet()) {
            SkillName spellName = new SkillName(integerStringEntry.getKey(), integerStringEntry.getValue());
            try {
                dao.create(spellName);
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
    }

    public static void createSkillParametersTable() {
        createTable(SkillParameters.class);
    }

    public static void addRecordToSkillParametersTable(byte[] buffer) {
        addRecordToTable(SkillParameters.class, buffer);
    }

    public static void addRecordToSpellParametersTable(byte[] buffer) {
        addRecordToTable(SpellParameters.class, buffer);
    }

    private static <T> void addRecordToTable(Class<T> ormClass, byte[] buffer) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ormClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        T object = createObject(ormClass, buffer);
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
        createTable(SpellParameters.class);
    }

    private static void createTable(Class<?> ormObjectClass) {
        ConnectionSource connectionSource = getConnectionSource();
        if (connectionSource == null) return;

        try {
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
