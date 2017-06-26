package sfgamedataeditor.views.main.modules.items.unitplans.units;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitRaceToItemPlansMapping;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class UnitsPlansFromUnitsPlansListModelCreator implements ModelCreator<ModulesModel, UnitsPlanListModel> {

    @Override
    public ModulesModel createModel(UnitsPlanListModel childModel) {
        String itemName = childModel.getParameter().getSelectedModuleName();
        String raceName = null;
        if (itemName != null) {
            Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(itemName, UnitRaceToItemPlansMapping.INSTANCE.getTypes());
            ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
            raceName = UnitRaceToItemPlansMapping.INSTANCE.getRaceNameByItemType(object.typeId);
        }

        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
