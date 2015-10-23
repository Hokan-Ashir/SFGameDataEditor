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

public class FileSelectionView implements IView {
    private JPanel mainPanel;
    private JTextField originalFileField;
    private JLabel originalFileLabel;
    private JButton originalFileSelectorButton;
    private JLabel modificationFileLabel;
    private JTextField modificationFileField;
    private JButton modificationFileSelectorButton;
    private JButton okButton;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("SpellForce GameData.cff Editor : File Selection Dialog");
        final FileSelectionView view = new FileSelectionView();
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        view.getOkButton().setEnabled(false);
        addOpenParametersEditorListener(frame, view);
        addOriginalFileSelectionListener(view);
        addModificationFileSelectionListener(view);
    }

    private static void addOriginalFileSelectionListener(final FileSelectionView view) {
        view.getOriginalFileSelectorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileFilter fileFilter = new FileNameExtensionFilter("Cff (SpellForce Gamedata files)", "cff");
                chooser.setFileFilter(fileFilter);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.showOpenDialog(view.getMainPanel());
                File selectedFile = chooser.getSelectedFile();
                if (selectedFile == null) {
                    view.getOkButton().setEnabled(FilesContainer.getOriginalFile() != null);
                    return;
                }

                view.getOriginalFileField().setText(selectedFile.getAbsolutePath());
                RandomAccessFile file;
                try {
                    file = new RandomAccessFile(selectedFile.getAbsolutePath(), "rw");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    view.getOkButton().setEnabled(false);
                    return;
                }

                FilesContainer.setOriginalFile(file);
                view.getOkButton().setEnabled(true);
            }
        });
    }

    private static void addModificationFileSelectionListener(final FileSelectionView view) {
        view.getModificationFileSelectorButton().addActionListener(new ActionListener() {
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

                view.getModificationFileField().setText(selectedFile.getAbsolutePath());
                RandomAccessFile file;
                try {
                    file = new RandomAccessFile(selectedFile.getAbsolutePath(), "rw");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }

                FilesContainer.setModificationFile(file);
            }
        });
    }

    private static void addOpenParametersEditorListener(final JFrame frame,
            final FileSelectionView view) {
        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getOkButton().setText("Processing data. Please wait ...");
                view.getOkButton().setEnabled(false);
                view.getOriginalFileSelectorButton().setEnabled(false);
                view.getModificationFileSelectorButton().setEnabled(false);
                view.getMainPanel().paintImmediately(view.getMainPanel().getBounds());
                MainView.showMainView();
                frame.dispose();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getOriginalFileSelectorButton() {
        return originalFileSelectorButton;
    }

    public JButton getModificationFileSelectorButton() {
        return modificationFileSelectorButton;
    }

    public JTextField getOriginalFileField() {
        return originalFileField;
    }

    public JTextField getModificationFileField() {
        return modificationFileField;
    }
}
