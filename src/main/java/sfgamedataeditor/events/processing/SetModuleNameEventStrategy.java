package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.common.AbstractModulesView;

public class SetModuleNameEventStrategy implements EventProcessingStrategy<SetModuleNameEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(SetModuleNameEvent event) {
        final AbstractModulesView modulesView = event.getModulesView();
        modulesView.setModulesComboBoxValue(event.getModuleName());
    }
}
