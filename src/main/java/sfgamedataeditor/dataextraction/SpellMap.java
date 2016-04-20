package sfgamedataeditor.dataextraction;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.utils.I18N;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SpellMap {
    INSTANCE;

    private static final int NUMBER_OF_PARAMETER_FIELDS = 9;
    private static final String FIELD_ATTRIBUTE = "field";
    private static final String NAME_ATTRIBUTE = "name";
    private static final Map<Integer, String> SPELLMAP = new HashMap<Integer, String>() {{
        put(1, "Fireburst");
        put(2, "Healing");
        put(3, "Death");
        put(4, "Slowness");
        put(5, "Poison");
        put(6, "Invulnerability");
        put(7, "CurePoison");
        put(9, "Freeze");
        put(10, "Fog");
        put(11, "Illuminate");
        put(12, "FireShield");
        put(13, "FireBall");
        put(14, "IceBurst");
        put(15, "IceShield");
        put(16, "Decay");
        put(17, "DecayArea");
        put(18, "Pain");
        put(19, "LifeTap");
        put(20, "SummonGoblin");
        put(21, "Hypnotize");
        put(22, "IceShieldStun");
        put(23, "Pestilence");
        put(24, "CureDisease");
        put(25, "Petrify");
        put(28, "PainArea");
        put(29, "SummonSkeleton");
        put(30, "RaiseDead");
        put(31, "SummonDemon");
        put(32, "DeathGrasp");
        put(33, "SummonChanneler");
        put(34, "Inflexibility");
        put(35, "Weaken");
        put(36, "DarkBanishing");
        put(37, "SlownessArea");
        put(38, "InflexibilityArea");
        put(39, "WeakenArea");
        put(40, "PlagueArea");
        put(41, "Remediless");
        put(42, "DarkMight");
        put(43, "HealingArea");
        put(44, "SentinelHealing");
        put(45, "GreaterHealing");
        put(46, "CharmAnimal");
        put(47, "ThornShield");
        put(48, "Quickness");
        put(49, "QuicknessArea");
        put(50, "Flexibility");
        put(51, "FlexibilityArea");
        put(52, "Strengthen");
        put(53, "StrengthenArea");
        put(54, "Guard");
        put(55, "RemoveCurse");
        put(56, "Regenerate");
        put(57, "HolyMight");
        put(58, "Hallow");
        put(60, "FireShieldDamage");
        put(61, "ThornShieldDamage");
        put(62, "Forget");
        put(63, "SelfIllusion");
        put(64, "Retention");
        put(65, "Brilliance");
        put(66, "SacrificeMana");
        put(67, "ManaTap");
        put(68, "ManaDrain");
        put(69, "Shock");
        put(70, "Disrupt");
        put(71, "Fear");
        put(72, "Confuse");
        put(73, "RainOfFire");
        put(74, "Blizzard");
        put(75, "AcidCloud");
        put(76, "StoneRain");
        put(77, "WallOfRocks");
        put(78, "RingOfRocks");
        put(79, "Amok");
        put(81, "Extinct");
        put(82, "DetectMetal");
        put(83, "DetectMagic");
        put(84, "Arrow");
        put(85, "Berserk");
        put(86, "Invisibility");
        put(87, "Stone");
        put(88, "AuraWeakness");
        put(89, "AuraSuffocation");
        put(90, "SuicideDeath");
        put(91, "AuraLifeTap");
        put(92, "SummonSpectre");
        put(93, "FeignDeath");
        put(94, "AuraSlowFighting");
        put(95, "AuraInflexibility");
        put(96, "DispelWhiteAura");
        put(97, "AuraSlowWalking");
        put(98, "AuraInability");
        put(99, "Suffocation");
        put(100, "Inability");
        put(101, "SlowFighting");
        put(102, "AuraStrength");
        put(103, "AuraHealing");
        put(104, "AuraEndurance");
        put(105, "SuicideHeal");
        put(106, "SummonWolf");
        put(107, "AuraRegeneration");
        put(108, "DominateAnimal");
        put(109, "SummonBear");
        put(110, "AuraFastFighting");
        put(111, "AuraFlexibility");
        put(112, "DispelBlackAura");
        put(113, "AuraFastWalking");
        put(114, "AuraLight");
        put(115, "AuraDexterity");
        put(116, "Dexterity");
        put(117, "Endurance");
        put(118, "FastFighting");
        put(119, "Distract");
        put(120, "Dominate");
        put(121, "ObjectIllusion");
        put(122, "Charm");
        put(123, "Befriend");
        put(124, "Disenchant");
        put(125, "Charisma");
        put(126, "Shockwave");
        put(127, "AuraHypnotization");
        put(128, "Demoralization");
        put(129, "AuraBrilliance");
        put(130, "Enlightenment");
        put(131, "AuraManaTap");
        put(132, "Meditation");
        put(133, "FireElemental");
        put(134, "WaveOfFire");
        put(135, "MeltResistance");
        put(136, "IceElemental");
        put(137, "WaveOfIce");
        put(138, "ChillResistance");
        put(139, "RockBullet");
        put(140, "Conservation");
        put(141, "EarthElemental");
        put(142, "WaveOfRocks");
        put(143, "ArrowTower");
        put(144, "HealingTower");
        put(145, "IceburstTower");
        put(146, "LifeTapAura");
        put(147, "FireBallEffect");
        put(148, "AbilityWarCry");
        put(149, "AbilityBenefactions");
        put(150, "AbilityPatronize");
        put(151, "AbilityEndurance");
        put(152, "AbilityBerserk");
        put(153, "AbilityBlessing");
        put(154, "AbilityShelter");
        put(155, "AbilityDurability");
        put(156, "AbilityTrueShot");
        put(157, "AbilitySteelSkin");
        put(158, "AbilitySalvo");
        put(159, "FireBurstTower");
        put(160, "Spark");
        put(161, "HypnotizeTower");
        put(162, "PainTower");
        put(163, "StoneTower");
        put(164, "ProtectionBlack");
        put(165, "DoNotTouchMe");
        put(166, "HealingAura");
        put(167, "HypnotizeTwo");
        put(168, "IceArrowEffect");
        put(169, "IceBlockEffect");
        put(170, "FireBlockEffect");
        put(171, "ExtinctTower");
        put(172, "ManaTapAura");
        put(173, "FireResistance");
        put(174, "EssenceBlack");
        put(175, "EssenceWhite");
        put(176, "EssenceElemental");
        put(177, "EssenceMental");
        put(178, "AlmightinessBlack");
        put(179, "AlmightinessWhite");
        put(180, "AlmightinessElemental");
        put(181, "AlmightinessMental");
        put(182, "AlmightinessElementalEffect");
        put(183, "EssenceElementalEffect");
        put(184, "Assistance");
        put(185, "HolyTouch");
        put(186, "Revenge");
        put(187, "RootsArea");
        put(188, "SummonTreeWraith");
        put(189, "Roots");
        put(190, "ChainHallow");
        put(191, "Reinforcement");
        put(192, "AuraEternity");
        put(193, "ChainPain");
        put(194, "Cannibalize");
        put(195, "Torture");
        put(196, "ChainLifetap");
        put(197, "DominateUndead");
        put(198, "SummonBlade");
        put(199, "Mutation");
        put(200, "DarknessArea");
        put(201, "ChainMutation");
        put(202, "ChainFireburst");
        put(203, "SummonFireGolem");
        put(204, "ChainFireball");
        put(205, "ChainIceburst");
        put(206, "SummonIceGolem");
        put(207, "FreezeArea");
        put(208, "ChainRockBullet");
        put(209, "SummonEarthGolem");
        put(210, "FeetClay");
        put(211, "MirrorImage");
        put(212, "ChainCharm");
        put(213, "Voodoo");
        put(214, "ChainShock");
        put(215, "HypnotizeArea");
        put(216, "ConfuseArea");
        put(217, "ChainManatap");
        put(218, "ManaShield");
        put(219, "ShiftMana");
        put(220, "AbilityShiftLife");
        put(221, "AbilityRiposte");
        put(222, "AbilityCriticalHits");
        put(223, "AuraSiegeHuman");
        put(225, "AuraSiegeElf");
        put(226, "AuraSiegeOrc");
        put(227, "AuraSiegeTroll");
        put(228, "AuraSiegeDarkElf");
        put(229, "Eternity");
        put(230, "HallowChained");
        put(231, "LifeTapChained");
        put(232, "ManaTapChained");
        put(233, "MutationChained");
        put(234, "FireBurstChained");
        put(235, "IceBurstChained");
        put(236, "RockBulletChained");
        put(237, "CharmChained");
        put(238, "ShockChained");
        put(239, "FireBallChained");
        put(240, "PainChained");
        put(241, "FakeSpellOneFigure");
    }};

    private Map<Integer, Pair<String, List<String>>> spellMap;

    public Map<Integer, Pair<String, List<String>>> getSpellMap() {
        if (spellMap != null) {
            return spellMap;
        }

        spellMap = new HashMap<>();
        for (Map.Entry<Integer, String> integerStringEntry : SpellMap.SPELLMAP.entrySet()) {
            String spellName = I18N.INSTANCE.getMessage(integerStringEntry.getValue() + "." + NAME_ATTRIBUTE);
            List<String> spellParameters = new ArrayList<>();
            for (int i = 0; i < NUMBER_OF_PARAMETER_FIELDS; ++i) {
                try {
                    spellParameters.add(I18N.INSTANCE.getMessage(integerStringEntry.getValue() + "." + FIELD_ATTRIBUTE + String.valueOf(i + 1)));
                } catch (Exception e) {
                    // TODO ignore this exception, this is happening when i18n can't find
                    // corresponding value in ResourceBundle, read from file
                    // this can be improved and instead of just catching all exceptions
                    // we can specify amount of spell parameters specific for each spell
                    // in SpellMap.SPELLMAP variable
                    // but due to non-common mechanism of determine how many parameters
                    // spell may have, its easier and unifiable to catch exceptions
                    //
                    // moreover some spells has different amount of unnamed parameters
                    // so they are marked as stubs in resourceBundle files like (spellName.fieldX = ???)
                    // in this case trying to identify ALL possible fields is much more
                    // effective using try-catch mechanism
                }
            }
            spellMap.put(integerStringEntry.getKey(), new Pair<>(spellName, spellParameters));
        }

        return spellMap;
    }
}
