package sfgamedataeditor.common.cache.icons.aliases;

public class HeroesAliases extends AbstractIconPathAlias {

    @Override
    protected void fillAliasMap() {
        // Priest marticus/Mentalist zerebos/Archer thrasylon
        addAlias("3665.png", "750.png");
        addAlias("4763.png", "750.png");

        // Warrior karia/Priest yasmin/Archer alahnna
        addAlias("4722.png", "751.png");
        addAlias("4757.png", "751.png");

        // Mage shindar/Archer shalir/Warrior rodgar/Dunhan/Archer finian/Mage lemuel/Warlock sinistro
        addAlias("758.png", "752.png");
        addAlias("763.png", "752.png");
        addAlias("3625.png", "752.png");
        addAlias("4767.png", "752.png");
        addAlias("7328.png", "752.png");
        addAlias("7330.png", "752.png");

        // Mage antarus/Mentalist selthas
        addAlias("2737.png", "753.png");

        // Fighter tilleth/Fighter ivor/Mentalist catlan
        addAlias("767.png", "755.png");
        addAlias("4634.png", "755.png");

        // Warlock antius/Fighter malvaren/Warlock antius/Mentalist garbarius/Archer ulme
        addAlias("770.png", "756.png");
        addAlias("3376.png", "756.png");
        addAlias("4146.png", "756.png");
        addAlias("4737.png", "756.png");

        // Mentalist iolan/Warlock finistra/Mentalist morna/Priest virginie
        addAlias("3854.png", "757.png");
        addAlias("4129.png", "757.png");
        addAlias("4771.png", "757.png");
    }

    @Override
    protected String getImagePathPrefix() {
        return "/images/heroes/";
    }
}
