package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;

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

    private MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(
                I18N.INSTANCE.getMessage("sfmodFilesDescription"), SFMOD_FILE_EXTENSION);
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

        String notificationMassage = I18N.INSTANCE.getMessage("processingSfModFile") + selectedFile.getName() + I18N.INSTANCE.getMessage("processingLoading")
                + "\n" + I18N.INSTANCE.getMessage("closeMessageWindowProposition");
        new Notification(notificationMassage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);

        FileUtils.uploadDataIntoDatabase();
        // TODO check if this is necessarily
//        updateAllCurrentViews();

        String successfulMessage = I18N.INSTANCE.getMessage("sfmodFilePrefix") + FilesContainer.INSTANCE.getModificationFileName() + I18N.INSTANCE.getMessage("successfullyLoaded");
        new Notification(successfulMessage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
    }
}


