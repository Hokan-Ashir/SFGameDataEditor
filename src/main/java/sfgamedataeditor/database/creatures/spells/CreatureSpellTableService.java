package sfgamedataeditor.database.creatures.spells;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureSpellTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureSpellObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureSpellObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_SPELLS;
        }
    };
}
