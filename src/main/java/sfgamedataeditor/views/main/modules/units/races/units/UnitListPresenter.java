package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsTableService;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModelParameter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class UnitListPresenter extends AbstractModulesPresenter<UnitListModelParameter, UnitListView, UnitsParametersModel> {

    public UnitListPresenter(UnitListView view) {
        super(view);
    }

    @Override
    protected UnitsParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleValue();
        Integer creatureId = ViewTools.getKeyByPropertyValue(selectedCreatureName, I18NTypes.CREATURES);
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(creatureId);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(creatureId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(creatureId);
        List<CreatureResourcesObject> creatureResources = CreatureResourcesTableService.INSTANCE.getCreatureResourcesByCreatureId(creatureId);
        List<CreatureBuildingsObject> creatureBuildings = CreatureBuildingsTableService.INSTANCE.getCreatureBuildingsByCreatureId(creatureId);
        UnitsParametersModelParameter parameter = new UnitsParametersModelParameter(commonParameterObject,
                creatureEquipment, creatureSpells, creatureResources, creatureBuildings);
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
