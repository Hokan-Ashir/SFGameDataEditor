package sfgamedataeditor.dataextraction;

import org.mantlik.xdeltaencoder.XDeltaEncoder;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FilesContainer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    private FileUtils() {

    }

    public static boolean createTemporaryModificationFile() {
        String originalFileDirectory = FilesContainer.getOriginalFilePath();
        String originalFileName = FilesContainer.getOriginalFileName();
        String modificationFileName = originalFileName + ".mod";
        String resultFilePath = originalFileDirectory + modificationFileName;

        if (FilesContainer.getModificationFile() != null) {
            String originalFilePath = originalFileDirectory + originalFileName;
            String modificationFilePath = FilesContainer.getModificationFilePath() + FilesContainer.getModificationFileName();

            XDeltaEncoder.main(new String[]{"-d", originalFilePath, modificationFilePath, resultFilePath});
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
}
