package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSfModFileButtonListener implements ActionListener {

    private MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

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
            ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);

            FileUtils.createSfModFile(sfModFileName);
            String successfulMessage = I18N.INSTANCE.getMessage("sfmodFilePrefix") + sfModFileName + I18N.INSTANCE.getMessage("successfullyCreated");
            new Notification(successfulMessage);

            ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
        }
    }
}
