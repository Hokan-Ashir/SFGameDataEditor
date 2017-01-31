package sfgamedataeditor.mvc.objects;

import java.util.Set;

public interface SubModuleParameter {
    String getSelectedModuleName();
    Set<String> getSubPanelsNames();
    Class<? extends PresentableView> getSubPanelsViewClass();
}
