package sfgamedataeditor.database.file.storage;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;

import java.sql.SQLException;
import java.util.List;

public enum  FileStorageService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(FileStorageService.class);

    static {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();

        try {
            TableUtils.createTableIfNotExists(connectionSource, FileStorageObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void setFileStorage(FileStorageObject object) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<FileStorageObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, FileStorageObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.update(object);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public FileStorageObject getFileStorage() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<FileStorageObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, FileStorageObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<FileStorageObject> fileStorageObjects = dao.queryForAll();
            if (fileStorageObjects == null || fileStorageObjects.isEmpty()) {
                FileStorageObject object = new FileStorageObject();
                dao.create(object);
                return object;
            } else {
                return fileStorageObjects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public void setIsVersion11(boolean isVersion11) {
        FileStorageObject fileStorage = getFileStorage();
        fileStorage.isVersion_11 = isVersion11;
        setFileStorage(fileStorage);
    }

    public void setSfModFilePath(String sfModFilePath) {
        FileStorageObject fileStorage = getFileStorage();
        fileStorage.pathToSFMod = sfModFilePath;
        setFileStorage(fileStorage);
    }
}
