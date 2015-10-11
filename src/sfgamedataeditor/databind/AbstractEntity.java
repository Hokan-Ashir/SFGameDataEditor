package sfgamedataeditor.databind;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntity implements IDataManipulator {

    private AbstractEntity parent;
    private List<AbstractEntity> children = new ArrayList<>();
    private long offsetInFile;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void saveDataInFile(RandomAccessFile file) {
        for (AbstractEntity child : children) {
            child.saveDataInFile(file);
        }
    }

    public AbstractEntity getParent() {
        return parent;
    }

    public void setParent(AbstractEntity parent) {
        this.parent = parent;
    }

    public List<AbstractEntity> getChildren() {
        return children;
    }

    protected long getOffsetInFile() {
        return offsetInFile;
    }

    protected void setOffsetInFile(long offsetInFile) {
        this.offsetInFile = offsetInFile;
    }

    protected long getFullFileOffset() {
        long offset = offsetInFile;
        AbstractEntity parent = getParent();
        while (parent != null) {
            offset += parent.getOffsetInFile();
            parent = parent.getParent();
        }

        return offset;
    }
}
