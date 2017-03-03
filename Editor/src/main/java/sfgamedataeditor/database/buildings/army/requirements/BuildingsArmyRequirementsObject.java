package sfgamedataeditor.database.buildings.army.requirements;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "buildings_army_requirements")
public class BuildingsArmyRequirementsObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer unitId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer numberOfRequirement;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer buildingId;
}
