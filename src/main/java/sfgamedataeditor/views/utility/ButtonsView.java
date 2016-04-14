package sfgamedataeditor.views.utility;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.FileSelectionView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.Containerable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ButtonsView<T extends AbstractView & Containerable> extends AbstractView<T> {
    private static final Logger LOGGER = Logger.getLogger(ButtonsView.class);

    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JPanel mainPanel;

    public ButtonsView(T parentView) {
        super(parentView);
        loadSfmodFileButton.setText(I18N.getMessage("loadSfModFileButtonCaption"));
        createSfmodFileButton.setText(I18N.getMessage("createSfModFileButtonCaption"));

        attachLoadSfmodFileButtonListener();
        attachCreateSfmodFileButtonListener();
    }

    private void attachCreateSfmodFileButtonListener() {
        createSfmodFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String sfModFileName = JOptionPane.showInputDialog(null, I18N.getMessage("newSfModFileName"), I18N.getMessage("newSfModFileCreationCaption"), JOptionPane.QUESTION_MESSAGE);
                if (sfModFileName != null && !sfModFileName.isEmpty()) {
                    String notificationMassage = I18N.getMessage("processingSfModFile") + sfModFileName + I18N.getMessage("processingCreation")
                            + "\n" + I18N.getMessage("closeMessageWindowProposition");
                    new Notification(notificationMassage);
                    ViewTools.setComponentsEnableStatus(getParentView().getSubModulesPanel(), false);

                    FileUtils.createSfModFile(sfModFileName);
                    String successfulMessage = I18N.getMessage("sfmodFilePrefix") + sfModFileName + I18N.getMessage("successfullyCreated");
                    new Notification(successfulMessage);

                    ViewTools.setComponentsEnableStatus(getParentView().getSubModulesPanel(), true);
                }
            }
        });
    }

    private void attachLoadSfmodFileButtonListener() {
        loadSfmodFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileFilter fileFilter = new FileNameExtensionFilter(
                        I18N.getMessage("sfmodFilesDescription"), FileSelectionView.SFMOD_FILE_EXTENSION);
                chooser.setFileFilter(fileFilter);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.showOpenDialog(getParentView().getSubModulesPanel());
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

                String notificationMassage = I18N.getMessage("processingSfModFile") + selectedFile.getName() + I18N.getMessage("processingLoading")
                        + "\n" + I18N.getMessage("closeMessageWindowProposition");
                new Notification(notificationMassage);
                ViewTools.setComponentsEnableStatus(getParentView().getSubModulesPanel(), false);

                FileUtils.createTemporaryModificationFile();
                getParentView().updateData();

                String successfulMessage = I18N.getMessage("sfmodFilePrefix") + FilesContainer.INSTANCE.getModificationFileName() + I18N.getMessage("successfullyLoaded");
                new Notification(successfulMessage);
                ViewTools.setComponentsEnableStatus(getParentView().getSubModulesPanel(), true);
            }
        });
    }

    @Override
    public void show() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
