package sfgamedataeditor.databind.files;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.*;
import org.mantlik.xdeltaencoder.XDeltaEncoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;
import java.util.zip.ZipException;

public class FileUtils {

    public static final String TMP_FILE_EXTENSION = ".tmp";
    public static final String SFMOD_FILE_EXTENSION = ".sfmod";
    public static final String MOD_FILE_EXTENSION = ".mod";
    public static final int KEY_SIZE = 192;

    private FileUtils() {

    }

    public static boolean createTemporaryModificationFile() {
        String originalFileDirectory = FilesContainer.getOriginalFilePath();
        String originalFileName = FilesContainer.getOriginalFileName();
        String modificationFileName = originalFileName + MOD_FILE_EXTENSION;
        String modificationFileDirectory = originalFileDirectory + modificationFileName;

        if (FilesContainer.getModificationFile() != null) {
            String originalFilePath = originalFileDirectory + originalFileName;
            String sfModificationFilePath = FilesContainer.getModificationFilePath() + FilesContainer.getModificationFileName();
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
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            XDeltaEncoder.main(new String[]{"-d", originalFilePath, tempExtractedFilePath, modificationFileDirectory});

            try {
                Files.delete(Paths.get(tempExtractedFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Path originalFilePath = Paths.get(originalFileDirectory + originalFileName);
            Path modificationFilePath = Paths.get(originalFileDirectory + modificationFileName);
            try {
                Files.copy(originalFilePath, modificationFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
        }

        RandomAccessFile file;
        try {
            file = new RandomAccessFile(originalFileDirectory + modificationFileName, "rw");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }

        FilesContainer.setModificationFile(new FileData(file, originalFileDirectory, modificationFileName));

        return true;
    }

    public static void createSfModFile(String sfModFileName) {
        String originalFileDirectory = FilesContainer.getOriginalFilePath();
        String originalFileName = FilesContainer.getOriginalFileName();
        String originalFilePath = originalFileDirectory + originalFileName;
        String tempDiffFilePath = originalFilePath + TMP_FILE_EXTENSION;

        String modificationFilePath = FilesContainer.getModificationFilePath() + FilesContainer.getModificationFileName();

        XDeltaEncoder.main(new String[]{originalFilePath, modificationFilePath, tempDiffFilePath});

        zipAndEncryptDiffFile(sfModFileName, originalFileDirectory, originalFileName, tempDiffFilePath);

        try {
            Files.delete(Paths.get(tempDiffFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipAndEncryptDiffFile(String sfModFileName, String originalFileDirectory, String originalFileName, String tempDiffFilePath) {
        AESEncrypter encrypter = new AESEncrypterBC();
        String password = getHashSHA_512(originalFileDirectory, originalFileName);
        try {
            encrypter.init(password, KEY_SIZE);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        sfModFileName += SFMOD_FILE_EXTENSION;
        String resultFilePath = originalFileDirectory + sfModFileName;
        try {
            AesZipFileEncrypter.zipAndEncrypt(new File(tempDiffFilePath), new File(resultFilePath), password, encrypter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isModificationFileBasedOnOriginalFile(String sfModificationFilePath) {
        String originalFileDirectory = FilesContainer.getOriginalFilePath();
        String originalFileName = FilesContainer.getOriginalFileName();
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
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                Files.delete(Paths.get(tempExtractedFilePath));
            } catch (IOException e) {
                e.printStackTrace();
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
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }

        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        byte[] dataBytes = new byte[1024];

        int nread;
        try {
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
