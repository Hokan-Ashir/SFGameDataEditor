package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public abstract class AbstractModulesModelCreator implements ModelCreator<ModulesModel, ModulesModel> {
    @Override
    public ModulesModel createModel(ModulesModel childModel) {
        ModuleParameter parameter = new ModuleParameter(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, createChildModuleNon18nName()));
        return new ModulesModel(parameter);
    }

    protected abstract String createChildModuleNon18nName();
}
