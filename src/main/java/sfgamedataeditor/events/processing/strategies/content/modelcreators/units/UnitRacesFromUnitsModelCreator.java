package sfgamedataeditor.events.processing.strategies.content.modelcreators.units;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;

public class UnitRacesFromUnitsModelCreator implements ModelCreator<ModulesModel, UnitListModel> {

    @Override
    public ModulesModel createModel(UnitListModel childModel) {
        String unitName = childModel.getParameter().getUnitNames().get(0);
        String raceName  = UnitMapping.INSTANCE.getRaceName(unitName);
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
