package sfgamedataeditor.database.items.armor.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum ArmorParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ArmorParametersObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ArmorParametersObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 36;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x000897E8, 0x0008F133);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ArmorParametersObject.class;
        }
    };

    private static final int ONE_HANDED_WEAPON_TYPE = 1793;
    private static final Logger LOGGER = Logger.getLogger(ArmorParametersTableService.class);

    public ArmorParametersObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ArmorParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ArmorParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ArmorParametersObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    // because Orb objects considered as "1h-weapon" their type is set to 1h-weapon in ItemPriceParameterObject
    // BUT they have to weapon parameters, so they have no actual WeaponParametersObject binded with them
    // AND they have Armor parameters objects, so to get list of all Orbs objects we have to find out
    // what are objects that:
    // - have 1h-weapon type in ItemPriceParameterObject
    // - have ArmorParametersObjects (that's the main reason why this method is here and no in WeaponParametersTableService)
    public List<ObjectTuple> getOrbNames() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<ArmorParametersObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ArmorParametersObject.class);
            GenericRawResults<Pair<Integer, Integer>> rawResults =
                    dao.queryRaw(
                            "select t.itemId, t.nameId from " +
                                    "(select armor_parameters.itemId as itemId, item_price_parameters.nameId as nameId" +
                                    " from armor_parameters " +
                                    "inner join item_price_parameters " +
                                    "on armor_parameters.itemId = item_price_parameters.itemId " +
                                    "where item_price_parameters.typeId = ?) as t " +
                                    "left join weapon_parameters " +
                                    "on t.itemId = weapon_parameters.itemId " +
                                    "where weapon_parameters.id is null",
                            new RawRowMapper<Pair<Integer, Integer>>() {
                                public Pair<Integer, Integer> mapRow(String[] columnNames,
                                                      String[] resultColumns) {
                                    return new Pair<>(Integer.valueOf(resultColumns[0]), Integer.valueOf(resultColumns[1]));
                                }
                            },
                            String.valueOf(ONE_HANDED_WEAPON_TYPE));

            List<Pair<Integer, Integer>> orbItemIds = rawResults.getResults();
            Integer[] nameIds = new Integer[orbItemIds.size()];
            Integer[] objectIds = new Integer[orbItemIds.size()];
            for (int i = 0; i < orbItemIds.size(); ++i) {
                nameIds[i] = orbItemIds.get(i).getValue();
                objectIds[i] = orbItemIds.get(i).getKey();
            }

            return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
