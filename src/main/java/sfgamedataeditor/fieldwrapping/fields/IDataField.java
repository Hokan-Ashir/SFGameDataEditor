package sfgamedataeditor.fieldwrapping.fields;

public interface IDataField {
    void loadFromFile();
    void saveToFile();

    void setOffsetInBytes(long offsetInBytes);
}
