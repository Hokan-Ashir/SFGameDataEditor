package sfgamedataeditor.views.common.races;

import org.apache.log4j.Logger;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class AbstractRacesPresenter extends AbstractModulesPresenter<ModuleParameter, AbstractRacesView, ModulesModel> {

    private static final Logger LOGGER = Logger.getLogger(AbstractRacesPresenter.class);

    public AbstractRacesPresenter(AbstractRacesView view) {
        super(view);
    }

    @Override
    protected ModulesModel createModel() {
        LOGGER.info("Not implemented");
        return null;
    }

    @Override
    protected void updateSubViewsContent() {
        LOGGER.info("Not implemented");
    }
}
