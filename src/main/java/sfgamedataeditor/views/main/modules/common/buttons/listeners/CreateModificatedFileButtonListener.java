package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.utility.ViewTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateModificatedFileButtonListener implements ActionListener {

    private ModulesView modulesView = (ModulesView) ViewRegister.INSTANCE.getView(new ClassTuple<>(ModulesView.class, MainView.class));

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new Notification(I18N.INSTANCE.getMessage("temporaryModificationFileCreation"));
        ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), false);
        FileUtils.dropDatabaseChangesIntoModificationFile();
        ViewTools.setComponentsEnableStatus(modulesView.getSubModulesPanel(), true);
        new Notification(I18N.INSTANCE.getMessage("temporaryModificationFileCreationSuccess"));
    }
}
