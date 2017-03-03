package sfgamedataeditor.database.items.spelleffect;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "item_spell_effects")
public class ItemSpellEffectsObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=270&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer itemId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer itemEffectNumber;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer effectNumber;
}
