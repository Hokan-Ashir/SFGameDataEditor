package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.viewconfigurations.buildings.parameters.BuildingsParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.CreatureParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.armor.ArmorParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.buildingplans.BuildingPlansParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.heroesrunes.HeroesRunesParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.miscellaneous.MiscellaneousParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.ScrollsParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.unitplans.UnitPlansParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.weapon.WeaponParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.workerrunes.WorkersRunesParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.skill.parameters.SkillParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.SpellParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.unit.parameters.UnitParametersConfigurationHolder;
import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;

import java.util.HashMap;
import java.util.Map;

public enum  ConfigurationsHolder {
    INSTANCE;

    private final Map<Class<? extends View>, AbstractConfigurationHolder> configurationHolderMap = new HashMap<>();

    ConfigurationsHolder() {
        configurationHolderMap.put(SpellParameterView.class, new SpellParametersConfigurationHolder());
        configurationHolderMap.put(SkillParameterView.class, new SkillParametersConfigurationHolder());
        configurationHolderMap.put(CreaturesParametersView.class, new CreatureParametersConfigurationHolder());
        configurationHolderMap.put(ArmorParametersView.class, new ArmorParametersConfigurationHolder());
        configurationHolderMap.put(WeaponParametersView.class, new WeaponParametersConfigurationHolder());
        configurationHolderMap.put(MiscellaneousParametersView.class, new MiscellaneousParametersConfigurationHolder());
        configurationHolderMap.put(SpellScrollsParametersView.class, new ScrollsParametersConfigurationHolder());
        configurationHolderMap.put(UnitsParametersView.class, new UnitParametersConfigurationHolder());
        configurationHolderMap.put(BuildingsParametersView.class, new BuildingsParametersConfigurationHolder());
        configurationHolderMap.put(BuildingsPlansParametersView.class, new BuildingPlansParametersConfigurationHolder());
        configurationHolderMap.put(UnitsPlansParametersView.class, new UnitPlansParametersConfigurationHolder());
        configurationHolderMap.put(WorkersRunesParametersView.class, new WorkersRunesParametersConfigurationHolder());
        configurationHolderMap.put(HeroesRunesParametersView.class, new HeroesRunesParametersConfigurationHolder());
    }

    public AbstractConfigurationHolder getConfigurationHolder(Class<? extends View> viewClass) {
        AbstractConfigurationHolder configurationHolder = configurationHolderMap.get(viewClass);
        if (configurationHolder != null) {
            return configurationHolder;
        }

        return null;
    }
}
