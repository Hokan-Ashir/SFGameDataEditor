package i18nbase.objects;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public enum Service {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(Service.class);

    public <T extends I18NObject> void createTableFromResourceFile(String fileName, Class<T> objectClass) {
        recreateTable(objectClass);
        List<T> i18nObjects = getObjectsFromFile(fileName, objectClass);
        addRecordsToTable(objectClass, i18nObjects);
    }

    private <T extends I18NObject> List<T> getObjectsFromFile(String fileName, Class<T> objectClass) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<T> i18nObjects = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split("\\s=\\s");
                if (strings[0].isEmpty()) {
                    continue;
                }

                T object = createObject(objectClass, strings[0], strings[1]);
                i18nObjects.add(object);
            }
            scanner.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return i18nObjects;
    }

    private <T extends I18NObject> void addRecordsToTable(Class<T> objectClass, final List<T> objects) {
        ConnectionSource connectionSource = getConnectionSourceFromModule();
        final Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, objectClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T object : objects) {
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

    private <T extends I18NObject> T createObject(Class<T> objectClass, String key, String value) {
        T object;
        try {
            object = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        object.key = key;
        object.value = value;

        return object;
    }

    private void recreateTable(Class<?> ormObjectClass) {
        ConnectionSource connectionSource = getConnectionSourceFromModule();
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

    public <T extends I18NObject> String getValue(Class<T> daoClass, String key) {
        Dao<T, String> dao = getDao(daoClass);

        try {
            T object = dao.queryBuilder().where().eq("key", key).query().get(0);
            return object.value;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public <T extends I18NObject> String getKey(Class<T> daoClass, String value) {
        Dao<T, String> dao = getDao(daoClass);

        try {
            T object = dao.queryBuilder().where().eq("value", value).query().get(0);
            return object.key;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private <T extends I18NObject> Dao<T, String> getDao(Class<T> daoClass) {
        ConnectionSource connectionSource = getConnectionSourceFromResources();
        final Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, daoClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        return dao;
    }

    private ConnectionSource getConnectionSourceFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("I18N-base.mv.db");
        LOGGER.error(resource);
        return getConnectionSource("jdbc:h2:file:" + resource + ";DB_CLOSE_ON_EXIT=FALSE");
    }

    private ConnectionSource getConnectionSourceFromModule() {
        return getConnectionSource("jdbc:h2:file:../i18n-base;DB_CLOSE_ON_EXIT=FALSE");
    }

    private ConnectionSource getConnectionSource(String databaseURL) {
        ConnectionSource connectionSource;
        try {
            connectionSource =
                    new JdbcConnectionSource(databaseURL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return connectionSource;
    }
}
