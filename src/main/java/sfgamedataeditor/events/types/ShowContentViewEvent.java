package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;

public class ShowContentViewEvent extends ShowViewEvent {
    public ShowContentViewEvent(Class<? extends ControllableView> classViewToShow, Model model) {
        super(classViewToShow, model);
    }
}
