package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.files.FileData;
import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.files.FilesContainer;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class LoadSfModFileButtonListener implements ActionListener {

    private static final String SFMOD_FILE_EXTENSION = "sfmod";
    private static final Logger LOGGER = Logger.getLogger(LoadSfModFileButtonListener.class);

    private final MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(
                I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilesDescription"), SFMOD_FILE_EXTENSION);
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(mainView.getMainPanel());
        File selectedFile = chooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }

        RandomAccessFile file;
        try {
            file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return;
        }

        FilesContainer.INSTANCE.setModificationFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));

        String notificationMassage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingSfModFile")
                + selectedFile.getName()
                + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingLoading")
                + "\n" + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "closeMessageWindowProposition");
        new Notification(notificationMassage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);

        FileUtils.uploadDataIntoDatabase();
        // TODO check if this is necessarily
//        updateAllCurrentViews();

        String successfulMessage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilePrefix")
                + FilesContainer.INSTANCE.getModificationFileName()
                + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "successfullyLoaded");
        new Notification(successfulMessage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
    }
}


