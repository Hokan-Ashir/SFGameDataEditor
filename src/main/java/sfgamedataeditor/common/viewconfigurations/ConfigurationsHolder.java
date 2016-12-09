package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.viewconfigurations.creature.parameters.CreatureParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.item.parameters.ItemParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.skill.parameters.SkillParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.SpellParametersConfigurationHolder;
import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.HashMap;
import java.util.Map;

public enum  ConfigurationsHolder {
    INSTANCE;

    private final Map<Class<? extends View>, AbstractConfigurationHolder> configurationHolderMap = new HashMap<>();

    ConfigurationsHolder() {
        configurationHolderMap.put(SpellParameterView.class, new SpellParametersConfigurationHolder());
        configurationHolderMap.put(SkillParameterView.class, new SkillParametersConfigurationHolder());
        configurationHolderMap.put(CreaturesParametersView.class, new CreatureParametersConfigurationHolder());
        configurationHolderMap.put(ArmorParametersView.class, new ItemParametersConfigurationHolder());
    }

    public AbstractConfigurationHolder getConfigurationHolder(Class<? extends View> viewClass) {
        AbstractConfigurationHolder configurationHolder = configurationHolderMap.get(viewClass);
        if (configurationHolder != null) {
            return configurationHolder;
        }

        return null;
    }
}
