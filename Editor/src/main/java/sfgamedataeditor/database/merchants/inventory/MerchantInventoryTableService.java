package sfgamedataeditor.database.merchants.inventory;

import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum MerchantInventoryTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(MerchantInventoryObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x4;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F864CC, 0x03F867CB);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return MerchantInventoryObject.class;
        }
    }
}
