package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class MiscellaneousListView extends AbstractModulesView {

    public MiscellaneousListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "miscellaneousTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        int miscellaneousType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, "items.miscelaneous"));
        List<String> miscellaneousTypes = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(miscellaneousType);
        for (String type : miscellaneousTypes) {
            addMapping(type, MiscellaneousParametersView.class);
        }
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MiscellaneousController.class;
    }
}
