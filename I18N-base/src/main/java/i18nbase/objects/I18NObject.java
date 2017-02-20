package i18nbase.objects;

import com.j256.ormlite.field.DatabaseField;

public class I18NObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    public String key;

    @DatabaseField
    public String value;
}
