package sfgamedataeditor.databind;

import javax.swing.*;

public class SpellEntity extends Entity {

    /**
     * Length of typical spell data (in bytes)
     */
    private static final int SPELL_DATA_LENGTH = 76;

    private int spellLevel;

    public SpellEntity(JTextField field, long offsetInBytes, int dataLengthInBytes) {
        super(field, offsetInBytes, dataLengthInBytes);
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected long getOffsetInFile() {
        long offset = super.getOffsetInFile();
        offset += spellLevel * SPELL_DATA_LENGTH;
        return offset;
    }
}
