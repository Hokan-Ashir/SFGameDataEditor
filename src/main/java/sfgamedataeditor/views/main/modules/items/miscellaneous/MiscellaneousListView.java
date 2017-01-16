package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
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
        int miscellaneousType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, "items.miscellaneous"));
        List<String> miscellaneousNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(miscellaneousType);
        for (String name : miscellaneousNames) {
            addMapping(name, MiscellaneousParametersView.class);
        }
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return MiscellaneousPresenter.class;
    }
}
