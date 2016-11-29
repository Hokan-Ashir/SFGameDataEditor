package sfgamedataeditor.views.fileselection;

import org.apache.log4j.Logger;
import sfgamedataeditor.files.FileData;
import sfgamedataeditor.files.FilesContainer;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class OriginalFileSelectorListener implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(OriginalFileSelectorListener.class);
    private static final String CFF_FILE_EXTENSION = "cff";
    private final FileSelectionView view;


    public OriginalFileSelectorListener(FileSelectionView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(I18N.INSTANCE.getMessage("cffFilesDescription"), CFF_FILE_EXTENSION);
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(view.getMainPanel());
        File selectedFile = chooser.getSelectedFile();
        if (selectedFile == null) {
            view.getOkButton().setEnabled(FilesContainer.INSTANCE.getOriginalFile() != null);
            return;
        }

        view.getOriginalFileField().setText(selectedFile.getAbsolutePath());
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
            view.getOkButton().setEnabled(false);
            return;
        }

        FilesContainer.INSTANCE.setOriginalFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));
        view.getOkButton().setEnabled(true);
    }
}
