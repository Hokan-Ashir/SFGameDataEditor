package sfgamedataeditor.views.main.modules.spellbook;

import sfgamedataeditor.views.main.modules.ModelCreator;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellBookModelCreator implements ModelCreator<SpellBookParametersModel> {
    @Override
    public SpellBookParametersModel createModel(int objectId, Icon icon) {
        String fullScrollName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(objectId));
        String baseScrollName = fullScrollName.split("\\s")[0];
        Integer scrollLevel = Integer.valueOf(fullScrollName.split("\\s")[3]);
        SpellBookParametersModelParameter parameter = new SpellBookParametersModelParameter(baseScrollName, scrollLevel, icon);
        return new SpellBookParametersModel(parameter);
    }
}
