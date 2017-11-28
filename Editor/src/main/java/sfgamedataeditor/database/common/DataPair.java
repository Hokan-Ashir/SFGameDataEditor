package sfgamedataeditor.database.common;

public final class DataPair implements Comparable<DataPair> {
    private final int length;
    private final int offset;

    public DataPair(int length, int offset) {
        this.length = length;
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public int getOffset() {
        return offset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(DataPair o) {
        return offset == o.getOffset() ? 0 : (offset > o.getOffset() ? 1 : -1);
    }
}
