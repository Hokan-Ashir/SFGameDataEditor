package sfgamedataeditor.common.widgets.spells.summonedcreature;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class SummonedCreatureWidgetListener extends AbstractWidgetListener<SummonnedCreatureWidget, OffsetableObject> implements ItemListener, ActionListener {

    public SummonedCreatureWidgetListener(SummonnedCreatureWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        setWidgetValueToDTOField();
    }

    @Override
    protected int[] getFieldValues() {
        return new int[0];
    }

    @Override
    protected void setFieldValues(int[] value) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreatureParameterObject creatureParameterObject = getSelectedSpellParameterObject();
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(creatureParameterObject);
        CreaturesParametersModel model = new CreaturesParametersModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(CreaturesParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private CreatureParameterObject getSelectedSpellParameterObject() {
        return null;
    }
}
