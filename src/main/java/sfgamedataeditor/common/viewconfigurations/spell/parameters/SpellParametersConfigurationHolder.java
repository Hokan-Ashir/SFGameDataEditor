package sfgamedataeditor.common.viewconfigurations.spell.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.configurations.*;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SpellParametersConfigurationHolder extends AbstractConfigurationHolder {

    // TODO get rid of extensive "static" variables, these are due to overriding
    // protected void fillConfigurationMapping()
    //

    private static final List<String> shieldSpellsNames = new ArrayList<String>() {{
       add("FireShield");
       add("IceShield");
       add("ThornShield");
    }};

    private static final List<String> rainsSpellsNames = new ArrayList<String>() {{
        add("RainOfFire");
        add("Blizzard");
        add("StoneRain");
    }};

    private static final List<String> aurasSpellsNames = new ArrayList<String>() {{
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

    private static final List<String> wavesSpellsNames = new ArrayList<String>() {{
        add("WaveOfFire");
        add("WaveOfIce");
        add("WaveOfRocks");
    }};

    private static final List<String> whiteSpellsNames = new ArrayList<String>() {{
        add("EssenceWhite");
        add("AlmightinessWhite");
    }};

    private static final List<String> elementalSpellsNames = new ArrayList<String>() {{
        add("EssenceElemental");
        add("AlmightinessElemental");
    }};

    private static final List<String> chainSpellsNames = new ArrayList<String>() {{
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

    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new DefaultSpellParameterViewConfiguration());
        addShieldSpellsConfigurations();
        addRainSpellsConfigurations();
        addAurasSpellsConfigurations();
        addWavesSpellsConfigurations();
        addWhiteSpellConfigurations();
        addElementalSpellConfigurations();
        addChainSpellsConfigurations();
    }

    private void addChainSpellsConfigurations() {
        ChainSpellsConfiguration configuration = new ChainSpellsConfiguration();
        addCustomSpellsConfigurations(configuration, chainSpellsNames);
    }

    private void addElementalSpellConfigurations() {
        ElementalSpellConfiguration configuration = new ElementalSpellConfiguration();
        addCustomSpellsConfigurations(configuration, elementalSpellsNames);
    }

    private void addWhiteSpellConfigurations() {
        WhiteSpellConfiguration configuration = new WhiteSpellConfiguration();
        addCustomSpellsConfigurations(configuration, whiteSpellsNames);
    }

    private void addWavesSpellsConfigurations() {
        WavesSpellsConfiguration configuration = new WavesSpellsConfiguration();
        addCustomSpellsConfigurations(configuration, wavesSpellsNames);
    }

    private void addAurasSpellsConfigurations() {
        AurasSpellsConfiguration configuration = new AurasSpellsConfiguration();
        addCustomSpellsConfigurations(configuration, aurasSpellsNames);
    }

    private void addRainSpellsConfigurations() {
        RainsSpellsConfiguration configuration = new RainsSpellsConfiguration();
        addCustomSpellsConfigurations(configuration, rainsSpellsNames);
    }

    private void addShieldSpellsConfigurations() {
        ShieldSpellsConfiguration configuration = new ShieldSpellsConfiguration();
        addCustomSpellsConfigurations(configuration, shieldSpellsNames);
    }

    private void addCustomSpellsConfigurations(AbstractConfiguration configuration, List<String> customSpellNames) {
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SPELLS_NAME_MAPPING);
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (customSpellNames.contains(value)) {
                addConfiguration(new SpellParameterModel(new SpellParameterModelParameter(Integer.valueOf(key), 0)), configuration);
            }
        }
    }
}
