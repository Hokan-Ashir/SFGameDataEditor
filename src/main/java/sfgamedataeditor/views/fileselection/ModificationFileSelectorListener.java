package sfgamedataeditor.views.fileselection;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.utils.NotificationType;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ModificationFileSelectorListener implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(ModificationFileSelectorListener.class);
    private static final String SFMOD_FILE_EXTENSION = "sfmod";
    private final FileSelectionView view;

    public ModificationFileSelectorListener(FileSelectionView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(I18N.INSTANCE.getMessage("sfmodFilesDescription"), SFMOD_FILE_EXTENSION);
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        JPanel mainPanel = view.getMainPanel();
        chooser.showOpenDialog(mainPanel);
        File selectedFile = chooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }

        ViewTools.setComponentsEnableStatus(mainPanel, false);
        if (!FileUtils.isModificationFileBasedOnOriginalFile(selectedFile.getPath())) {
            String errorMessage = I18N.INSTANCE.getMessage("sfmodFilePrefix") + selectedFile.getName() + I18N.INSTANCE.getMessage("basedOnAnotherCffFile");
            new Notification(errorMessage, NotificationType.ERROR);
            ViewTools.setComponentsEnableStatus(mainPanel, true);
            return;
        }
        ViewTools.setComponentsEnableStatus(mainPanel, true);

        view.getModificationFileField().setText(selectedFile.getAbsolutePath());
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return;
        }

        FilesContainer.INSTANCE.setModificationFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));
    }
}
