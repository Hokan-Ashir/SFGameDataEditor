package sfgamedataeditor.databind.files;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.*;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.database.objects.ObjectDataMappingService;
import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.database.objects.SpellParameters;
import xdeltaencoder.org.mantlik.xdeltaencoder.XDeltaEncoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.ZipException;

public final class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class);

    private static final String TMP_FILE_EXTENSION = ".tmp";
    private static final String SFMOD_FILE_EXTENSION = ".sfmod";
    private static final String MOD_FILE_EXTENSION = ".mod";
    private static final int KEY_SIZE = 192;
    private static final String HASH_ALGORITHM = "SHA-512";

    private FileUtils() {

    }

    public static boolean createTemporaryModificationFile() {
        String originalFileDirectory = FilesContainer.INSTANCE.getOriginalFilePath();
        String originalFileName = FilesContainer.INSTANCE.getOriginalFileName();
        String modificationFileName = originalFileName + MOD_FILE_EXTENSION;
        String modificationFileDirectory = originalFileDirectory + modificationFileName;

        if (FilesContainer.INSTANCE.getModificationFile() != null) {
            String originalFilePath = originalFileDirectory + originalFileName;
            String sfModificationFilePath = FilesContainer.INSTANCE.getModificationFilePath() + FilesContainer.INSTANCE.getModificationFileName();
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
                return false;
            }
        }

        RandomAccessFile file;
        try {
            file = new RandomAccessFile(originalFileDirectory + modificationFileName, "rw");
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        FilesContainer.INSTANCE.setModificationFile(new FileData(file, originalFileDirectory, modificationFileName));

        return true;
    }

    public static void createSfModFile(String sfModFileName) {
        dropDatabaseChangesIntoModificationFile();
        String originalFileDirectory = FilesContainer.INSTANCE.getOriginalFilePath();
        String originalFileName = FilesContainer.INSTANCE.getOriginalFileName();
        String originalFilePath = originalFileDirectory + originalFileName;
        String tempDiffFilePath = originalFilePath + TMP_FILE_EXTENSION;

        String modificationFilePath = FilesContainer.INSTANCE.getModificationFilePath() + FilesContainer.INSTANCE.getModificationFileName();

        XDeltaEncoder.main(new String[]{originalFilePath, modificationFilePath, tempDiffFilePath});

        zipAndEncryptDiffFile(sfModFileName, originalFileDirectory, originalFileName, tempDiffFilePath);

        try {
            Files.delete(Paths.get(tempDiffFilePath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void dropDatabaseChangesIntoModificationFile() {
        RandomAccessFile modificationFile = FilesContainer.INSTANCE.getModificationFile();
        List<SkillParameters> allSkillParameters = TableCreationUtils.getAllSkillParameters();
        for (SkillParameters allSkillParameter : allSkillParameters) {
            Long offset = allSkillParameter.getOffset();
            try {
                modificationFile.seek(offset);
                byte[] bytes = ObjectDataMappingService.INSTANCE.serializeObject(allSkillParameter);
                modificationFile.write(bytes);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        List<SpellParameters> allSpellParameters = TableCreationUtils.getAllSpellParameters();
        for (SpellParameters allSpellParameter : allSpellParameters) {
            Long offset = allSpellParameter.getOffset();
            try {
                modificationFile.seek(offset);
                byte[] bytes = ObjectDataMappingService.INSTANCE.serializeObject(allSpellParameter);
                modificationFile.write(bytes);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

    private static void zipAndEncryptDiffFile(String sfModFileName, String originalFileDirectory, String originalFileName, String tempDiffFilePath) {
        AESEncrypter encrypter = new AESEncrypterBC();
        String password = getHashSHA_512(originalFileDirectory, originalFileName);
        try {
            encrypter.init(password, KEY_SIZE);
        } catch (ZipException e) {
            LOGGER.error(e.getMessage(), e);
        }

        sfModFileName += SFMOD_FILE_EXTENSION;
        String resultFilePath = originalFileDirectory + sfModFileName;
        try {
            AesZipFileEncrypter.zipAndEncrypt(new File(tempDiffFilePath), new File(resultFilePath), password, encrypter);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static boolean isModificationFileBasedOnOriginalFile(String sfModificationFilePath) {
        String originalFileDirectory = FilesContainer.INSTANCE.getOriginalFilePath();
        String originalFileName = FilesContainer.INSTANCE.getOriginalFileName();
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
    private static String getHashSHA_512(String filePath, String fileName) {
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
