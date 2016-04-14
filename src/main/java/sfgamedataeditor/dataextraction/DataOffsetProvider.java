package sfgamedataeditor.dataextraction;

public enum DataOffsetProvider {
    INSTANCE;

    // TODO replace all methods with file acquiring according to its size
    public int getSpellDataBeginOffset() {
        return 0x20;
    }

    public int getSpellDataEndOffset() {
        return 0x3fd13;
    }

    public int getSkillDataBeginOffset() {
        return 0x03F85FD4;
    }

    public int getSkillDataEndOffset() {
        return 0x03F864BF;
    }
}
