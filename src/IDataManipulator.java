import java.io.RandomAccessFile;

/**
 * Created by hokan on 02.10.15.
 */
public interface IDataManipulator {

    void saveDataInFile(RandomAccessFile file);

    void loadDataFromFile(RandomAccessFile file);
}
