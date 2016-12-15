package sfgamedataeditor.common.widgets.creatures.races;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.Set;

public class RacesWidgetListener extends AbstractWidgetListener<RacesWidget, OffsetableObject> implements ItemListener {

    public RacesWidgetListener(RacesWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getRacesComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        setWidgetValueToDTOField();
    }

    @Override
    protected int[] getFieldValues() {
        String selectedRaceName = (String) getWidget().getRacesComboBox().getSelectedItem();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.RACES);
        Set<String> keySet = bundle.keySet();

        int raceId = 0;
        for (String key : keySet) {
            if (bundle.getString(key).equals(selectedRaceName)) {
                raceId = Integer.parseInt(key);
                break;
            }
        }

        return new int[]{raceId};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int raceId = value[0];
        String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        getWidget().getRacesComboBox().setSelectedItem(raceName);
    }
}
