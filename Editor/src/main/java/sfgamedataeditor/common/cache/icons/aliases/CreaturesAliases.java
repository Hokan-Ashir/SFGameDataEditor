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

        // Wrath of stone/Stonefist/Stone golem - 1/Stone golem - 2/Stone golem - 3/Stone golem - 4/Stone golem - 5/Stone golem - 6/Stone golem - 7/Stone golem - 8
        addAlias("2076.png", "125.png");
        addAlias("123.png", "125.png");
        addAlias("1403.png", "125.png");
        addAlias("2258.png", "125.png");
        addAlias("2259.png", "125.png");
        addAlias("2260.png", "125.png");
        addAlias("2261.png", "125.png");
        addAlias("2269.png", "125.png");
        addAlias("2270.png", "125.png");

        // Mighty elf titan/Dark treant/Elf titan - 1/Elf titan - 2
        addAlias("2283.png", "2010.png");
        addAlias("1079.png", "2010.png");
        addAlias("2508.png", "2010.png");

        // Uthar Griffonsteel - 1/ Uthar Griffonsteel - 2/Dark griffonrider
        // Mighty human titan/Human titan - 1/Human titan - 2
        addAlias("1228.png", "1198.png");
        addAlias("1388.png", "1198.png");
        addAlias("1363.png", "1198.png");
        addAlias("2284.png", "1198.png");
        addAlias("1099.png", "1198.png");
        addAlias("2507.png", "1198.png");

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

        // Vulkanoid/Fire golem - 1/Fire golem - 2/Fire golem - 3/Fire golem - 4
        addAlias("2248.png", "998.png");
        addAlias("2250.png", "998.png");
        addAlias("2252.png", "998.png");
        addAlias("2253.png", "998.png");

        // Icefist/Ice golem - 1/Ice golem - 2/Ice golem - 3/Ice golem - 4/Ice golem - 5/Ice golem - 6/Ice golem - 7
        addAlias("2254.png", "2313.png");
        addAlias("2255.png", "2313.png");
        addAlias("2256.png", "2313.png");
        addAlias("2257.png", "2313.png");
        addAlias("2807.png", "2313.png");
        addAlias("2820.png", "2313.png");
        addAlias("2922.png", "2313.png");

        // all risen dead
        for (int i = 1253; i <= 1281; i++) {
            addAlias(String.valueOf(i) + ".png", "1252.png");
        }

        for (int i = 2360; i <= 2379; i++) {
            addAlias(String.valueOf(i) + ".png", "1252.png");
        }

        // Cave orc-1/Cave orc-2
        addAlias("2232.png", "1161.png");

        // Rohen-1/Rohen-2/Rohen-3/Rohen-4/Rohen-5/Rohen the master of elements
        addAlias("1377.png", "856.png");
        addAlias("2477.png", "856.png");
        addAlias("2663.png", "856.png");
        addAlias("2680.png", "856.png");
        addAlias("2707.png", "856.png");

        //Raith the black - 1/Raith the black - 2
        addAlias("2470.png", "2195.png");

        //Swine-1/Swine-2
        addAlias("1046.png", "1045.png");

        addBalisisksAliases();
        addBeastmanAliases();
        addBeetlesAliases();
        addBladesAliases();
        addBriarwolvesAliases();
    }

    private void addBriarwolvesAliases() {
        // Briarwolves sergent/Briarwolves corporal
        addAlias("983.png", "982.png");

        // Ismail/Briarwolves Enchanter
        addAlias("971.png", "962.png");

        // Captain Ciar/Briar guard/Briarwolves elite
        addAlias("1054.png", "980.png");
        addAlias("1344.png", "980.png");

        // Briarwolves captain juno/Briarwolves armsman
        addAlias("987.png", "970.png");

        // Mechlan - 1/Mechlan - 2/Mechlan - 3/Mechlan - 4
        //Mechlan - 5/Mechlan - 6/Mechlan - 7/Mechlan - 8
        addAlias("1330.png", "984.png");
        addAlias("1332.png", "984.png");
        addAlias("1333.png", "984.png");
        addAlias("1334.png", "984.png");
        addAlias("1335.png", "984.png");
        addAlias("1336.png", "984.png");
        addAlias("1337.png", "984.png");
    }

    private void addBladesAliases() {
        // Blade minion - 1/Blade minion - 2/Blade iceweaver/Blade changer - 1/Blade changer - 2
        // Blade changer - 3
        // Blade servant
        addAlias("304.png", "302.png");
        addAlias("310.png", "302.png");
        addAlias("2485.png", "302.png");
        addAlias("2730.png", "302.png");
        addAlias("2731.png", "302.png");
        addAlias("2732.png", "302.png");

        // Blade defender/Blade changer - 7/Blade changer - 8/Blade changer - 9
        // Blade warlord - 1/Blade warlord - 2/Blade overlord/Blade runner/Bladeweaver
        // Blade Greater Weaver/Blade Captain/Blade devastator/Blade Nightmare
        addAlias("2733.png", "1375.png");
        addAlias("2734.png", "1375.png");
        addAlias("2735.png", "1375.png");
        addAlias("306.png", "1375.png");
        addAlias("1102.png", "1375.png");
        addAlias("303.png", "1375.png");
        addAlias("312.png", "1375.png");
        addAlias("1418.png", "1375.png");
        addAlias("1419.png", "1375.png");
        addAlias("1420.png", "1375.png");
        addAlias("2280.png", "1375.png");
        addAlias("2483.png", "1375.png");
        addAlias("2416.png", "1375.png");

        // Blade warlord - 1/Blade warlord - 2
        // Blade changer - 7/Blade changer - 8/Blade changer - 9
        // Princeps of the first order/Blade grandmaster
        // Blade frostcaller/Servant of the forger/Blade lord of ice
        // Blade master of strategy
        addAlias("2280.png", "1420.png");
        addAlias("2736.png", "1420.png");
        addAlias("2737.png", "1420.png");
        addAlias("2738.png", "1420.png");
        addAlias("1105.png", "1420.png");
        addAlias("2484.png", "1420.png");
        addAlias("317.png", "1420.png");
        addAlias("2580.png", "1420.png");
        addAlias("2417.png", "1420.png");
        addAlias("2845.png", "1420.png");

        // Soulforger - 1/Soulforger - 2/Soulforger - 3
        // Lesser soulforger - 1/Lesser soulforger - 2
        addAlias("1392.png", "1044.png");
        addAlias("1393.png", "1044.png");
        addAlias("2425.png", "1044.png");
        addAlias("2579.png", "1044.png");
    }

    private void addBeetlesAliases() {
        // Lesser forest beetle/Forest beetle/Scythecrawler
        addAlias("1002.png", "208.png");
        addAlias("737.png", "208.png");

        // Giant rock beetle/Greater sand beetle
        addAlias("2172.png", "211.png");
    }

    private void addBeastmanAliases() {
        // Clawripper/Cave Devil-1
        addAlias("1912.png", "725.png");

        // Beastman butcher/The Ripper
        addAlias("916.png", "2276.png");

        // Beastman scratchling - 1/Weak Beastman
        addAlias("706.png", "689.png");
    }

    private void addBalisisksAliases() {
        // Basilisk-1/Basilisk-2/Lava Crawler/Mindbender/Sandworm/Stonewyrm
        addAlias("1282.png", "319.png");
        addAlias("1723.png", "319.png");
        addAlias("996.png", "319.png");
        addAlias("2174.png", "319.png");
        addAlias("1048.png", "319.png");
    }

    @Override
    protected String getImagePathPrefix() {
        return "/images/creatures/";
    }
}
