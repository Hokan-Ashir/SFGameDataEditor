package sfgamedataeditor.views;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.utils.NotificationType;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileSelectionView implements IView {
    public static final String CFF_FILE_EXTENSION = "cff";
    public static final String SFMOD_FILE_EXTENSION = "sfmod";
    private static final Logger LOGGER = Logger.getLogger(FileSelectionView.class);
    private JPanel mainPanel;
    private JTextField originalFileField;
    private JLabel originalFileLabel;
    private JButton originalFileSelectorButton;
    private JLabel modificationFileLabel;
    private JTextField modificationFileField;
    private JButton modificationFileSelectorButton;
    private JButton okButton;

    public FileSelectionView() {
        originalFileLabel.setText(I18N.getMessage("fileSelectionWindowOriginalFileTextFieldCaption"));
        modificationFileLabel.setText(I18N.getMessage("fileSelectionWindowModificationFileTextFieldCaption"));
        okButton.setText(I18N.getMessage("ok"));
    }

    public static void showFileSelectionView() {
        final JFrame frame = new JFrame(I18N.getMessage("fileSelectionWindowCaption"));
        final FileSelectionView view = new FileSelectionView();
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);

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
                FileFilter fileFilter = new FileNameExtensionFilter(I18N.getMessage("cffFilesDescription"), CFF_FILE_EXTENSION);
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
                } catch (FileNotFoundException ex) {
                    LOGGER.error(ex.getMessage(), ex);
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
                FileFilter fileFilter = new FileNameExtensionFilter(I18N.getMessage("sfmodFilesDescription"), SFMOD_FILE_EXTENSION);
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
                    String errorMessage = I18N.getMessage("sfmodFilePrefix") + selectedFile.getName() + I18N.getMessage("basedOnAnotherCffFile");
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
                JPanel mainPanel = view.getMainPanel();
                ViewTools.setComponentsEnableStatus(mainPanel, false);
                ViewTools.repaintButtonTextContent(okButton, frame, mainPanel, I18N.getMessage("temporaryModificationFileCreation"));
                boolean creationSuccess = FileUtils.createTemporaryModificationFile();
                if (!creationSuccess) {
                    String errorMessage = I18N.getMessage("temporaryModificationFileCreationError");
                    new Notification(errorMessage, NotificationType.ERROR);
                    ViewTools.setComponentsEnableStatus(mainPanel, true);
                    ViewTools.repaintButtonTextContent(okButton, frame, mainPanel, I18N.getMessage("ok"));
                    return;
                }

                ViewTools.repaintButtonTextContent(okButton, frame, mainPanel, I18N.getMessage("processingData"));
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
