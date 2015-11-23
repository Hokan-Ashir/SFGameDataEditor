package sfgamedataeditor.skills;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.entity.EntityContainer;
import sfgamedataeditor.databind.entity.EntityTuple;
import sfgamedataeditor.dataextraction.XMLExtractor;
import sfgamedataeditor.views.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRequirementView extends EntityContainer implements IView {
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

    public SkillRequirementView(List<Pair<Integer, Long>> offsets) {
        super(offsets);
        stregthLabel.setText(XMLExtractor.getTagValue("strength"));
        staminaLabel.setText(XMLExtractor.getTagValue("stamina"));
        dexterityLabel.setText(XMLExtractor.getTagValue("dexterity"));
        agilityLabel.setText(XMLExtractor.getTagValue("agility"));
        wisdomLabel.setText(XMLExtractor.getTagValue("wisdom"));
        intelligenceLabel.setText(XMLExtractor.getTagValue("intelligence"));
        charismaLabel.setText(XMLExtractor.getTagValue("charisma"));

        initializeEntityList();
        setAbilityDataOffsetByLevel(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<EntityTuple> createEntityTuples() {
        // skill requirements offset range is: 03F85FD4 - 03F864BF
        // with format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
        return new ArrayList<EntityTuple>() {{
            add(new EntityTuple<>(strengthField, 0, 1));
            add(new EntityTuple<>(staminaField, 1, 1));
            add(new EntityTuple<>(agilityField, 2, 1));
            add(new EntityTuple<>(dexterityField, 3, 1));
            add(new EntityTuple<>(charismaField, 4, 1));
            add(new EntityTuple<>(intelligenceField, 5, 1));
            add(new EntityTuple<>(wisdomField, 6, 1));
        }};
    }

    public void setSkillName(String skillName) {
        skillNameLabel.setText(skillName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Component[] getMainPanelComponents() {
        return mainPanel.getComponents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
