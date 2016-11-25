package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public enum SpellNameTableService {
    INSTANCE;

    private static final int NUMBER_OF_PARAMETER_FIELDS = 9;
    private static final String FIELD_ATTRIBUTE = "field";
    private static final String NAME_ATTRIBUTE = "name";

    private static final Logger LOGGER = Logger.getLogger(SpellNameTableService.class);
    private static final String SPELL_NAME_MAPPING_PROPERTY_FILENAME = "spellNameMapping";

    public void createSpellNameTable() {
        CommonTableService.INSTANCE.recreateTable(SpellName.class);
        fillSpellNameTableWithPredefinedNames();
    }

    private void fillSpellNameTableWithPredefinedNames() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final List<SpellName> spellNames = new ArrayList<>();

        ResourceBundle bundle = ResourceBundle.getBundle(SPELL_NAME_MAPPING_PROPERTY_FILENAME, Locale.getDefault());
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            SpellName spellNameObject = createSpellNameObject(Integer.valueOf(key), value);
            spellNames.add(spellNameObject);
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

    private SpellName createSpellNameObject(Integer spellType, String spellName) {
        String name = I18N.INSTANCE.getMessage(spellName + "." + NAME_ATTRIBUTE);
        SpellName spell = new SpellName(spellType, name);
        for (int i = 1; i < NUMBER_OF_PARAMETER_FIELDS + 1; i++) {
            String fieldName = FIELD_ATTRIBUTE + i;
            try {
                Field declaredField = spell.getClass().getDeclaredField(fieldName);
                String parameter;
                try {
                    parameter = I18N.INSTANCE.getMessage(spellName + "." + fieldName);
                } catch (MissingResourceException e) {
                    parameter = I18N.INSTANCE.getMessage("spellParameterNotUsed");
                }
                declaredField.setAccessible(true);
                declaredField.set(spell, ViewTools.convertToMultiline(parameter));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return spell;
    }

    public Integer getSpellId(String spellName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().selectColumns("spellType").where().eq("name", spellName).query();
            return query.get(0).spellType;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public String getSpellName(Integer spellType) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().selectColumns("name").where().eq("spellType", spellType).query();
            if (query.isEmpty()) {
                return null;
            }

            return query.get(0).name;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public SpellName getSpellName(String spellName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().where().eq("name", spellName).query();
            return query.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getAllSpellNames() {
        List<SpellName> allTableData = CommonTableService.INSTANCE.getAllTableData(SpellName.class);
        List<String> result = new ArrayList<>();
        for (SpellName spellName : allTableData) {
            result.add(spellName.name);
        }

        return result;
    }
}
