package sfgamedataeditor.events.processing.strategies.content.modelcreators.units;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModelParameter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;

import java.util.Set;

public class UnitsFromUnitParametersModelCreator implements ModelCreator<UnitListModel, UnitsParametersModel> {

    @Override
    public UnitListModel createModel(UnitsParametersModel childModel) {
        String selectedUnitName = childModel.getParameter().getCreatureCommonParameterObject().name;
        String raceName = UnitMapping.INSTANCE.getRaceName(selectedUnitName);
        Set<String> unitNames = UnitMapping.INSTANCE.getUnitNames(raceName);
        UnitListModelParameter parameter = new UnitListModelParameter(unitNames, selectedUnitName);
        return new UnitListModel(parameter);
    }
}
