package sfgamedataeditor.views.main.modules.common.buttons;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.FileSelectionView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ButtonsView extends AbstractView<MainView> {
    private static final Logger LOGGER = Logger.getLogger(ButtonsView.class);

    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JPanel mainPanel;

    public ButtonsView(MainView parentView) {
        super(parentView);
        loadSfmodFileButton.setText(I18N.INSTANCE.getMessage("loadSfModFileButtonCaption"));
        createSfmodFileButton.setText(I18N.INSTANCE.getMessage("createSfModFileButtonCaption"));

        createSfmodFileButton.addActionListener(new CreateSfModFileButtonListener());
        loadSfmodFileButton.addActionListener(new LoadSfModFileButtonListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private final class CreateSfModFileButtonListener implements ActionListener {

        private ModulesView modulesView = (ModulesView) ViewRegister.INSTANCE.getView(new ClassTuple<>(ModulesView.class, MainView.class));

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            final String sfModFileName = JOptionPane.showInputDialog(null, I18N.INSTANCE.getMessage("newSfModFileName"), I18N.INSTANCE.getMessage("newSfModFileCreationCaption"), JOptionPane.QUESTION_MESSAGE);
            if (sfModFileName != null && !sfModFileName.isEmpty()) {
                String notificationMassage = I18N.INSTANCE.getMessage("processingSfModFile") + sfModFileName + I18N.INSTANCE.getMessage("processingCreation")
                        + "\n" + I18N.INSTANCE.getMessage("closeMessageWindowProposition");
                new Notification(notificationMassage);
                ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), false);

                FileUtils.createSfModFile(sfModFileName);
                String successfulMessage = I18N.INSTANCE.getMessage("sfmodFilePrefix") + sfModFileName + I18N.INSTANCE.getMessage("successfullyCreated");
                new Notification(successfulMessage);

                ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), true);
            }
        }
    }

    private final class LoadSfModFileButtonListener implements ActionListener {

        private ModulesView modulesView = (ModulesView) ViewRegister.INSTANCE.getView(new ClassTuple<>(ModulesView.class, MainView.class));

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileFilter fileFilter = new FileNameExtensionFilter(
                    I18N.INSTANCE.getMessage("sfmodFilesDescription"), FileSelectionView.SFMOD_FILE_EXTENSION);
            chooser.setFileFilter(fileFilter);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.showOpenDialog(modulesView.getSubModulesPanel());
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
            ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), false);

            FileUtils.createTemporaryModificationFile();
            updateAllCurrentViews();

            String successfulMessage = I18N.INSTANCE.getMessage("sfmodFilePrefix") + FilesContainer.INSTANCE.getModificationFileName() + I18N.INSTANCE.getMessage("successfullyLoaded");
            new Notification(successfulMessage);
            ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), true);
        }
    }

    private void updateAllCurrentViews() {
        OffsetProvider.INSTANCE.recreateAllMaps();
        // TODO make this use-case work:
        // user selected Fire/Fireball-1 and change its spell requirements to
        // Elemental magic/Ice-1, made sfmod-file, then load it,
        // cause all maps in Mappings class stays the same
        // Fireball-1's requrements still considered as Elemental magic/Fire-1
        MainView view = (MainView) ViewRegister.INSTANCE.getView(new ClassTuple<>(MainView.class, NullView.class));
        updateDataRecursively(view);
    }

    private <T extends AbstractView> void updateDataRecursively(AbstractView<T> parent) {
        parent.updateData(null);
        for (AbstractView<T> tAbstractView : parent.getChildren()) {
            updateDataRecursively(tAbstractView);
        }
    }
}
