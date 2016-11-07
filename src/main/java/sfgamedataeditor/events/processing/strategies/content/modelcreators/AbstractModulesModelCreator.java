package sfgamedataeditor.events.processing.strategies.content.modelcreators;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public abstract class AbstractModulesModelCreator implements ModelCreator<ModulesModel, ModulesModel> {
    @Override
    public ModulesModel createModel(ModulesModel childModel) {
        ModuleParameter parameter = new ModuleParameter(I18N.INSTANCE.getMessage(createChildModuleNon18nName()));
        return new ModulesModel(parameter);
    }

    protected abstract String createChildModuleNon18nName();
}
