package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModelParameter;

import java.util.List;

public class UnitsPlansFromUnitsPlansParameterModelCreator implements ModelCreator<UnitsPlanListModel, UnitsPlansParametersModel> {

    @Override
    public UnitsPlanListModel createModel(UnitsPlansParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        ObjectTuple itemName = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.itemId);
        Integer unitRaceId = object.typeId;
        List<ObjectTuple> unitsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(unitRaceId);
        UnitsPlanListModelParameter parameter = new UnitsPlanListModelParameter(unitsNames, itemName);
        return new UnitsPlanListModel(parameter);
    }
}
