package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SkillParameterView extends AbstractView<SkillSchoolsView> {

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

        long skillParametersOffset = getSkillParametersOffset(parameter.getSkillSchoolId(), parameter.getSkillLevel());
        setFieldsData(skillParametersOffset);
    }

    private long getSkillParametersOffset(int skillSchoolId, int skillLevel) {
        long skillParametersOffset = 0L;
        Map<Integer, List<Pair<Integer, Long>>> skillSchoolsOffsets = OffsetProvider.INSTANCE.getSkillSchoolsOffsets();
        for (Pair<Integer, Long> integerLongPair : skillSchoolsOffsets.get(skillSchoolId)) {
            if (integerLongPair.getKey() == skillLevel) {
                skillParametersOffset = integerLongPair.getValue();
                break;
            }
        }

        return skillParametersOffset;
    }

    private void setFieldsData(long skillParametersOffset) {
        for (IDataField dataField : dataFields) {
            dataField.setOffsetInBytes(skillParametersOffset);
            dataField.loadFromFile();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }
}
