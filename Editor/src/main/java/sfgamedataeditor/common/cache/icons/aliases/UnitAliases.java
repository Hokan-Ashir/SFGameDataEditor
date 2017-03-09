package sfgamedataeditor.common.cache.icons.aliases;

public class UnitAliases extends AbstractIconPathAlias {

    @Override
    protected void fillAliasMap() {
        // dwarven elite/elder update
        addAlias("1236.png", "1235.png");

        // elven priest/wintermage/druid update
        addAlias("1240.png", "1239.png");
        addAlias("1435.png", "1239.png");

        // orc drummer/hornblower upgrade
        addAlias("1250.png", "1249.png");

        // troll hurler/smasher upgrade
        addAlias("1288.png", "1284.png");

        // troll champion/destroyer upgrade
        addAlias("1290.png", "1289.png");

        // darkelven deathknight/havoc upgrade
        addAlias("1299.png", "1296.png");
    }

    @Override
    protected String getImagePathPrefix() {
        return "/images/units/";
    }
}
