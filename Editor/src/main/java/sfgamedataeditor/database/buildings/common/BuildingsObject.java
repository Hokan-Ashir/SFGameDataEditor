package sfgamedataeditor.database.buildings.common;

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
    public Integer buildingId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer raceId;

    @Data(offset = 3, length = 1)
    @DatabaseField
    private Integer enterSlot;

    @Data(offset = 4, length = 1)
    @DatabaseField
    private Integer slotsAmount;

    @Data(offset = 5, length = 2)
    @DatabaseField
    private Integer hpAmount;

    @Data(offset = 7, length = 2)
    @DatabaseField
    private Integer nameId;

    @Data(offset = 9, length = 2)
    @DatabaseField
    private Integer unknown1;

    @Data(offset = 11, length = 2)
    @DatabaseField
    private Integer unknown2;

    @Data(offset = 13, length = 1)
    @DatabaseField
    private Integer unknown3;

    @Data(offset = 14, length = 2)
    @DatabaseField
    private Integer workerJobTime;

    @Data(offset = 16, length = 2)
    @DatabaseField
    private Integer unknown4;

    @Data(offset = 18, length = 4)
    @DatabaseField
    private Integer unknown5;

    @Data(offset = 22, length = 1)
    @DatabaseField
    private Integer unknown6;
}
