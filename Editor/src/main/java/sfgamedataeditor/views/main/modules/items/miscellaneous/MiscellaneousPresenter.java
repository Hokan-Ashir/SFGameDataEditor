package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class MiscellaneousPresenter extends AbstractModulesPresenter<ModuleParameter, MiscellaneousListView, MiscellaneousParametersModel> {

    private static final Integer MISCELLANEOUS_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.miscellaneous"));
    private final MiscellaneousModelCreator modelCreator = new MiscellaneousModelCreator();

    public MiscellaneousPresenter(MiscellaneousListView view) {
        super(view);
    }

    @Override
    protected MiscellaneousParametersModel createModel() {
        String selectedMiscellaneous = getView().getSelectedModuleName();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedMiscellaneous, MISCELLANEOUS_TYPE_ID);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
