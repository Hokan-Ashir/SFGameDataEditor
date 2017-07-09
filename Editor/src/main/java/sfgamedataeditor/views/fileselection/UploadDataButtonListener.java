package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.database.file.storage.FileStorageObject;
import sfgamedataeditor.database.file.storage.FileStorageService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.main.modules.common.localization.LocalizationView;
import sfgamedataeditor.views.main.modules.common.search.SearchView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UploadDataButtonListener implements ActionListener {

    private final JFrame frame;
    private final FileSelectionView view;

    UploadDataButtonListener(JFrame frame, FileSelectionView view) {
        this.frame = frame;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton okButton = view.getOkButton();
        JPanel mainPanel = view.getMainPanel();
        ViewTools.setComponentsEnableStatus(mainPanel, false);
        repaintButtonTextContent(okButton, frame, mainPanel, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingData"));

        FileStorageObject storageObject = FileStorageService.INSTANCE.getFileStorage();
        storageObject.pathToGameDataCff = view.getOriginalFileField().getText();
        storageObject.pathToSFMod = view.getModificationFileField().getText();
        FileStorageService.INSTANCE.setFileStorage(storageObject);

        FileUtils.uploadDataIntoDatabase();
        showInitialViews();

        frame.dispose();
    }

    private void showInitialViews() {
        EventProcessor.INSTANCE.process(new ShowViewEvent(MainView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(EventHistoryView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(SearchView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ButtonsView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(LocalizationView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ModulesView.class, null));
    }

    private void repaintButtonTextContent(JButton button, JFrame frame, JPanel panel, String content) {
        button.setText(content);
        int fontSize = button.getFont().getSize();
        Dimension size = new Dimension(button.getText().length() * fontSize,
                button.getPreferredSize().height);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);

        frame.pack();
        panel.paintImmediately(panel.getBounds());
    }
}
