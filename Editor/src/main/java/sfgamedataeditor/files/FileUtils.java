package sfgamedataeditor.files;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.impl.AESDecrypter;
import de.idyl.winzipaes.impl.AESDecrypterBC;
import de.idyl.winzipaes.impl.ExtZipEntry;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.file.storage.FileStorageObject;
import sfgamedataeditor.database.file.storage.FileStorageService;
import xdeltaencoder.org.mantlik.xdeltaencoder.XDeltaEncoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;

public final class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class);
    private static final String HASH_ALGORITHM = "SHA-512";
    public static final String MOD_FILE_EXTENSION = ".mod";
    public static final String TMP_FILE_EXTENSION = ".tmp";

    /**
     * length of GameData.cff file on version v1.1
     */
    private static final long GAME_DATA_CFF_V11_FILE_LENGTH = 39442356L;

    private FileUtils() {

    }

    public static void uploadDataIntoDatabase() {
        RandomAccessFile file = createTemporaryModificationFile();
        DataFilesParser.INSTANCE.extractAllDataFromFile(file);

        try {
            boolean isVersion11 = file.length() == GAME_DATA_CFF_V11_FILE_LENGTH;
            FileStorageService.INSTANCE.setIsVersion11(isVersion11);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        FileStorageObject fileStorage = FileStorageService.INSTANCE.getFileStorage();
        String[] split = fileStorage.pathToGameDataCff.split("/");
        String originalFileName = split[split.length - 1];
        String originalFileDirectory = fileStorage.pathToGameDataCff.replaceAll(originalFileName, "");
        String modificationFileName = originalFileName + MOD_FILE_EXTENSION;
        try {
            file.close();
            Files.delete(Paths.get(originalFileDirectory + modificationFileName));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static RandomAccessFile createTemporaryModificationFile() {
        FileStorageObject fileStorage = FileStorageService.INSTANCE.getFileStorage();
        String[] split = fileStorage.pathToGameDataCff.split("/");
        String originalFileName = split[split.length - 1];
        String originalFileDirectory = fileStorage.pathToGameDataCff.replaceAll(originalFileName, "");
        String modificationFileName = originalFileName + MOD_FILE_EXTENSION;
        String modificationFileDirectory = originalFileDirectory + modificationFileName;

        if (fileStorage.pathToSFMod != null && !fileStorage.pathToSFMod.isEmpty()) {
            String originalFilePath = originalFileDirectory + originalFileName;
            String sfModificationFilePath = fileStorage.pathToSFMod;
            String tempExtractedFilePath = originalFilePath + TMP_FILE_EXTENSION;

            String password = getHashSHA_512(originalFileDirectory, originalFileName);
            AESDecrypter decrypter = new AESDecrypterBC();
            try {
                AesZipFileDecrypter aesZipFileDecrypter = new AesZipFileDecrypter(new File(sfModificationFilePath), decrypter);
                java.util.List<ExtZipEntry> list = aesZipFileDecrypter.getEntryList();
                // TODO technically zip-file consists of one ZipEntry
                for (ExtZipEntry extZipEntry : list) {
                    try {
                        aesZipFileDecrypter.extractEntry(extZipEntry, new File(tempExtractedFilePath), password);
                    } catch (DataFormatException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

            XDeltaEncoder.main(new String[]{"-d", originalFilePath, tempExtractedFilePath, modificationFileDirectory});

            try {
                Files.delete(Paths.get(tempExtractedFilePath));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        } else {
            Path originalFilePath = Paths.get(originalFileDirectory + originalFileName);
            Path modificationFilePath = Paths.get(originalFileDirectory + modificationFileName);
            try {
                Files.copy(originalFilePath, modificationFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        try {
            return new RandomAccessFile(originalFileDirectory + modificationFileName, "rw");
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    public static boolean isModificationFileBasedOnOriginalFile(String sfModificationFilePath) {
        FileStorageObject fileStorage = FileStorageService.INSTANCE.getFileStorage();
        String[] split = fileStorage.pathToGameDataCff.split("/");
        String originalFileName = split[split.length - 1];
        String originalFileDirectory = fileStorage.pathToGameDataCff.replaceAll(originalFileName, "");
        String originalFilePath = originalFileDirectory + originalFileName;
        String tempExtractedFilePath = originalFilePath + TMP_FILE_EXTENSION;

        String password = getHashSHA_512(originalFileDirectory, originalFileName);
        AESDecrypter decrypter = new AESDecrypterBC();
        try {
            AesZipFileDecrypter aesZipFileDecrypter = new AesZipFileDecrypter(new File(sfModificationFilePath), decrypter);
            java.util.List<ExtZipEntry> list = aesZipFileDecrypter.getEntryList();
            // TODO technically zip-file consists of one ZipEntry
            for (ExtZipEntry extZipEntry : list) {
                try {
                    aesZipFileDecrypter.extractEntry(extZipEntry, new File(tempExtractedFilePath), password);
                } catch (DataFormatException e) {
                    LOGGER.error(e.getMessage(), e);
                    return false;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        } finally {
            try {
                Files.delete(Paths.get(tempExtractedFilePath));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return true;
    }

    /**
     * Hashing file with SHA-512 algorithm (source code is taken from http://www.mkyong.com/java/java-sha-hashing-example/)
     *
     * @param filePath path to file, from which hash will be taken
     * @param fileName name of file, from which hash will be taken
     * @return hash as hex string, null is any error occurred
     */
    public static String getHashSHA_512(String filePath, String fileName) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            return null;

        }

        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath + fileName);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        byte[] dataBytes = new byte[1024];

        int nread;
        try {
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
