package sfgamedataeditor.common.cache.icons.aliases;

public class SpellAliases extends AbstractIconPathAlias {
    @Override
    protected void fillAliasMap() {
        addAlias("AbilityBenefactions.png", "AbilityBlessing.png");
        addAlias("AbilityWarCry.png", "AbilityBerserk.png");
        addAlias("AbilityDurability.png", "AbilityEndurance.png");
        addAlias("AbilityPatronize.png", "AbilityShelter.png");

        addAlias("Brilliance.png", "AuraBrilliance.png");
        addAlias("Dexterity.png", "AuraDexterity.png");
        addAlias("Eternity.png", "AuraEternity.png");
        addAlias("Endurance.png", "AuraEndurance.png");
        addAlias("FastFighting.png", "AuraFastFighting.png");
        addAlias("Inability.png", "AuraInability.png");
        addAlias("Inflexibility.png", "AuraInflexibility.png");
        addAlias("Regenerate.png", "AuraRegeneration.png");
        addAlias("SlowFighting.png", "AuraSlowFighting.png");
        addAlias("Slowness.png", "AuraSlowWalking.png");
        addAlias("Strengthen.png", "AuraStrength.png");
        addAlias("Suffocation.png", "AuraSuffocation.png");
        addAlias("Weaken.png", "AuraWeakness.png");
    }

    @Override
    protected String getImagePathPrefix() {
        return "/images/spells_and_scrolls/";
    }
}
