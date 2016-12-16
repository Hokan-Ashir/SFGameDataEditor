package sfgamedataeditor.database.creatures.equipment;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureEquipmentTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureEquipmentObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureEquipmentObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_EQUIPMENT;
        }
    };
}
