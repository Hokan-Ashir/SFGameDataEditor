package sfgamedataeditor.database.creatures.production.buildings;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_buildings")
public class CreatureBuildingsObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=284&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer creatureId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer requirementNumber;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer buildingId;
}
