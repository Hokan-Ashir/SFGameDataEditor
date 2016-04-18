package sfgamedataeditor.dataextraction;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;

import java.util.ArrayList;
import java.util.List;

public enum DataOffsetProvider {
    INSTANCE;

    private List<Pair<Integer, Integer>> spellOffsets = new ArrayList<>();
    private List<Pair<Integer, Integer>> skillOffsets = new ArrayList<>();

    DataOffsetProvider() {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();

        if (isVersion11) {
            // TODO find appropriate offset (instead of 0x3fd13)
            spellOffsets.add(new Pair<>(0x20, 0x3fd13));
            skillOffsets.add(new Pair<>(0x2577bd4, 0x2577ec8));
        } else {
            spellOffsets.add(new Pair<>(0x20, 0xf6d3));
            spellOffsets.add(new Pair<>(0x1105c, 0x3fd13));
            skillOffsets.add(new Pair<>(0x03F85FD4, 0x03F864BF));
        }
    }

    public List<Pair<Integer, Integer>> getSpellOffsets() {
        return spellOffsets;
    }

    public List<Pair<Integer, Integer>> getSkillOffsets() {
        return skillOffsets;
    }

    public int getSpellDataLength() {
        return 0x4c;
    }

    public int getSkillDataLength() {
        return 0x9;
    }
}
