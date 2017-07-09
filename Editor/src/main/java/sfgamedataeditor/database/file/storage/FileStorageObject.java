package sfgamedataeditor.database.file.storage;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "file_strage")
public class FileStorageObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    public String pathToGameDataCff;

    @DatabaseField
    public String pathToSFMod;

    @DatabaseField
    public Boolean isVersion_11;
}
