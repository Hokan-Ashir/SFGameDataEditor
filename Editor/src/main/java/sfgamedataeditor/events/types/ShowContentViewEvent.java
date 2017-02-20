package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;

public class ShowContentViewEvent extends ShowViewEvent {
    public ShowContentViewEvent(Class<? extends PresentableView> classViewToShow, Model model) {
        super(classViewToShow, model);
    }
}
