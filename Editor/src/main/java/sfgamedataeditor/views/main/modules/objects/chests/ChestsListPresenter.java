package sfgamedataeditor.views.main.modules.objects.chests;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestLootModelCreator;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ChestsListPresenter extends AbstractModulesPresenter<ModuleParameter, ChestsListView, ChestParametersModel> {

    private final ChestLootModelCreator creator = new ChestLootModelCreator();

    public ChestsListPresenter(ChestsListView view) {
        super(view);
    }

    @Override
    protected ChestParametersModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer chestLootId = ViewTools.getKeyByPropertyValue(selectedModuleName, I18NTypes.OBJECTS);
        return creator.createModel(chestLootId);
    }
}
