package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class MiscellaneousListView extends AbstractModulesView {

    public MiscellaneousListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "miscellaneousTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        int miscellaneousType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.miscellaneous"));
        Set<String> miscellaneousNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(miscellaneousType);
        addMappings(miscellaneousNames, MiscellaneousParametersView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return MiscellaneousPresenter.class;
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
