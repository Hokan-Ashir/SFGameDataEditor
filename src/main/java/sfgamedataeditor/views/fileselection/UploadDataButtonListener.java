package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.MainViewMetaEvent;
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
        MainViewMetaEvent event = new MainViewMetaEvent();
        EventHandlerRegister.INSTANCE.fireEventSilently(event);
        frame.dispose();
    }
}
