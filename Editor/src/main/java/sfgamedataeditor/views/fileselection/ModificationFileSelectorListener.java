package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;
import sfgamedataeditor.views.utility.notification.NotificationType;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class ModificationFileSelectorListener implements ActionListener {

    private static final String SFMOD_FILE_EXTENSION = "sfmod";
    private final FileSelectionView view;

    ModificationFileSelectorListener(FileSelectionView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilesDescription"), SFMOD_FILE_EXTENSION);
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
            String errorMessage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilePrefix")
                    + selectedFile.getName()
                    + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "basedOnAnotherCffFile");
            new Notification(errorMessage, NotificationType.ERROR);
            ViewTools.setComponentsEnableStatus(mainPanel, true);
            return;
        }
        ViewTools.setComponentsEnableStatus(mainPanel, true);

        view.getModificationFileField().setText(selectedFile.getAbsolutePath());
    }
}
