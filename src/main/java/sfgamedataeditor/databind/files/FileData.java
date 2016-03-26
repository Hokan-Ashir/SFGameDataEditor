package sfgamedataeditor.databind.files;

import java.io.RandomAccessFile;

public class FileData {
    private RandomAccessFile file;
    private String path;
    private String name;

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
