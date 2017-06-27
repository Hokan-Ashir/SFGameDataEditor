package sfgamedataeditor.views.main.modules.items.unitplans.units;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitRaceToItemPlansMapping;

public class UnitsPlansFromUnitsPlansListModelCreator implements ModelCreator<ModulesModel, UnitsPlanListModel> {

    @Override
    public ModulesModel createModel(UnitsPlanListModel childModel) {
        ObjectTuple itemName = childModel.getParameter().getSelectedModuleName();
        String raceName = null;
        Integer raceId = null;
        if (itemName != null) {
            Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(itemName.getName(), UnitRaceToItemPlansMapping.INSTANCE.getTypes());
            ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
            raceId = object.typeId;
            raceName = UnitRaceToItemPlansMapping.INSTANCE.getRaceNameByItemType(raceId);
        }

        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(raceName, raceId));
        return new ModulesModel(parameter);
    }
}
