package sfgamedataeditor.databind.files;

import java.io.RandomAccessFile;

public final class FilesContainer {
    private static FileData originalFileData;
    private static FileData modificationFileData;

    private FilesContainer() {
    }

    public static RandomAccessFile getOriginalFile() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getFile();
    }

    public static RandomAccessFile getModificationFile() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getFile();
    }

    public static void setOriginalFile(FileData originalFileData) {
        FilesContainer.originalFileData = originalFileData;
    }

    public static void setModificationFile(FileData modificationFileData) {
        FilesContainer.modificationFileData = modificationFileData;
    }

    public static String getOriginalFilePath() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getPath();
    }

    public static String getOriginalFileName() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getName();
    }

    public static String getModificationFilePath() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getPath();
    }

    public static String getModificationFileName() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getName();
    }
}
