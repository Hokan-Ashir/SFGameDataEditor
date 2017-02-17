package sfgamedataeditor.database.buildings.requirements;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "buildings_requirements")
public class BuildingsRequirementsObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer buildingId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer resourceId;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer resourceAmount;
}
