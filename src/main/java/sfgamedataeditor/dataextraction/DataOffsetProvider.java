package sfgamedataeditor.dataextraction;

import sfgamedataeditor.databind.files.FilesContainer;

public enum DataOffsetProvider {
    INSTANCE;

    private static long spellDataBeginOffset;
    private static long spellDataEndOffset;
    private static long skillDataBeginOffset;
    private static long skillDataEndOffset;

    static {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();

        if (isVersion11) {
            spellDataBeginOffset = 0x20;
            // TODO find appropriate offset
            spellDataEndOffset = 0x3fd13;
            skillDataBeginOffset = 0x2577bd4;
            skillDataEndOffset = 0x2577ec8;
        } else {
            spellDataBeginOffset = 0x20;
            spellDataEndOffset = 0x3fd13;
            skillDataBeginOffset = 0x03F85FD4;
            skillDataEndOffset = 0x03F864BF;
        }
    }

    public long getSpellDataBeginOffset() {
        return spellDataBeginOffset;
    }

    public long getSpellDataEndOffset() {
        return spellDataEndOffset;
    }

    public long getSkillDataBeginOffset() {
        return skillDataBeginOffset;
    }

    public long getSkillDataEndOffset() {
        return skillDataEndOffset;
    }
}
