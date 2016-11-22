package sfgamedataeditor.mvc.objects;

public interface ControllableView extends View {
    Class<? extends AbstractController> getControllerClass();
}
