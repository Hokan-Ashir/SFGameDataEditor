package sfgamedataeditor.dataextraction;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.CreaturesOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SkillsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SpellsOffsetHolder;
import sfgamedataeditor.utils.Pair;

import java.util.List;

public enum DataOffsetProvider {
    INSTANCE;

    private final AbstractOffsetHolder spellOffsets = new SpellsOffsetHolder();
    private final AbstractOffsetHolder skillOffsets = new SkillsOffsetHolder();
    private final AbstractOffsetHolder creaturesOffsets = new CreaturesOffsetHolder();

    public List<Pair<Integer, Integer>> getSpellOffsets() {
        return spellOffsets.getOffsets();
    }

    public List<Pair<Integer, Integer>> getSkillOffsets() {
        return skillOffsets.getOffsets();
    }

    public List<Pair<Integer, Integer>> getCreaturesOffsets() {
        return creaturesOffsets.getOffsets();
    }

    public int getSpellDataLength() {
        return spellOffsets.getDataLength();
    }

    public int getSkillDataLength() {
        return skillOffsets.getDataLength();
    }

    public int getCreatureDataLength() {
        return creaturesOffsets.getDataLength();
    }
}
