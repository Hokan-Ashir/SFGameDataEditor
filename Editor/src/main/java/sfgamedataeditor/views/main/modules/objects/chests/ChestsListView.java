package sfgamedataeditor.views.main.modules.objects.chests;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class ChestsListView extends AbstractModulesView {

    public ChestsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "objects"));
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> names = ChestCorpseLootTableService.INSTANCE.getAllChestCorpseLootObjectNames();
        addMappings(names, ChestParametersView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ChestsListPresenter.class;
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }

    @Override
    public void localize() {
        setModuleName(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "objects"));
    }
}
