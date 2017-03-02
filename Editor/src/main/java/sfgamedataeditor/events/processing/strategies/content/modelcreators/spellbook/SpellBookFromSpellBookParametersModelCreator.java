package sfgamedataeditor.events.processing.strategies.content.modelcreators.spellbook;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellBookFromSpellBookParametersModelCreator implements ModelCreator<ModulesModel, SpellBookParametersModel> {

    private static final Integer SPELL_OBJECT_TYPE = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));

    @Override
    public ModulesModel createModel(SpellBookParametersModel childModel) {
        SpellBookParametersModelParameter childModelParameter = childModel.getParameter();
        String scrollName = childModelParameter.getScrollBaseName() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " " + childModelParameter.getScrollLevel();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(scrollName, SPELL_OBJECT_TYPE);
        String selectedItem = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
