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
    // still have to be serialized, cause during deserialization whole object is deserialized into byte array
    // and no bytes should be replaced by zeros
    @Data(offset = 2, length = 2)
    @DatabaseField
    public Integer nameId;

    @Data(offset = 4, length = 2)
    @DatabaseField
    private Integer statsId;

    @Data(offset = 6, length = 4)
    @DatabaseField
    private Integer experience;

    // 00 00 - unknown (maybe MP factor)
    @Data(offset = 10, length = 2)
    @DatabaseField
    private Integer unknown1;

    // 00 00 00 00 - HP factor (all nulls: player parameters offset HP factor)
    @Data(offset = 12, length = 4)
    @DatabaseField
    private Integer hpFactor;

    // 64 00 64 32 00 - unknown (maybe some other factors)
    @Data(offset = 16, length = 4)
    @DatabaseField
    private Integer unknown2;

    @Data(offset = 20, length = 1)
    @DatabaseField
    private Integer unknown3;

    @Data(offset = 21, length = 2)
    @DatabaseField
    private Integer armor;

    // 4D 65 72 63 68 61 6E 74 20 4B 6C 61 75 73 00 00 00 00 00 00 00 00 00
    // 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 - unit name in gamedata file
    @Data(offset = 23, length = 4)
    @DatabaseField
    private Integer unitNameIdPart1;

    @Data(offset = 27, length = 4)
    @DatabaseField
    private Integer unitNameIdPart2;

    @Data(offset = 31, length = 4)
    @DatabaseField
    private Integer unitNameIdPart3;

    @Data(offset = 35, length = 4)
    @DatabaseField
    private Integer unitNameIdPart4;

    @Data(offset = 39, length = 4)
    @DatabaseField
    private Integer unitNameIdPart5;

    @Data(offset = 43, length = 4)
    @DatabaseField
    private Integer unitNameIdPart6;

    @Data(offset = 47, length = 4)
    @DatabaseField
    private Integer unitNameIdPart7;

    @Data(offset = 51, length = 4)
    @DatabaseField
    private Integer unitNameIdPart8;

    @Data(offset = 55, length = 4)
    @DatabaseField
    private Integer unitNameIdPart9;

    @Data(offset = 59, length = 4)
    @DatabaseField
    private Integer unitNameIdPart10;

    // 00 - unknown (only 00 and 01 values)
    @Data(offset = 63, length = 1)
    @DatabaseField
    private Integer unknown4;
}
