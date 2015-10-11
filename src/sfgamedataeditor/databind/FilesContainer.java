package sfgamedataeditor.databind;

import java.io.RandomAccessFile;

public final class FilesContainer {
    private static RandomAccessFile originalFile;
    private static RandomAccessFile modificationFile;

    private FilesContainer() {
    }

    public static RandomAccessFile getOriginalFile() {
        return originalFile;
    }

    public static RandomAccessFile getModificationFile() {
        return modificationFile;
    }

    public static void setOriginalFile(RandomAccessFile originalFile) {
        FilesContainer.originalFile = originalFile;
    }

    public static void setModificationFile(RandomAccessFile modificationFile) {
        FilesContainer.modificationFile = modificationFile;
    }
}
