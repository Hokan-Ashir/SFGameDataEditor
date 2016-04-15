package sfgamedataeditor.views.skills;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.DataSavingUtils;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.listeners.TextFieldListener;
import sfgamedataeditor.utils.EntityTuple;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.LevelableView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillParameterView extends AbstractView<SkillSchoolsView> {
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
    private LevelableView<SkillSchoolsView> view;
    private List<EntityTuple<JTextField>> entities = new ArrayList<>();
    private Map<JTextField, TextFieldListener> listenerMap = new HashMap<>();

    public SkillParameterView(SkillSchoolsView parentView) {
        super(parentView);
        view = new LevelableView<>(parentView);
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                show();
            }
        });
        attachTextFieldListeners();
        setLabelsI18nNames();
    }

    private void setLabelsI18nNames() {
        stregthLabel.setText(I18N.INSTANCE.getMessage("strength"));
        staminaField.setText(I18N.INSTANCE.getMessage("stamina"));
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

        int incrementalOffset = 0;
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
    public void show() {
        updateData();
        reattachView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData() {
        long skillParametersOffset = getSkillParametersOffset();
        setFieldsData(skillParametersOffset);
    }

    private void reattachView() {
        getParentView().getSubModulesPanel().removeAll();
        getParentView().getSubModulesPanel().add(view.getMainPanel());
        getParentView().getSubModulesPanel().add(mainPanel);
        getParentView().getSubModulesPanel().revalidate();
        getParentView().getSubModulesPanel().repaint();
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

    private long getSkillParametersOffset() {
        String selectedSkillSchool = (String) getParentView().getSelectedModuleValue();
        int selectedSchoolNumber = Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
        int selectedLevel = view.getSelectedLevel();
        long skillParametersOffset = 0L;
        Map<Integer, List<Pair<Integer, Long>>> skillSchoolsOffsets = OffsetProvider.INSTANCE.getSkillSchoolsOffsets();
        for (Pair<Integer, Long> integerLongPair : skillSchoolsOffsets.get(selectedSchoolNumber)) {
            if (integerLongPair.getKey() == selectedLevel) {
                skillParametersOffset = integerLongPair.getValue();
                break;
            }
        }

        return skillParametersOffset;
    }
}
