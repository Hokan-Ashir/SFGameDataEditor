package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;

public class ScrollsFromScrollsParametersModelCreator implements ModelCreator<ModulesModel, SpellScrollsParametersModel> {

    @Override
    public ModulesModel createModel(SpellScrollsParametersModel childModel) {
        SpellScrollsParametersModelParameter childModelParameter = childModel.getParameter();
        String scrollBaseName = childModelParameter.getScrollBaseName();
        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(scrollBaseName);
        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(scrollBaseName, spellId));
        return new ModulesModel(parameter);
    }
}
