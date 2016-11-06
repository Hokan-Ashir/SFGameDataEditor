package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadDataButtonListener implements ActionListener {

    private final JFrame frame;
    private final FileSelectionView view;

    public UploadDataButtonListener(JFrame frame, FileSelectionView view) {
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
        ViewTools.repaintButtonTextContent(okButton, frame, mainPanel, I18N.INSTANCE.getMessage("processingData"));
        FileUtils.uploadDataIntoDatabase();
        EventProcessor.INSTANCE.process(new ShowViewEvent(MainView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(EventHistoryView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ButtonsView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ModulesView.class, null));
        frame.dispose();
    }
}
