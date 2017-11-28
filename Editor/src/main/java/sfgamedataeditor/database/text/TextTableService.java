package sfgamedataeditor.database.text;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.common.localization.LocalizationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TextTableService implements TableCreationService {
    INSTANCE {
        private DTOFilter filter = new TextFilter();
        private Serializer serializer = new TextSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(TextObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(TextObject.class, offsettedData, filter, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
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

    public ObjectTuple getObjectTuple(Integer nameId, Integer objectId) {
        List<ObjectTuple> objectNames = getObjectTuples(new Integer[]{nameId}, new Integer[] {objectId});
        if (objectNames == null || objectNames.isEmpty()) {
            return new ObjectTuple(NULL_OBJECT_PREFIX + objectId, objectId);
        } else {
            return objectNames.get(0);
        }
    }

    public List<ObjectTuple> getObjectTuples(Integer[] nameIds, Integer[] objectIds) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<TextObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, TextObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            Where<TextObject, String> where = dao.queryBuilder().selectColumns("text", "textId")
                    .orderBy("textId", true)
                    .where().in("textId", (Object[]) nameIds)
                    .and().eq("isDialogue", 0)
                    .and().eq("languageId", LocalizationService.INSTANCE.getLanguageId());
            List<TextObject> objects = where.query();
            if (objects.isEmpty()) {
                List<ObjectTuple> result = new ArrayList<>();
                for (Integer objectId : objectIds) {
                    result.add(new ObjectTuple(NULL_OBJECT_PREFIX + objectId, objectId));
                }
                return result;
            } else {
                List<ObjectTuple> result = new ArrayList<>();
                for (int i = 0; i < nameIds.length; ++i) {
                    TextObject textObject = null;
                    for (TextObject object : objects) {
                        if (object.textId.equals(nameIds[i])) {
                            textObject = object;
                        }
                    }

                    if (textObject != null) {
                        result.add(new ObjectTuple(textObject.text, objectIds[i]));
                    } else {
                        result.add(new ObjectTuple(NULL_OBJECT_PREFIX + objectIds[i], objectIds[i]));
                    }
                }

                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
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

        SelectArg textArg = new SelectArg(text);
        try {
            return dao.queryBuilder().where()
                    .like("text", textArg)
                    .and().eq("isDialogue", 0)
                    .and().eq("languageId", LocalizationService.INSTANCE.getLanguageId()).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Integer> getObjectsNameIdsByRegExp(String regexp) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<TextObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, TextObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            GenericRawResults<Integer> rawResults =
                    dao.queryRaw(
                            "select text.textId from text where text.text regexp ? and text.isDialogue = 0",
                            new RawRowMapper<Integer>() {
                                public Integer mapRow(String[] columnNames,
                                                      String[] resultColumns) {
                                    return Integer.valueOf(resultColumns[0]);
                                }
                            },
                            regexp);

            return rawResults.getResults();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // upload only selected language
    private static final class TextFilter implements DTOFilter {

        private static final int LANGUAGE_ID_BYTE_POSITION = 2;

        @Override
        public boolean isApplicable(byte[] value) {
            return value[LANGUAGE_ID_BYTE_POSITION] == LocalizationService.INSTANCE.getLanguageId();
        }
    }
}
