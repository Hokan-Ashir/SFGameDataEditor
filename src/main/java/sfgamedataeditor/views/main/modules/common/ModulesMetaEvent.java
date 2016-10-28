package sfgamedataeditor.views.main.modules.common;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.buttons.ShowButtonsViewEvent;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.main.modules.common.eventhistory.ShowEventHistoryViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.common.modules.ShowModulesViewEvent;

public class ModulesMetaEvent extends AbstractViewableMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowEventHistoryViewEvent eventHistoryViewEvent = EventCreator.createEvent(EventHistoryView.class, MainView.class, ShowEventHistoryViewEvent.class);
        ShowModulesViewEvent modulesViewEvent = EventCreator.createEvent(ModulesView.class, MainView.class, ShowModulesViewEvent.class);
        ShowButtonsViewEvent buttonsViewEvent = EventCreator.createEvent(ButtonsView.class, MainView.class, ShowButtonsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(ModulesView.class, MainView.class);
        addEvents(eventHistoryViewEvent, modulesViewEvent, buttonsViewEvent, clearViewEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractViewableMetaEvent createParentMetaEvent() {
        return null;
    }
}
