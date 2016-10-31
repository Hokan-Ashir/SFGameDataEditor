package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
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

public class SkillParameterView extends AbstractView implements Processable<SkillParametersMetaEvent> {

    private final SkillParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    private SkillEventParameter parameter;
    // TODO merge levelView into SkillParameterView
    private final LevelableView<SkillSchoolsView> view = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(LevelableView.class);
    private final SkillSchoolsView skillSchoolsView = (SkillSchoolsView) ViewRegister.INSTANCE.getView(SkillSchoolsView.class);

    public SkillParameterView() {
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

    public Collection<IDataField> getDataFields() {
        return dataFields;
    }

    public JComboBox<String> getLevelComboBox() {
        return view.getLevelComboBox();
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
