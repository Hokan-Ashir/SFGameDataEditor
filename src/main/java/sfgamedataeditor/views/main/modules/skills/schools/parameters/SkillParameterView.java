package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.DataSavingUtils;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.listeners.TextFieldListener;
import sfgamedataeditor.utils.EntityTuple;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.LevelableView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillParameterView extends AbstractView<SkillSchoolsView> {

    private SkillEventParameter parameter;

    private JPanel mainPanel;
    private JTextField strengthField;
    private JLabel stregthLabel;
    private JTextField staminaField;
    private JLabel staminaLabel;
    private JTextField agilityField;
    private JLabel agilityLabel;
    private JLabel dexterityLabel;
    private JTextField dexterityField;
    private JLabel intelligenceLabel;
    private JTextField intelligenceField;
    private JLabel wisdomLabel;
    private JTextField wisdomField;
    private JLabel charismaLabel;
    private JTextField charismaField;
    private JPanel layoutPanel;
    private List<EntityTuple<JTextField>> entities = new ArrayList<>();
    private Map<JTextField, TextFieldListener> listenerMap = new HashMap<>();

    public SkillParameterView(SkillSchoolsView parentView) {
        super(parentView);
        final LevelableView<SkillSchoolsView> view = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, getParentView()));
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // TODO get rid of multiple event instances
                    ClassTuple tuple = new ClassTuple<>(SkillParameterView.class, getParentView());
                    ShowSkillParameterViewEvent event = new ShowSkillParameterViewEvent(tuple);
                    SkillEventParameter eventParameter = new SkillEventParameter(parameter.getSkillSchoolId(), view.getSelectedLevel());
                    event.setObjectParameter(eventParameter);
                    EventHandlerRegister.INSTANCE.fireEvent(event);
                }
            }
        });
        attachTextFieldListeners();
        setLabelsI18nNames();
    }

    private void setLabelsI18nNames() {
        stregthLabel.setText(I18N.INSTANCE.getMessage("strength"));
        staminaLabel.setText(I18N.INSTANCE.getMessage("stamina"));
        dexterityLabel.setText(I18N.INSTANCE.getMessage("dexterity"));
        agilityLabel.setText(I18N.INSTANCE.getMessage("agility"));
        intelligenceLabel.setText(I18N.INSTANCE.getMessage("intelligence"));
        wisdomLabel.setText(I18N.INSTANCE.getMessage("wisdom"));
        charismaLabel.setText(I18N.INSTANCE.getMessage("charisma"));
    }

    private void attachTextFieldListeners() {
        List<JTextField> fields = getViewFields();
        for (JTextField field : fields) {
            TextFieldListener listener = new TextFieldListener(field);
            field.getDocument().addDocumentListener(listener);
            listenerMap.put(field, listener);
        }
    }

    private void mapEntities(long blockOffset) {
        // TODO more common to use Map<JTextField, Integer> instead of list
        // but cause skill requirement fields are simply going one after another
        // with 1 byte offset, its easier to use list

        // skill requirements offset range is: 03F85FD4 - 03F864BF
        // with format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
        List<JTextField> fields = getViewFields();

        int incrementalOffset = 2;
        for (JTextField field : fields) {
            entities.add(new EntityTuple<>(field, blockOffset + incrementalOffset, 1));
            incrementalOffset++;
        }
    }

    private List<JTextField> getViewFields() {
        return new ArrayList<JTextField>() {{
            add(strengthField);
            add(staminaField);
            add(agilityField);
            add(dexterityField);
            add(charismaField);
            add(intelligenceField);
            add(wisdomField);
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object o) {
        // TODO get rid of class casting
        parameter = (SkillEventParameter) o;
        long skillParametersOffset = getSkillParametersOffset(parameter.getSkillSchoolId(), parameter.getSkillLevel());
        setFieldsData(skillParametersOffset);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setFieldsData(long skillParametersOffset) {
        mapEntities(skillParametersOffset);
        for (EntityTuple<JTextField> entity : entities) {
            int value = DataSavingUtils.loadDataFromFile(entity.getOffsetInBytes(), entity.getLengthInBytes());
            setFieldValue(entity, value);
            TextFieldListener listener = listenerMap.get(entity.getComponent());
            listener.setOffset(entity.getOffsetInBytes());
        }
    }

    private void setFieldValue(EntityTuple<JTextField> entity, int value) {
        JTextField field = entity.getComponent();
        TextFieldListener listener = listenerMap.get(field);
        field.getDocument().removeDocumentListener(listener);

        entity.getComponent().setText(String.valueOf(value));

        field.getDocument().addDocumentListener(listener);
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
}
