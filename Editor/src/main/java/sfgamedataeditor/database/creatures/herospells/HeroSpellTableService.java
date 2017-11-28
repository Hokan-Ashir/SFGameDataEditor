package sfgamedataeditor.database.creatures.herospells;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public enum HeroSpellTableService implements TableCreationService {
    INSTANCE {

        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(HeroSpellObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(HeroSpellObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x5;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x00062EA3, 0x00063591);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return HeroSpellObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureEquipmentTableService.class);

    public List<HeroSpellObject> getHeroSpellsByCreatureId(int statsId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<HeroSpellObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, HeroSpellObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<HeroSpellObject> objects = dao.queryBuilder().where().eq("statsId", statsId).query();
            if (objects.isEmpty()) {
                return Collections.emptyList();
            } else {
                // TODO double check this out; Warror Ishtar and Warrior Thiderik have spells with spellNumber = 0
                // which is impossible, cause such spell doesn't exists in game files
                // so this code is for eliminating this spells from result list
                Iterator<HeroSpellObject> iterator = objects.iterator();
                while (iterator.hasNext()) {
                    HeroSpellObject next = iterator.next();
                    if (next.spellNumber == 0) {
                        iterator.remove();
                    }
                }
                return objects;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
