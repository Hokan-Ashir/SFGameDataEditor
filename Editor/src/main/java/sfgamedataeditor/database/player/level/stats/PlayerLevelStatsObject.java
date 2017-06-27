package sfgamedataeditor.database.player.level.stats;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "player_level_stats")
public class PlayerLevelStatsObject extends OffsetableObject {

    @Data(offset = 0, length = 1)
    @DatabaseField(id = true)
    public Integer level;

    @Data(offset = 1, length = 2)
    @DatabaseField
    public Integer hpFactor;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer mpFactor;

    @Data(offset = 5, length = 4)
    @DatabaseField
    private Integer experience;

    @Data(offset = 9, length = 1)
    @DatabaseField
    private Integer maxAttributesPoints;

    @Data(offset = 10, length = 1)
    @DatabaseField
    private Integer maxSkillLevel;

    @Data(offset = 11, length = 2)
    @DatabaseField
    private Integer weaponFactor;

    @Data(offset = 13, length = 2)
    @DatabaseField
    private Integer armorFactor;
}
