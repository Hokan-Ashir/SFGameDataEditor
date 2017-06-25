package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.PresentableView;

public class SubViewPanelTuple implements Comparable<SubViewPanelTuple> {
    private final String name;
    private final Integer objectId;
    private final Class<? extends PresentableView> presentableViewClass;

    public SubViewPanelTuple(String name) {
        this.name = name;
        objectId = null;
        presentableViewClass = null;
    }

    public SubViewPanelTuple(String name, Integer objectId) {
        this.name = name;
        this.objectId = objectId;
        presentableViewClass = null;
    }

    public SubViewPanelTuple(String name, Class<? extends PresentableView> presentableViewClass) {
        this.name = name;
        this.presentableViewClass = presentableViewClass;
        objectId = null;
    }

    public SubViewPanelTuple(String name, Integer objectId, Class<? extends PresentableView> presentableViewClass) {
        this.name = name;
        this.objectId = objectId;
        this.presentableViewClass = presentableViewClass;
    }

    public String getName() {
        return name;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public Class<? extends PresentableView> getPresentableViewClass() {
        return presentableViewClass;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubViewPanelTuple tuple = (SubViewPanelTuple) o;

        return name != null ? name.equals(tuple.name) : tuple.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(SubViewPanelTuple o) {
        return name.compareTo(o.getName());
    }
}
