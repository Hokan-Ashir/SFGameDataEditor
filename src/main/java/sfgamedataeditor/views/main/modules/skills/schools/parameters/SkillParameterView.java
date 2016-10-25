package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.database.tableservices.SkillParametersTableService;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.Processable;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

public class SkillParameterView extends AbstractView<SkillSchoolsView> implements Processable<SkillParametersMetaEvent> {

    private final SkillParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    private SkillEventParameter parameter;

    public SkillParameterView(SkillSchoolsView parentView) {
        super(parentView);
        this.stub = new SkillParameterViewStub();
        this.dataFields = FieldsWrapperCreator.createFieldWrappers(stub);

        final LevelableView<SkillSchoolsView> view = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SkillSchoolsView.class));
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                // TODO get rid of multiple event instances
                SkillParametersMetaEvent event = new SkillParametersMetaEvent();
                SkillEventParameter eventParameter = new SkillEventParameter(parameter.getSkillSchoolId(), view.getSelectedLevel());
                event.setEventParameter(ShowSkillParameterViewEvent.class, eventParameter);
                event.setEventParameter(SetModuleNameEvent.class, getParentView().getSelectedModuleValue());
                EventHandlerRegister.INSTANCE.fireEvent(event);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object o) {
        if (o != null) {
            // TODO get rid of class casting
            parameter = (SkillEventParameter) o;
        }

        setFieldsData(parameter.getSkillSchoolId(), parameter.getSkillLevel());
    }

    private void setFieldsData(int skillSchoolId, int skillLevel) {
        SkillParameters skillParameter = SkillParametersTableService.INSTANCE.getSkillParameter(skillSchoolId, skillLevel);
        for (IDataField dataField : dataFields) {
            dataField.mapValues(skillParameter);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SkillParametersMetaEvent> getMetaEventClass() {
        return SkillParametersMetaEvent.class;
    }
}
