package sfgamedataeditor.views;

import sfgamedataeditor.databind.FilesContainer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public abstract class ModificationFileSelectionListener implements ActionListener {

    private IView view;

    public ModificationFileSelectionListener(IView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(
                "Sfmod (SpellForce modifications files)", "sfmod");
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(view.getMainPanel());
        File selectedFile = chooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }

        postLoadingActions();
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(selectedFile.getAbsolutePath(), "rw");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }

        FilesContainer.setModificationFile(file);
    }

    protected abstract void postLoadingActions();
}
