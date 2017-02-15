package sfgamedataeditor.common.viewconfigurations.spell.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.configurations.*;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.*;

public class SpellParametersConfigurationHolder extends AbstractConfigurationHolder {

    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new DefaultSpellParameterViewConfiguration());

        Map<DefaultSpellParameterViewConfiguration, List<String>> configurations = new HashMap<>();
        configurations.put(new SummoningSpellsConfiguration(), getSummoningSpellsNames());
        configurations.put(new ChainSpellsConfiguration(), getChainSpellsNames());
        configurations.put(new ElementalSpellConfiguration(), getElementalSpellsNames());
        configurations.put(new WhiteSpellConfiguration(), getWhiteSpellsNames());
        configurations.put(new WavesSpellsConfiguration(), getWavesSpellsNames());
        configurations.put(new AurasSpellsConfiguration(), getAurasSpellsNames());
        configurations.put(new RainsSpellsConfiguration(), getRainsSpellsNames());
        configurations.put(new ShieldSpellsConfiguration(), getShieldSpellsNames());

        for (Map.Entry<DefaultSpellParameterViewConfiguration, List<String>> entry : configurations.entrySet()) {
            addCustomSpellsConfigurations(entry.getKey(), entry.getValue());
        }
    }

    private List<String> getShieldSpellsNames() {
        return new ArrayList<String>() {{
            add("FireShield");
            add("IceShield");
            add("ThornShield");
        }};
    }

    private List<String> getRainsSpellsNames() {
        return new ArrayList<String>() {{
            add("RainOfFire");
            add("Blizzard");
            add("StoneRain");
        }};
    }

    private List<String> getAurasSpellsNames() {
        return new ArrayList<String>() {{
            add("AuraWeakness");
            add("AuraSuffocation");
            add("AuraLifeTap");
            add("AuraSlowFighting");
            add("AuraInflexibility");
            add("AuraSlowWalking");
            add("AuraInability");
            add("AuraStrength");
            add("AuraHealing");
            add("AuraEndurance");
            add("AuraRegeneration");
            add("AuraFastFighting");
            add("AuraFlexibility");
            add("AuraFastWalking");
            add("AuraLight");
            add("AuraHypnotization");
            add("AuraBrilliance");
            add("AuraManaTap");
            add("AuraEternity");
        }};
    }

    private List<String> getWavesSpellsNames() {
        return new ArrayList<String>() {{
            add("WaveOfFire");
            add("WaveOfIce");
            add("WaveOfRocks");
        }};
    }

    private List<String> getWhiteSpellsNames() {
        return new ArrayList<String>() {{
            add("EssenceWhite");
            add("AlmightinessWhite");
        }};
    }

    private List<String> getElementalSpellsNames() {
        return new ArrayList<String>() {{
            add("EssenceElemental");
            add("AlmightinessElemental");
        }};
    }

    private List<String> getChainSpellsNames() {
        return new ArrayList<String>() {{
            add("ChainHallow");
            add("ChainPain");
            add("ChainLifetap");
            add("ChainMutation");
            add("ChainFireburst");
            add("ChainFireball");
            add("ChainIceburst");
            add("ChainRockBullet");
            add("ChainCharm");
            add("ChainShock");
            add("ChainManatap");
        }};
    }

    private List<String> getSummoningSpellsNames() {
        return new ArrayList<String>() {{
            add("SummonGoblin");
            add("SummonSkeleton");
            add("SummonDemon");
            add("SummonChanneler");
            add("SummonSpectre");
            add("SummonWolf");
            add("SummonBear");
            add("SummonTreeWraith");
            add("SummonBlade");
            add("SummonFireGolem");
            add("SummonIceGolem");
            add("SummonEarthGolem");
        }};
    }

    private void addCustomSpellsConfigurations(AbstractConfiguration configuration, List<String> customSpellNames) {
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SPELLS_NAME_MAPPING);
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (customSpellNames.contains(value)) {
                addConfiguration(new SpellParameterModel(new SpellParameterModelParameter(Integer.valueOf(key), 0, null)), configuration);
            }
        }
    }
}
