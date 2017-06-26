package sfgamedataeditor.database.text;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.DTOFilter;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
            CommonTableService.INSTANCE.addRecordsToTable(TextObject.class, offsettedData, new TextFilter());
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
    public static final String NULL_OBJECT_PREFIX = "Null object - ";

    public String getObjectName(int nameId) {
        List<String> objectNames = getObjectNames(new Integer[]{nameId});
        if (objectNames == null || objectNames.isEmpty()) {
            return NULL_OBJECT_PREFIX + nameId;
        } else {
            return objectNames.get(0);
        }
    }

    public List<String> getObjectNames(Integer[] nameIds) {
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
            List<TextObject> objects = dao.queryBuilder().selectColumns("text", "textId").orderBy("textId", true).where().in("textId", (Object[]) nameIds).and().eq("languageId", 1).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                List<String> result = new ArrayList<>();
                for (Integer nameId : nameIds) {
                    TextObject textObject = null;
                    for (TextObject object : objects) {
                        if (object.textId.equals(nameId)) {
                            textObject = object;
                        }
                    }

                    if (textObject != null) {
                        result.add(textObject.text);
                    } else {
                        result.add(NULL_OBJECT_PREFIX + nameId);
                    }
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

    private static final class TextFilter implements DTOFilter {

        @Override
        public boolean isAcceptable(byte[] buffer) {
            return buffer[3] == 0;
        }
    }
}
