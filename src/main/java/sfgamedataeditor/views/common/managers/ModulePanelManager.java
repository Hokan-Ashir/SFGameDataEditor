package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.common.SubViewPanel;

import java.util.List;

public interface ModulePanelManager extends View {
    void updatePanelsLayout(List<SubViewPanel> subViewsPanels);
}
