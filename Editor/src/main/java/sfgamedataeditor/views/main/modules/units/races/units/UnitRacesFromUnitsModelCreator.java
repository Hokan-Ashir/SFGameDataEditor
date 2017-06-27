package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;

public class UnitRacesFromUnitsModelCreator implements ModelCreator<ModulesModel, UnitListModel> {

    @Override
    public ModulesModel createModel(UnitListModel childModel) {
        // no matter which unit we select, cause changing unit's race is impossible (as I know)
        Integer unitId = childModel.getParameter().getSubViewPanelTuples().get(0).getObjectId();
        ObjectTuple tuple  = UnitMapping.INSTANCE.getRaceTuple(unitId);
        ModuleParameter parameter = new ModuleParameter(tuple);
        return new ModulesModel(parameter);
    }
}
