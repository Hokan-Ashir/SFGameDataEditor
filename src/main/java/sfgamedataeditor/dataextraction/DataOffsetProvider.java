package sfgamedataeditor.dataextraction;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SkillsOffsetHolder;
import sfgamedataeditor.dataextraction.offsets.SpellsOffsetHolder;

import java.util.List;

public enum DataOffsetProvider {
    INSTANCE;

    AbstractOffsetHolder spellOffsets = new SpellsOffsetHolder();
    AbstractOffsetHolder skillOffsets = new SkillsOffsetHolder();

    public List<Pair<Integer, Integer>> getSpellOffsets() {
        return spellOffsets.getOffsets();
    }

    public List<Pair<Integer, Integer>> getSkillOffsets() {
        return skillOffsets.getOffsets();
    }

    public int getSpellDataLength() {
        return spellOffsets.getDataLength();
    }

    public int getSkillDataLength() {
        return skillOffsets.getDataLength();
    }
}
