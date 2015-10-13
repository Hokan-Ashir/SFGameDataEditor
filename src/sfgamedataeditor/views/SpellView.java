package sfgamedataeditor.views;

import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.SpellEntity;

import javax.swing.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SpellView extends AbstractEntity implements IView {

    private JPanel mainPanel;
    private JTextField numberField;
    private JLabel numberLabel;
    private JLabel typeLable;
    private JTextField typeField;
    private JLabel requirementClassLabel;
    private JTextField requirementClassField;
    private JLabel requirementSubClassLabel;
    private JTextField requirementSubClassField;
    private JLabel requirementLevelLabel;
    private JTextField requirementLevelField;
    private JLabel manaUsageLabel;
    private JTextField manaUsageField;
    private JLabel castTimeLabel;
    private JTextField castTimeField;
    private JLabel cooldownLabel;
    private JTextField cooldownField;
    private JLabel maxRangeLabel;
    private JTextField maxRangeField;
    private JLabel castTypeLabel;
    private JTextField castTypeField;
    private JLabel durationLabel;
    private JTextField durationField;

    private List<SpellEntity> entityList = new ArrayList<>();
    private int spellLevel;

    public SpellView(long offsetInBytes) {
        setOffsetInFile(offsetInBytes);

        // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
        List<EntityTuple> entityTuples = new ArrayList<EntityTuple>() {{
            add(new EntityTuple(numberField, 0, 2));
            add(new EntityTuple(typeField, 2, 2));
            add(new EntityTuple(requirementClassField, 4, 1));
            add(new EntityTuple(requirementSubClassField, 5, 1));
            add(new EntityTuple(requirementLevelField, 6, 1));
            // TODO add possible spell skill requirements (9 bytes offset)
            add(new EntityTuple(manaUsageField, 16, 2));
            add(new EntityTuple(castTimeField, 18, 4));
            add(new EntityTuple(cooldownField, 22, 4));
            // TODO add possible spell min range parameter (2 bytes offset)
            add(new EntityTuple(maxRangeField, 28, 2));
            add(new EntityTuple(castTypeField, 30, 2));
            add(new EntityTuple(durationField, 32, 4));
            // TODO add spell additional parameters (number of targets for wave-like spells,
            // spell-child effects for auras and frost/fire shields)
        }};

        for (EntityTuple entityTuple : entityTuples) {
            SpellEntity entity = new SpellEntity(entityTuple.field, entityTuple.offsetInBytes, entityTuple.lengthInBytes);
            entity.setParent(this);
            getChildren().add(entity);
            entityList.add(entity);
        }
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
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
        for (SpellEntity entity : entityList) {
            entity.setSpellLevel(spellLevel);
            entity.loadDataFromFile(file);
        }
    }

    private class EntityTuple {
        private JTextField field;
        private long offsetInBytes;
        private int lengthInBytes;

        public EntityTuple(JTextField field, long offsetInBytes, int lengthInBytes) {
            this.field = field;
            this.offsetInBytes = offsetInBytes;
            this.lengthInBytes = lengthInBytes;
        }
    }
}
