package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SkillName;
import sfgamedataeditor.utils.I18N;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public enum SkillNameTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SkillNameTableService.class);

    public void createSkillNameTable() {
        CommonTableService.INSTANCE.recreateTable(SkillName.class);

        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
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
}
