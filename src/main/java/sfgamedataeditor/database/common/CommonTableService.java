package sfgamedataeditor.database.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import sfgamedataeditor.utils.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public enum CommonTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CommonTableService.class);

    public void recreateTable(Class<?> ormObjectClass) {
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

    public <T extends OffsetableObject> void addRecordsToTable(final Class<T> ormClass, final List<Pair<byte[], Long>> offsetedData) {
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

    private <T> T createObject(Class<T> objectClass, byte[] buffer) {
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

    public <T> List<T> getAllTableData(Class<T> tableClass) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<T, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, tableClass);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ConnectionSource getConnectionSource() {
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

    public <T extends OffsetableObject> void updateObject(OffsetableObject objectToSave, Class<T> tClass) {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            Dao<T, Integer> dao = DaoManager.createDao(connectionSource, tClass);
            dao.update((T) objectToSave);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
