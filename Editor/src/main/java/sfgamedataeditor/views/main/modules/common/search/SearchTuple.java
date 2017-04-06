package sfgamedataeditor.views.main.modules.common.search;

public class SearchTuple {
    private final Integer elementType;
    private final String elementName;
    private final Integer elementId;

    public SearchTuple(Integer elementType, String elementName, Integer elementId) {
        this.elementType = elementType;
        this.elementName = elementName;
        this.elementId = elementId;
    }

    public Integer getElementType() {
        return elementType;
    }

    public String getElementName() {
        return elementName;
    }

    public Integer getElementId() {
        return elementId;
    }

    @Override
    public String toString() {
        return elementName;
    }
}
