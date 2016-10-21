package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSfModFileButtonListener implements ActionListener {

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
