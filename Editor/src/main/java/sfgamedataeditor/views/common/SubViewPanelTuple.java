package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.PresentableView;

public class SubViewPanelTuple  extends ObjectTuple {
    private final Class<? extends PresentableView> presentableViewClass;

    public SubViewPanelTuple(ObjectTuple tuple, Class<? extends PresentableView> presentableViewClass) {
        super(tuple);
        this.presentableViewClass = presentableViewClass;
    }

    public Class<? extends PresentableView> getPresentableViewClass() {
        return presentableViewClass;
    }
}
