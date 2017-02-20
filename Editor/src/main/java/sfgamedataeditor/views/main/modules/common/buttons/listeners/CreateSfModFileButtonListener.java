package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.files.FileUtils;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSfModFileButtonListener implements ActionListener {

    private final MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final String sfModFileName = JOptionPane.showInputDialog(null, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "newSfModFileName"), I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "newSfModFileCreationCaption"), JOptionPane.QUESTION_MESSAGE);
        if (sfModFileName == null || sfModFileName.isEmpty()) {
            return;
        }

        String notificationMassage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingSfModFile") + sfModFileName + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "processingCreation")
                + "\n" + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "closeMessageWindowProposition");
        new Notification(notificationMassage);
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);

        FileUtils.createSfModFile(sfModFileName);
        String successfulMessage = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilePrefix") + sfModFileName + I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "successfullyCreated");
        new Notification(successfulMessage);

        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
    }
}
