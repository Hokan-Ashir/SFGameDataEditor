package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.AbstractController;

public interface RenderableView extends View, Renderable {
    Class<? extends AbstractController> getControllerClass();
}
