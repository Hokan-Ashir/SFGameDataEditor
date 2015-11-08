package sfgamedataeditor.databind.entity;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class EntityContainer extends AbstractEntity {
    private List<Entity> entityList = new ArrayList<>();
    private List<Pair<Integer, Long>> abilityLevelOffsets;

    public EntityContainer(List<Pair<Integer, Long>> offsets) {
        this.abilityLevelOffsets = offsets;
        Collections.sort(this.abilityLevelOffsets, new Comparator<Pair<Integer, Long>>() {
            @Override
            public int compare(Pair<Integer, Long> o1, Pair<Integer, Long> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }

    protected void initializeEntityList() {
        List<EntityTuple> entityTuples = createEntityTuples();
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

    protected abstract List<EntityTuple> createEntityTuples();

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {
        for (Entity entity : entityList) {
            entity.loadDataFromFile(file);
        }
    }

    public void setAbilityDataOffsetByLevel(int abilityLevel) {
        for (Pair<Integer, Long> spellLevelOffset : abilityLevelOffsets) {
            if (spellLevelOffset.getKey() == abilityLevel) {
                setOffsetInFile(spellLevelOffset.getValue());
            }
        }
    }

    public void hideViewComponents() {
        for (Component component : getMainPanelComponents()) {
            component.setVisible(getOffsetInFile() != 0);
        }
    }

    protected abstract Component[] getMainPanelComponents();

    public boolean isAbilityHasLevel(int abilityLevel) {
        if (abilityLevelOffsets.isEmpty()) {
            return false;
        }

        for (Pair<Integer, Long> spellLevelOffset : abilityLevelOffsets) {
            if (spellLevelOffset.getKey() == abilityLevel) {
                return true;
            }
        }

        return false;
    }

    public List<Pair<Integer, Long>> getAbilityLevelOffsets() {
        return abilityLevelOffsets;
    }
}
