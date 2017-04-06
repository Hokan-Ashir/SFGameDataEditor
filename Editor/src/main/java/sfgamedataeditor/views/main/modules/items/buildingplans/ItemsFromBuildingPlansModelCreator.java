package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ItemsFromBuildingPlansModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.buildingPlans";
    }
}
