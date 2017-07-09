package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class OriginalFileSelectorListener implements ActionListener {

    private static final String CFF_FILE_EXTENSION = "cff";
    private final FileSelectionView view;


    OriginalFileSelectorListener(FileSelectionView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "cffFilesDescription"), CFF_FILE_EXTENSION);
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(view.getMainPanel());
        File selectedFile = chooser.getSelectedFile();
        if (selectedFile == null) {
            view.getOkButton().setEnabled(false);
            return;
        }

        view.getOriginalFileField().setText(selectedFile.getAbsolutePath());
        view.getOkButton().setEnabled(true);
    }
}
