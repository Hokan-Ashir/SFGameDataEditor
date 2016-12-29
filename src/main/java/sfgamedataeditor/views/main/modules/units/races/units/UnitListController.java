package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModelParameter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class UnitListController extends AbstractModulesController<UnitListModelParameter, UnitListView, UnitsParametersModel> {

    public UnitListController(UnitListView view) {
        super(view);
    }

    @Override
    protected UnitsParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleValue();
        Integer creatureId = ViewTools.getKeyByPropertyValue(selectedCreatureName, I18NTypes.CREATURES);
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(creatureId);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(creatureId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(creatureId);
        UnitsParametersModelParameter parameter = new UnitsParametersModelParameter(commonParameterObject,
                creatureEquipment, creatureSpells);
        return new UnitsParametersModel(parameter);
    }

    @Override
    public void updateView() {
        UnitListModelParameter parameter = getModel().getParameter();
        List<String> unitNames = parameter.getUnitNames();

        getView().clearComboBoxAndMapping();
        for (String unitName : unitNames) {
            getView().addMapping(unitName, UnitsParametersView.class);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(parameter.getSelectedUnitName());
    }
}
