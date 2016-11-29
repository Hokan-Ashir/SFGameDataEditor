package sfgamedataeditor.views.common.races;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;

public abstract class AbstractRacesView extends AbstractModulesView {

    protected AbstractRacesView() {
        super(I18N.INSTANCE.getMessage("races"));
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return AbstractRacesController.class;
    }
}
