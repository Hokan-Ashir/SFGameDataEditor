package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
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
        repaintButtonTextContent(okButton, frame, mainPanel, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingData"));
        FileUtils.uploadDataIntoDatabase();
        EventProcessor.INSTANCE.process(new ShowViewEvent(MainView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(EventHistoryView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ButtonsView.class, null));
        EventProcessor.INSTANCE.process(new ShowViewEvent(ModulesView.class, null));
        frame.dispose();
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
