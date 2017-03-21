package sfgamedataeditor.database.items.effects;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ItemEffectsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ItemEffectsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ItemEffectsObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x4;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0012B373, 0x0012D16A);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ItemEffectsObject.class;
        }
    }
}
