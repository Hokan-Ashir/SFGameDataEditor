package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESEncrypter;
import de.idyl.winzipaes.impl.AESEncrypterBC;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.file.storage.FileStorageObject;
import sfgamedataeditor.database.file.storage.FileStorageService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.files.DataFilesParser;
import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;
import xdeltaencoder.org.mantlik.xdeltaencoder.XDeltaEncoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipException;

public class CreateSfModFileButtonListener implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(CreateSfModFileButtonListener.class);
    private static final String SFMOD_FILE_EXTENSION = ".sfmod";
    private static final int KEY_SIZE = 192;
    private final MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final String sfModFileName = JOptionPane.showInputDialog(null, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "newSfModFileName"), I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "newSfModFileCreationCaption"), JOptionPane.QUESTION_MESSAGE);
        if (sfModFileName == null || sfModFileName.isEmpty()) {
            return;
        }

        String notificationMassage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingSfModFile") + sfModFileName + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingCreation")
                + "\n" + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "closeMessageWindowProposition");
        new Notification(notificationMassage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);

        createSfModFile(sfModFileName);
        String successfulMessage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilePrefix") + sfModFileName + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "successfullyCreated");
        new Notification(successfulMessage);

        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
    }

    public void createSfModFile(String sfModFileName) {
        DataFilesParser.INSTANCE.dropDatabaseChangesIntoModificationFile();

        FileStorageObject fileStorage = FileStorageService.INSTANCE.getFileStorage();
        String[] split = fileStorage.pathToGameDataCff.split("/");
        String originalFileName = split[split.length - 1];
        String originalFileDirectory = fileStorage.pathToGameDataCff.replaceAll(originalFileName, "");
        String originalFilePath = originalFileDirectory + originalFileName;
        String tempDiffFilePath = originalFilePath + FileUtils.TMP_FILE_EXTENSION;
        String modificationFileName = originalFileName + FileUtils.MOD_FILE_EXTENSION;
        String modificationFilePath = originalFileDirectory + modificationFileName;

        XDeltaEncoder.main(new String[]{originalFilePath, modificationFilePath, tempDiffFilePath});

        zipAndEncryptDiffFile(sfModFileName, originalFileDirectory, originalFileName, tempDiffFilePath);

        try {
            Files.delete(Paths.get(tempDiffFilePath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            Files.delete(Paths.get(modificationFilePath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void zipAndEncryptDiffFile(String sfModFileName, String originalFileDirectory, String originalFileName, String tempDiffFilePath) {
        AESEncrypter encrypter = new AESEncrypterBC();
        String password = FileUtils.getHashSHA_512(originalFileDirectory, originalFileName);
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
}
