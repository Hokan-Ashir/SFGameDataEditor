package sfgamedataeditor.database.buildings;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "buildings")
public class BuildingsObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer buildingId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer raceId;

    @Data(offset = 3, length = 3)
    @DatabaseField
    private Integer enterSlot;

    @Data(offset = 6, length = 3)
    @DatabaseField
    private Integer slotsAmount;

    @Data(offset = 9, length = 4)
    @DatabaseField
    private Integer hpAmount;

    @Data(offset = 13, length = 2)
    @DatabaseField
    private Integer nameId;

    @Data(offset = 15, length = 4)
    @DatabaseField
    private Integer timeBuilding;

    @Data(offset = 19, length = 4)
    @DatabaseField
    private Integer workRequirement;
}
