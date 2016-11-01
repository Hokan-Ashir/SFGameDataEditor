package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.AbstractController;

public interface ControllableView extends View {
    Class<? extends AbstractController> getControllerClass();
}
