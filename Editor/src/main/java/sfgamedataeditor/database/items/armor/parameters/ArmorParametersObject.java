package sfgamedataeditor.database.items.armor.parameters;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "armor_parameters")
public class ArmorParametersObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=266&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @Data(offset = 0, length = 2)
    @DatabaseField(id = true)
    private Integer itemId;

    @Data(offset = 2, length = 2)
    @DatabaseField
    private Integer strength;

    @Data(offset = 4, length = 2)
    @DatabaseField
    private Integer stamina;

    @Data(offset = 6, length = 2)
    @DatabaseField
    private Integer agility;

    @Data(offset = 8, length = 2)
    @DatabaseField
    private Integer dexterity;

    @Data(offset = 10, length = 2)
    @DatabaseField
    private Integer health;

    @Data(offset = 12, length = 2)
    @DatabaseField
    private Integer charisma;

    @Data(offset = 14, length = 2)
    @DatabaseField
    private Integer intelligence;

    @Data(offset = 16, length = 2)
    @DatabaseField
    private Integer wisdom;

    @Data(offset = 18, length = 2)
    @DatabaseField
    private Integer mana;

    @Data(offset = 20, length = 2)
    @DatabaseField
    private Integer armor;

    @Data(offset = 22, length = 2)
    @DatabaseField
    private Integer fireResistance;

    @Data(offset = 24, length = 2)
    @DatabaseField
    private Integer iceResistance;

    @Data(offset = 26, length = 2)
    @DatabaseField
    private Integer blackResistance;

    @Data(offset = 28, length = 2)
    @DatabaseField
    private Integer mindResistance;

    @Data(offset = 30, length = 2)
    @DatabaseField
    private Integer runSpeed;

    @Data(offset = 32, length = 2)
    @DatabaseField
    private Integer fightSpeed;

    @Data(offset = 34, length = 2)
    @DatabaseField
    private Integer castSpeed;
}
