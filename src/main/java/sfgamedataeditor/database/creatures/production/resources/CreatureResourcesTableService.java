package sfgamedataeditor.database.creatures.production.resources;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureResourcesTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureResourcesObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureResourcesObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_RESOURCES;
        }
    };
}
