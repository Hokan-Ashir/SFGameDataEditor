package sfgamedataeditor.databind.files;

import java.io.RandomAccessFile;

public enum FilesContainer {
    INSTANCE;

    private FileData originalFileData;
    private FileData modificationFileData;

    public RandomAccessFile getOriginalFile() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getFile();
    }

    public void setOriginalFile(FileData originalFileData) {
        this.originalFileData = originalFileData;
    }

    public RandomAccessFile getModificationFile() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getFile();
    }

    public void setModificationFile(FileData modificationFileData) {
        this.modificationFileData = modificationFileData;
    }

    public String getOriginalFilePath() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getPath();
    }

    public String getOriginalFileName() {
        if (originalFileData == null) {
            return null;
        }

        return originalFileData.getName();
    }

    public String getModificationFilePath() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getPath();
    }

    public String getModificationFileName() {
        if (modificationFileData == null) {
            return null;
        }

        return modificationFileData.getName();
    }
}
