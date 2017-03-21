package sfgamedataeditor.views.main.modules.common.buttons.listeners;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.files.DataFilesParser;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateModificatedFileButtonListener implements ActionListener {

    private final MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "temporaryModificationFileCreation"));
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), false);
        DataFilesParser.INSTANCE.dropDatabaseChangesIntoModificationFile();
        ViewTools.setComponentsEnableStatus(mainView.getMainPanel(), true);
        new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "temporaryModificationFileCreationSuccess"));
    }
}
