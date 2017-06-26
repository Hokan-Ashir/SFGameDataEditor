package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModelParameter;

import java.util.List;

public class UnitsFromUnitParametersModelCreator implements ModelCreator<UnitListModel, UnitsParametersModel> {

    @Override
    public UnitListModel createModel(UnitsParametersModel childModel) {
        Integer creatureId = childModel.getParameter().getCreatureCommonParameterObject().creatureId;
        SubViewPanelTuple unitTuple = UnitMapping.INSTANCE.getUnitTuple(creatureId);
        SubViewPanelTuple raceTuple = UnitMapping.INSTANCE.getRaceTuple(creatureId);
        List<SubViewPanelTuple> unitNames = UnitMapping.INSTANCE.getUnitNames(raceTuple);
        UnitListModelParameter parameter = new UnitListModelParameter(unitNames, unitTuple.getName());
        return new UnitListModel(parameter);
    }
}
