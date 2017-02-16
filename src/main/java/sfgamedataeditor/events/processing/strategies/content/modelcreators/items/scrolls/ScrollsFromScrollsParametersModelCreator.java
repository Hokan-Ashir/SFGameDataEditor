package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.scrolls;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ScrollsFromScrollsParametersModelCreator implements ModelCreator<ModulesModel, SpellScrollsParametersModel> {
    @Override
    public ModulesModel createModel(SpellScrollsParametersModel childModel) {
        SpellScrollsParametersModelParameter childModelParameter = childModel.getParameter();
        String scrollName = childModelParameter.getScrollBaseName() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " " + childModelParameter.getScrollLevel();
        Integer itemId = ViewTools.getKeyByPropertyValue(scrollName, I18NTypes.ITEMS);
        String selectedItem = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
