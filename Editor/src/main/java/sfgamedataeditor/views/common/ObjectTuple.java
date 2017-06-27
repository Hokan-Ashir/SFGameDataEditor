package sfgamedataeditor.views.common;

public class ObjectTuple implements Comparable<ObjectTuple> {
    private final String name;
    private final Integer objectId;

    public ObjectTuple(ObjectTuple tuple) {
        this.name = tuple.getName();
        this.objectId = tuple.getObjectId();
    }

    public ObjectTuple(String name, Integer objectId) {
        this.name = name;
        this.objectId = objectId;
    }

    public ObjectTuple(String name) {
        this.name = name;
        objectId = null;
    }

    public String getName() {
        return name;
    }

    public Integer getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectTuple that = (ObjectTuple) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return objectId != null ? objectId.equals(that.objectId) : that.objectId == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ObjectTuple o) {
        return name.compareTo(o.getName());
    }
}
