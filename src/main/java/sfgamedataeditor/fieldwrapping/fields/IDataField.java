package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.database.objects.OffsetableObject;

public interface IDataField {
    void saveToFile();
    void mapValues(OffsetableObject mappedObject);
}
