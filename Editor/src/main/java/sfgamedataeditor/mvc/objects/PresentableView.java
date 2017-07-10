package sfgamedataeditor.mvc.objects;

public interface PresentableView extends View, LocalizableObject {
    Class<? extends AbstractPresenter> getPresenterClass();
}
