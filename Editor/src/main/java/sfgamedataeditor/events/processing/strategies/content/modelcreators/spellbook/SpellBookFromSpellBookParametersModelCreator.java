package sfgamedataeditor.events.processing.strategies.content.modelcreators.spellbook;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellBookFromSpellBookParametersModelCreator implements ModelCreator<ModulesModel, SpellBookParametersModel> {
    @Override
    public ModulesModel createModel(SpellBookParametersModel childModel) {
        SpellBookParametersModelParameter childModelParameter = childModel.getParameter();
        String scrollName = childModelParameter.getScrollBaseName() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " " + childModelParameter.getScrollLevel();
        Integer itemId = ViewTools.getKeyByPropertyValue(scrollName, I18NTypes.ITEMS);
        String selectedItem = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
