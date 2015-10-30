package sfgamedataeditor.skills;

import sfgamedataeditor.databind.*;
import sfgamedataeditor.views.IView;

import javax.swing.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SkillRequirementView extends AbstractEntity implements IView {
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
    private JLabel skillNameLabel;
    private JSeparator skillNameSeparator;

    private List<Entity> entityList = new ArrayList<>();

    // TODO pass List<Skill - Level> offsets and make setLevel method to publicly use it from
    // SkillView class in level comboBox listener
    public SkillRequirementView() {
        initializeEntityList();
    }

    // skill requirements offset range is: 03F85FD4 - 03F864BF
    // with format - SCHOOL LEVEL STR STA AGI DEX INT WIS CHA, each 1 byte length
    private void initializeEntityList() {
        List<EntityTuple> entityTuples = new ArrayList<EntityTuple>() {{
            add(new EntityTuple<>(strengthField, 0, 1));
            add(new EntityTuple<>(staminaField, 1, 1));
            add(new EntityTuple<>(agilityField, 2, 1));
            add(new EntityTuple<>(dexterityField, 3, 1));
            add(new EntityTuple<>(intelligenceField, 4, 1));
            add(new EntityTuple<>(wisdomField, 5, 1));
            add(new EntityTuple<>(charismaField, 6, 1));
        }};

        for (EntityTuple entityTuple : entityTuples) {
            Entity entity;
            JComponent component = entityTuple.getComponent();
            if (component instanceof JTextField) {
                entity = new TextFieldEntity((JTextField) component, entityTuple.getOffsetInBytes(), entityTuple.getLengthInBytes());
            } else {
                entity = new ComboBoxEntity((JComboBox) component, entityTuple.getOffsetInBytes(), entityTuple.getLengthInBytes());
            }
            entity.setParent(this);
            getChildren().add(entity);
            entityList.add(entity);
        }
    }

    public void setSkillName(String skillName) {
        skillNameLabel.setText(skillName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {
        for (Entity entity : entityList) {
            entity.loadDataFromFile(file);
        }
    }
}
