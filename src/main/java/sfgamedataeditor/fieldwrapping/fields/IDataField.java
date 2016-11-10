package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.database.objects.OffsetableObject;

public interface IDataField {
    void setValueToField();
    void mapValues(OffsetableObject mappedObject);
}
