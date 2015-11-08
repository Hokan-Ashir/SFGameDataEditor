package sfgamedataeditor.dataextraction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    private HashUtils() {

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
