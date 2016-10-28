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
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.List;

public class SkillParameterView extends AbstractView<MainView> implements Processable<SkillParametersMetaEvent> {

    private final SkillParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    private SkillEventParameter parameter;
    private final LevelableView<SkillSchoolsView> view = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, MainView.class));
    private final SkillSchoolsView skillSchoolsView = (SkillSchoolsView) ViewRegister.INSTANCE.getView(new ClassTuple(SkillSchoolsView.class, MainView.class));

    public SkillParameterView(MainView parentView) {
        super(parentView);
        this.stub = new SkillParameterViewStub();
        this.dataFields = FieldsWrapperCreator.createFieldWrappers(stub);

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
                event.setEventParameter(SetModuleNameEvent.class, skillSchoolsView.getSelectedModuleValue());
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

        fillPossibleSkillLevelsComboBox(parameter.getSkillSchoolId());
        setFieldsData(parameter.getSkillSchoolId(), parameter.getSkillLevel());
    }

    private void fillPossibleSkillLevelsComboBox(final int skillSchoolId) {
        final JComboBox<String> comboBox = view.getLevelComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                List<SkillParameters> skillPossibleValues = SkillParametersTableService.INSTANCE.getSkillPossibleValues(skillSchoolId);
                for (SkillParameters skillPossibleValue : skillPossibleValues) {
                    comboBox.addItem(String.valueOf(skillPossibleValue.level));
                }
            }
        });
    }

    private void setFieldsData(int skillSchoolId, int skillLevel) {
        SkillParameters skillParameter = SkillParametersTableService.INSTANCE.getSkillParameter(skillSchoolId, skillLevel);
        if (skillParameter != null) {
            for (IDataField dataField : dataFields) {
                dataField.mapValues(skillParameter);
            }
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
