package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.MiscellaneousModelCreator;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class MiscellaneousPresenter extends AbstractModulesPresenter<ModuleParameter, MiscellaneousListView, MiscellaneousParametersModel> {

    private final MiscellaneousModelCreator modelCreator = new MiscellaneousModelCreator();

    public MiscellaneousPresenter(MiscellaneousListView view) {
        super(view);
    }

    @Override
    protected MiscellaneousParametersModel createModel() {
        String selectedMiscellaneous = getView().getSelectedModuleValue();
        int itemId = ViewTools.getKeyByPropertyValue(selectedMiscellaneous, I18NTypes.ITEMS);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
