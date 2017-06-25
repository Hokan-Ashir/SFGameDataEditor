package sfgamedataeditor.database.text;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "text")
public class TextObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer textId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer languageId;

    @Data(offset = 3, length = 1)
    @DatabaseField
    public Integer isDialogue;

    @Data(offset = 4, length = 50)
    @DatabaseField(width = 50)
    public String dialogueName;

    @Data(offset = 54, length = 512)
    @DatabaseField(width = 512)
    public String text;
}
