package sfgamedataeditor.views.main.modules.common.search;

class SearchTuple implements Comparable<SearchTuple> {
    private final Integer elementType;
    private final String elementName;
    private final Integer elementId;

    SearchTuple(Integer elementType, String elementName, Integer elementId) {
        this.elementType = elementType;
        this.elementName = elementName;
        this.elementId = elementId;
    }

    Integer getElementType() {
        return elementType;
    }

    Integer getElementId() {
        return elementId;
    }

    @Override
    public String toString() {
        return elementName;
    }

    @Override
    public int compareTo(SearchTuple o) {
        return this.elementName.compareTo(o.elementName);
    }
}
