package sfgamedataeditor.databind.files;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;

public enum FilesContainer {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(FilesContainer.class);

    /**
     * length of GameData.cff file on version v1.1
     */
    private static final long GAME_DATA_CFF_V11_FILE_LENGTH = 39442356L;

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

    public boolean isVersion11() {
        try {
            return getModificationFile().length() == GAME_DATA_CFF_V11_FILE_LENGTH;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return false;
    }
}
