package sfgamedataeditor.mvc.objects;

import java.util.Set;

// TODO expand to Abstract class
public interface SubModuleParameter {
    String getSelectedModuleName();
    Set<String> getSubPanelsNames();
    Class<? extends PresentableView> getSubPanelsViewClass();
}
