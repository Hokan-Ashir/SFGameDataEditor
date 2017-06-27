package sfgamedataeditor.views.common.model.creators;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public abstract class AbstractModulesModelCreator implements ModelCreator<ModulesModel, ModulesModel> {
    @Override
    public ModulesModel createModel(ModulesModel childModel) {
        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, createChildModuleNon18nName()), null));
        return new ModulesModel(parameter);
    }

    protected abstract String createChildModuleNon18nName();
}
