package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;

public class OffsetableObject {

    @DatabaseField(canBeNull = false)
    private Long offset;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }
}
