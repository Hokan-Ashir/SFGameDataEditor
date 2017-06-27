package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ScrollsFromScrollsParametersModelCreator implements ModelCreator<ModulesModel, SpellScrollsParametersModel> {

    // TODO replace with "item.spells" (also change mapping for Skill-like abilities)
    private static final Integer SCROLL_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));

    @Override
    public ModulesModel createModel(SpellScrollsParametersModel childModel) {
        SpellScrollsParametersModelParameter childModelParameter = childModel.getParameter();
        String scrollName = childModelParameter.getScrollBaseName() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " " + childModelParameter.getLevel();
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(scrollName, SCROLL_TYPE_ID);
        // TODO FIX
        String selectedItem = "";
        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(selectedItem, itemId));
        return new ModulesModel(parameter);
    }
}
