package sfgamedataeditor.files;

import java.io.RandomAccessFile;

public class FileData {
    private final RandomAccessFile file;
    private final String path;
    private final String name;

    public FileData(RandomAccessFile file, String path, String name) {
        this.file = file;
        this.path = path;
        this.name = name;
    }

    public RandomAccessFile getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
