package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateModificatedFileButtonListener implements ActionListener {

    private final MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new Notification(I18N.INSTANCE.getMessage("temporaryModificationFileCreation"));
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);
        FileUtils.dropDatabaseChangesIntoModificationFile();
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
        new Notification(I18N.INSTANCE.getMessage("temporaryModificationFileCreationSuccess"));
    }
}
