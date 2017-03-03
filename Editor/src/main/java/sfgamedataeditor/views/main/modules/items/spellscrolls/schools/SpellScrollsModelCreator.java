package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.views.main.modules.ModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellScrollsModelCreator implements ModelCreator<SpellScrollsParametersModel> {
    @Override
    public SpellScrollsParametersModel createModel(int objectId, Icon icon) {
        String fullScrollName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(objectId));
        String baseScrollName = fullScrollName.split("\\s")[0];
        Integer scrollLevel = Integer.valueOf(fullScrollName.split("\\s")[3]);
        SpellScrollsParametersModelParameter parameter = new SpellScrollsParametersModelParameter(baseScrollName, scrollLevel, icon);
        return new SpellScrollsParametersModel(parameter);
    }
}
