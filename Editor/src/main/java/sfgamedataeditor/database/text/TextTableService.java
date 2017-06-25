package sfgamedataeditor.database.text;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TextTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(TextObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(TextObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 566;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0012D177, 0x03F4B426);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return TextObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(TextTableService.class);

    public String getObjectName(int nameId) {
        List<String> objectNames = getObjectNames(new Integer[]{nameId});
        return objectNames.get(0);
    }

    public List<String> getObjectNames(Integer[] nameId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<TextObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, TextObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            // TODO set correct languageId
            List<TextObject> objects = dao.queryBuilder().selectColumns("text").orderBy("textid", true).where().in("textId", (Object[]) nameId).and().eq("languageId", 1).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                List<String> result = new ArrayList<>();
                for (TextObject object : objects) {
                    result.add(object.text);
                }
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<TextObject> getObjectsByName(String text) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<TextObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, TextObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            // TODO set correct languageId
            return dao.queryBuilder().where().eq("text", text).and().eq("languageId", 1).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
