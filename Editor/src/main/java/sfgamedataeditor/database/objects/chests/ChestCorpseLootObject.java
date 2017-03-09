package sfgamedataeditor.database.objects.chests;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "chest_corpse_loot")
public class ChestCorpseLootObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242&p=1701#p1701

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer chestCorpseId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer slotNumber;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer itemId1;

    // chance in %
    @Data(offset = 5, length = 1)
    @DatabaseField
    private Integer chanceToGetItem1;

    @Data(offset = 6, length = 2)
    @DatabaseField
    private Integer itemId2;

    // chance in %
    @Data(offset = 8, length = 1)
    @DatabaseField
    private Integer chanceToGetItem2;

    // chances to get last is remaining, i.e:
    // suppose change to get first item is 15%
    // and second item has 15% drop rate
    // so chance to get last item is 100% - 15% - 15% = 70%
    @Data(offset = 9, length = 2)
    @DatabaseField
    private Integer itemId3;
}
