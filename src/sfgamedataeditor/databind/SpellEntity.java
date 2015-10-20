package sfgamedataeditor.databind;

import javax.swing.*;

public abstract class SpellEntity<T extends JComponent> extends Entity<T> {

    /**
     * Length of typical spell data (in bytes)
     */
    private static final int SPELL_DATA_LENGTH = 76;

    private int spellLevel;

    public SpellEntity(T component, long offsetInBytes, int dataLengthInBytes) {
        super(component, offsetInBytes, dataLengthInBytes);
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
        // subtract 1, cause initial spell offset (see SpellView constructor)
        // is already contains first spell level offset
        if (spellLevel <= 12) {
            offset += (spellLevel - 1) * SPELL_DATA_LENGTH;
        } else {
            offset += (spellLevel - 13) * SPELL_DATA_LENGTH;
        }
        return offset;
    }
}
