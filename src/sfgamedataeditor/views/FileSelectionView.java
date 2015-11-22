package sfgamedataeditor.views;

import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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
                    file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    view.getOkButton().setEnabled(false);
                    return;
                }

                FilesContainer.setOriginalFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));
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

                setComponentsEnableStatus(view.getMainPanel(), false);
                view.getMainPanel().paintImmediately(view.getMainPanel().getBounds());
                if (!FileUtils.isModificationFileBasedOnOriginalFile(selectedFile.getPath())) {
                    JOptionPane.showMessageDialog(null, "Sfmod-file \"" + selectedFile.getName() + "\" based on other original GameData.cff file", "Error", JOptionPane.ERROR_MESSAGE);
                    setComponentsEnableStatus(view.getMainPanel(), true);
                    view.getMainPanel().paintImmediately(view.getMainPanel().getBounds());
                    return;
                }
                setComponentsEnableStatus(view.getMainPanel(), true);
                view.getMainPanel().paintImmediately(view.getMainPanel().getBounds());

                view.getModificationFileField().setText(selectedFile.getAbsolutePath());
                RandomAccessFile file;
                try {
                    file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }

                FilesContainer.setModificationFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));
            }
        });
    }

    private static void addOpenParametersEditorListener(final JFrame frame,
            final FileSelectionView view) {
        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton okButton = view.getOkButton();
                okButton.setEnabled(false);
                view.getOriginalFileSelectorButton().setEnabled(false);
                view.getModificationFileSelectorButton().setEnabled(false);
                repaintButtonTextContent(okButton, "Creating temporary modification file. Please wait ...");
                boolean creationSuccess = FileUtils.createTemporaryModificationFile();
                if (!creationSuccess) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Can't load create temporary modification file", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    okButton.setEnabled(true);
                    view.getOriginalFileSelectorButton().setEnabled(true);
                    view.getModificationFileSelectorButton().setEnabled(true);
                    repaintButtonTextContent(okButton, "OK");
                    return;
                }

                repaintButtonTextContent(okButton, "Processing data. Please wait ...");
                MainView.showMainView();
                frame.dispose();
            }

            private void repaintButtonTextContent(JButton okButton, String content) {
                okButton.setText(content);
                int fontSize = okButton.getFont().getSize();
                okButton.setPreferredSize(new Dimension(okButton.getText().length() * fontSize,
                        okButton.getPreferredSize().height));

                frame.pack();
                view.getMainPanel().paintImmediately(view.getMainPanel().getBounds());
            }
        });
    }

    private static void setComponentsEnableStatus(JPanel panel, boolean isEnabled) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel) {
                setComponentsEnableStatus((JPanel) component, isEnabled);
            }

            component.setEnabled(isEnabled);
        }
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
