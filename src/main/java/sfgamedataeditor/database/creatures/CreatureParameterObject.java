package sfgamedataeditor.database.creatures;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_parameters")
public class CreatureParameterObject extends OffsetableObject {

    // offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=256&sid=fc7270cda26e73fbb92bf600e1c5be4d

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer statsId;

    @Data(offset = 2, length = 2)
    @DatabaseField
    private Integer level;

    @Data(offset = 4, length = 1)
    @DatabaseField
    public Integer raceId;

    @Data(offset = 5, length = 2)
    @DatabaseField
    private Integer agility;

    @Data(offset = 7, length = 2)
    @DatabaseField
    private Integer charisma;

    @Data(offset = 9, length = 2)
    @DatabaseField
    private Integer dexterity;

    @Data(offset = 11, length = 2)
    @DatabaseField
    private Integer intelligence;

    @Data(offset = 13, length = 2)
    @DatabaseField
    private Integer stamina;

    @Data(offset = 15, length = 2)
    @DatabaseField
    private Integer strength;

    @Data(offset = 17, length = 2)
    @DatabaseField
    private Integer wisdom;

    // TODO 2 bytes are unknown

    @Data(offset = 21, length = 2)
    @DatabaseField
    private Integer fireResistance;

    @Data(offset = 23, length = 2)
    @DatabaseField
    private Integer iceResistance;

    @Data(offset = 25, length = 2)
    @DatabaseField
    private Integer blackResistance;

    @Data(offset = 27, length = 2)
    @DatabaseField
    private Integer mindResistance;

    @Data(offset = 29, length = 2)
    @DatabaseField
    private Integer walkSpeed;

    @Data(offset = 31, length = 2)
    @DatabaseField
    private Integer fightSpeed;

    @Data(offset = 33, length = 2)
    @DatabaseField
    private Integer castSpeed;

    // (64 00 is normal)
    @Data(offset = 35, length = 2)
    @DatabaseField
    private Integer size;

    // TODO 2 bytes are unknown

    // in milliseconds
    // for heroes/workers at monument; for scripted creatures (campaign spawned and spell spawned)
    @Data(offset = 39, length = 4)
    @DatabaseField
    private Integer spawnTime;

    //    00 - male; can be killed
    //    01 - female; can be killed
    //    02 - male; can't be killed, only reduce HP to 1 (campaign companions, such as Urias, etc.)
    //    03 - female; can't be killed, only reduce HP to 1 (campaign companions, such as Lena, etc.)
    @Data(offset = 43, length = 1)
    @DatabaseField
    private Integer genderAndVulnerability;

    @Data(offset = 44, length = 2)
    @DatabaseField
    private Integer headId;

    //    01 - all slots available
    //    02 - only hands and rings available
    //    03 - no slots available
    @Data(offset = 46, length = 1)
    @DatabaseField
    private Integer equipmentSlotsId;

    public CreatureParameterObject() {
    }
}
