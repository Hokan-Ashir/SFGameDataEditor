package sfgamedataeditor.databind;

public class SpellDataTuple {
    private long offset;
    private int level;
    private String name;
    private int id;

    public SpellDataTuple(long offset, int level, String name, int id) {
        this.offset = offset;
        this.level = level;
        this.name = name;
        this.id = id;
    }

    public long getOffset() {
        return offset;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
