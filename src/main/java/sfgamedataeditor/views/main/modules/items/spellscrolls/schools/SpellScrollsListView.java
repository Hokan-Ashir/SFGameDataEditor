package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class SpellScrollsListView extends AbstractModulesView {

    public SpellScrollsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.spellScrolls"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, "items.scrolls"));
        List<String> scrollsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        for (String name : scrollsNames) {
            addMapping(name, SpellScrollsParametersView.class);
        }
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellScrollsController.class;
    }
}
