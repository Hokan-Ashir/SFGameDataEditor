package sfgamedataeditor.common.cache.icons.aliases;

public class CreaturesAliases extends AbstractIconPathAlias {
    @Override
    protected void fillAliasMap() {
        // Mercenaries/Mercenary - 1/Mercenary - 2
        addAlias("2246.png", "1426.png");
        addAlias("2823.png", "1426.png");

        // Hill giant/Mountain giant/Braga/Kraga/Kraga - 2/Mjorn - 1/Mjorn - 2
        addAlias("130.png", "129.png");
        addAlias("131.png", "129.png");
        addAlias("132.png", "129.png");
        addAlias("2581.png", "129.png");
        addAlias("2582.png", "129.png");
        addAlias("925.png", "129.png");

        // Derwish/Flamedancer/Mirage
        addAlias("2049.png", "2048.png");
        addAlias("2091.png", "2048.png");

        // Soulforger - 1/Soulforger - 2/Soulforger - 3
        addAlias("1392.png", "1044.png");
        addAlias("1393.png", "1044.png");

        // Lichking anktahr/Korshar
        addAlias("2068.png", "592.png");

        // Urias - 1/Urias - 2/Urias - 3/Urias - 4
        addAlias("2108.png", "931.png");
        addAlias("2534.png", "931.png");
        addAlias("2572.png", "931.png");

        // Shin tar guar/Ghost of udwin
        addAlias("1097.png", "640.png");

        // Iridon/Laurin - 1/Laurin - 2/Laurin - 3/Laurin - 4/Laurin - 5
        addAlias("2308.png", "2307.png");
        addAlias("2309.png", "2307.png");
        addAlias("2315.png", "2307.png");
        addAlias("2316.png", "2307.png");
        addAlias("2317.png", "2307.png");

        // Wrath of stone/Stonefist
        addAlias("2076.png", "125.png");

        // Mighty elf titan/Dark treant/Elf titan - 1/Elf titan - 2
        addAlias("2283.png", "2010.png");
        addAlias("1079.png", "2010.png");
        addAlias("2508.png", "2010.png");

        // Uthar Griffonsteel - 1/ Uthar Griffonsteel - 2/
        addAlias("1228.png", "1198.png");
        addAlias("1388.png", "1198.png");

        // Darius - 2/Lea
        addAlias("1112.png", "2910.png");

        // Undead goblin - 1/2/3/4/5/6/7/8/9/10/11
        addAlias("162.png", "161.png");
        addAlias("593.png", "161.png");
        addAlias("594.png", "161.png");
        addAlias("595.png", "161.png");
        addAlias("596.png", "161.png");
        addAlias("597.png", "161.png");
        addAlias("2191.png", "161.png");
        addAlias("2192.png", "161.png");
        addAlias("2193.png", "161.png");
        addAlias("2194.png", "161.png");

        // Hazim warrior/Krongor/Hazim guard - 1/Hazim guard - 2
        addAlias("2904.png", "2094.png");
        addAlias("2436.png", "2094.png");
        addAlias("2954.png", "2094.png");

        // Firebone fiend - 1/Firebone fiend - 2
        addAlias("2958.png", "2662.png");
    }

    @Override
    protected String getImagePathPrefix() {
        return "/images/creatures/";
    }
}
