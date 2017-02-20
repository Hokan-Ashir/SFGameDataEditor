package sfgamedataeditor.database.common;

import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public interface TableCreationService {
    void createTable();
    void addRecordsToTable(List<Pair<byte[], Long>> offsettedData);
    DTOOffsetTypes getDTOOffsetType();
}
