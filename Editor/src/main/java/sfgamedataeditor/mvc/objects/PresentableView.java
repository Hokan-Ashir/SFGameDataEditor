package sfgamedataeditor.mvc.objects;

public interface PresentableView extends View {
    Class<? extends AbstractPresenter> getPresenterClass();
}
