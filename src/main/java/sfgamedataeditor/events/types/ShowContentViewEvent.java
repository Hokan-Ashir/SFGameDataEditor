package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;

public class ShowContentViewEvent extends ShowViewEvent {
    public ShowContentViewEvent(Class<? extends ControllableView> classViewToShow, Model model) {
        super(classViewToShow, model);
    }
}
