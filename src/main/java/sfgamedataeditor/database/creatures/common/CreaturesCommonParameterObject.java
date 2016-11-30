package sfgamedataeditor.database.creatures.common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_common_parameters")
public class CreaturesCommonParameterObject extends OffsetableObject {

    // TODO creatureId is enough to be unique in form of identifier
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    public String name;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer creatureId;

    // TODO maybe add
    // 54 5A - name ID

    @Data(offset = 4, length = 2)
    @DatabaseField
    private Integer statsId;

    @Data(offset = 6, length = 4)
    @DatabaseField
    private Integer experience;

    // TODO maybe add
    // 00 00 - unknown (maybe MP factor)
    // 00 00 00 00 - HP factor (all nulls: player stats offset HP factor)
    // 64 00 64 32 00 - unknown (maybe some other factors)

    @Data(offset = 21, length = 2)
    @DatabaseField
    private Integer armor;

    // TODO maybe add
    // 4D 65 72 63 68 61 6E 74 20 4B 6C 61 75 73 00 00 00 00 00 00 00 00 00
    // 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 - unit name in gamedata file
    // 00 - unknown (only 00 and 01 values)
}
