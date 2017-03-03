package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModelParameter;

import java.util.Set;

public class UnitsPlansFromUnitsPlansParameterModelCreator implements ModelCreator<UnitsPlanListModel, UnitsPlansParametersModel> {

    @Override
    public UnitsPlanListModel createModel(UnitsPlansParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        String selectedUnitName = object.name;
        Integer unitRaceId = object.typeId;
        Set<String> unitsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(unitRaceId);
        UnitsPlanListModelParameter parameter = new UnitsPlanListModelParameter(unitsNames, selectedUnitName, unitRaceId);
        return new UnitsPlanListModel(parameter);
    }
}
