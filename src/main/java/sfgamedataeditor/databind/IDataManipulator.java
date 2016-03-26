package sfgamedataeditor.databind;

import java.io.RandomAccessFile;

public interface IDataManipulator {

    void saveDataInFile(RandomAccessFile file);

    void loadDataFromFile(RandomAccessFile file);
}
