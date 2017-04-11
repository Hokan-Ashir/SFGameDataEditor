package sfgamedataeditor.views.main.modules.common.search;

class SearchTuple {
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
}
