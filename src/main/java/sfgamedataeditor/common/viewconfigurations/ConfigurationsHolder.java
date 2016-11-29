package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.viewconfigurations.creatureparameters.CreatureParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.skillparameters.SkillParametersConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.spellparameters.SpellParametersConfigurationHolder;
import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
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
    }

    public AbstractConfigurationHolder getConfigurationHolder(Class<? extends View> viewClass) {
        AbstractConfigurationHolder configurationHolder = configurationHolderMap.get(viewClass);
        if (configurationHolder != null) {
            return configurationHolder;
        }

        return null;
    }
}
