package sfgamedataeditor.database.spells.names;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import i18nbase.objects.I18NObject;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public enum SpellNameTableService {
    INSTANCE;

    private static final int NUMBER_OF_PARAMETER_FIELDS = 9;
    private static final String FIELD_ATTRIBUTE = "field";
    private static final String NAME_ATTRIBUTE = "name";

    private static final Logger LOGGER = Logger.getLogger(SpellNameTableService.class);

    public void createSpellNameTable() {
        CommonTableService.INSTANCE.recreateTable(SpellNameObject.class);
        fillSpellNameTableWithPredefinedNames();
    }

    private void fillSpellNameTableWithPredefinedNames() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellNameObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellNameObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final List<SpellNameObject> spellNameObjects = new ArrayList<>();

        List<? extends I18NObject> i18NObjects = I18NService.INSTANCE.getI18NObjects(I18NTypes.SPELLS_NAME_MAPPING);
        for (I18NObject i18NObject : i18NObjects) {
            SpellNameObject spellNameObject = createSpellNameObject(Integer.valueOf(i18NObject.key), i18NObject.value);
            spellNameObjects.add(spellNameObject);
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (SpellNameObject spellNameObject : spellNameObjects) {
                        dao.create(spellNameObject);
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

    private SpellNameObject createSpellNameObject(Integer spellType, String spellName) {
        String name = I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, spellName + "." + NAME_ATTRIBUTE);
        SpellNameObject spell = new SpellNameObject(spellType, name);
        for (int i = 1; i < NUMBER_OF_PARAMETER_FIELDS + 1; i++) {
            String fieldName = FIELD_ATTRIBUTE + i;
            try {
                Field declaredField = spell.getClass().getDeclaredField(fieldName);
                String parameter = I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, spellName + "." + fieldName);
                if (parameter != null) {
                    parameter = ViewTools.convertToMultiline(parameter);
                }
                declaredField.setAccessible(true);
                declaredField.set(spell, parameter);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return spell;
    }

    public Integer getSpellId(String spellName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellNameObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellNameObject.class);
            List<SpellNameObject> query = dao.queryBuilder().selectColumns("spellType").where().eq("name", spellName).query();
            return query.get(0).spellType;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public String getSpellName(Integer spellType) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellNameObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellNameObject.class);
            List<SpellNameObject> query = dao.queryBuilder().selectColumns("name").where().eq("spellType", spellType).query();
            if (query.isEmpty()) {
                return null;
            }

            return query.get(0).name;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public SpellNameObject getSpellName(String spellName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellNameObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellNameObject.class);
            List<SpellNameObject> query = dao.queryBuilder().where().eq("name", spellName).query();
            return query.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getAllSpellNames() {
        List<SpellNameObject> allTableData = CommonTableService.INSTANCE.getAllTableData(SpellNameObject.class);
        List<String> result = new ArrayList<>();
        for (SpellNameObject spellNameObject : allTableData) {
            result.add(spellNameObject.name);
        }

        return result;
    }
}
