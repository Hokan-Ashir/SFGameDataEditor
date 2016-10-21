package sfgamedataeditor.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.*;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.utils.I18N;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public final class TableCreationUtils {

    private static final Logger LOGGER = Logger.getLogger(TableCreationUtils.class);
    private static final int LABEL_LINE_MAX_LENGTH = 12;
    private static final int NUMBER_OF_PARAMETER_FIELDS = 9;
    private static final String FIELD_ATTRIBUTE = "field";
    private static final String NAME_ATTRIBUTE = "name";

    private TableCreationUtils() {
    }

    public static void createSpellSchoolNameTable() {
        recreateTable(SpellSchoolName.class);
        fillSpellSchoolTable();
    }

    private static void fillSpellSchoolTable() {
        final Set<String> spellSchoolsNames = createSpellSchoolsNames();

        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellSchoolName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellSchoolName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (String spellSchoolName : spellSchoolsNames) {
                        dao.create(new SpellSchoolName(spellSchoolName));
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private static Set<String> createSpellSchoolsNames() {
        Set<String> spellSchoolsNames = new HashSet<>();
        List<SpellParameters> allSpellParameters = TableCreationUtils.getAllSpellParameters();
        for (SpellParameters allSpellParameter : allSpellParameters) {
            extractSpellSchoolNamesFromSpell(allSpellParameter, spellSchoolsNames);
        }

        return spellSchoolsNames;
    }

    private static void extractSpellSchoolNamesFromSpell(SpellParameters spellParameter, Set<String> spellSchoolsNames) {
        int schoolRequirement1 = spellParameter.requirementClass1;
        int subSchoolRequirement1 = spellParameter.requirementSubClass1;
        int schoolRequirement2 = spellParameter.requirementClass2;
        int subSchoolRequirement2 = spellParameter.requirementSubClass2;
        int schoolRequirement3 = spellParameter.requirementClass3;
        int subSchoolRequirement3 = spellParameter.requirementSubClass3;

        addSchoolName(schoolRequirement1, subSchoolRequirement1, spellSchoolsNames);
        addSchoolName(schoolRequirement2, subSchoolRequirement2, spellSchoolsNames);
        addSchoolName(schoolRequirement3, subSchoolRequirement3, spellSchoolsNames);
    }

    private static void addSchoolName(int schoolRequirement, int subSchoolRequirement, Set<String> spellSchoolsNames) {
        int schoolId = schoolRequirement * 10 + subSchoolRequirement;
        if (!Mappings.INSTANCE.SPELL_SCHOOL_MAP.containsKey(schoolId)) {
            return;
        }

        spellSchoolsNames.add(Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(schoolId));
    }

    public static void createSpellNameTable() {
        recreateTable(SpellName.class);
        fillSpellNameTableWithPredefinedNames();
    }

    private static void fillSpellNameTableWithPredefinedNames() {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final List<SpellName> spellNames = new ArrayList<>();

        final Map<Integer, String> spellMap = new HashMap<Integer, String>() {{
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

        for (Map.Entry<Integer, String> integerStringEntry : spellMap.entrySet()) {
            SpellName spellNameObject = createSpellNameObject(integerStringEntry.getKey(), integerStringEntry.getValue());
            spellNames.add(spellNameObject);
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (SpellName spellName : spellNames) {
                        dao.create(spellName);
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private static SpellName createSpellNameObject(Integer spellType, String spellName) {
        String name = I18N.INSTANCE.getMessage(spellName + "." + NAME_ATTRIBUTE);
        SpellName spell = new SpellName(spellType, name);
        for (int i = 1; i < NUMBER_OF_PARAMETER_FIELDS + 1; i++) {
            String fieldName = FIELD_ATTRIBUTE + i;
            try {
                Field declaredField = spell.getClass().getDeclaredField(fieldName);
                String parameter;
                try {
                     parameter = I18N.INSTANCE.getMessage(spellName + "." + fieldName);
                } catch (MissingResourceException e) {
                    parameter = I18N.INSTANCE.getMessage("spellParameterNotUsed");
                }
                declaredField.setAccessible(true);
                declaredField.set(spell, convertToMultiline(parameter));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return spell;
    }

    private static String convertToMultiline(String value) {
        String[] subStrings = value.split(" ");
        String result = "<html>";
        int lastNewLineInjectionPosition = 0;
        for (int i = 0; i < subStrings.length; ++i) {
            result = result + subStrings[i] + " ";
            if (result.length() - lastNewLineInjectionPosition > LABEL_LINE_MAX_LENGTH
                    && i != subStrings.length - 1) {
                result = result + "<br>";
                lastNewLineInjectionPosition = result.length();
            }
        }
        return result;
    }

    public static Integer getSpellId(String spellName) {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().selectColumns("spellType").where().eq("name", spellName).query();
            return query.get(0).spellType;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getSpellName(Integer spellType) {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().selectColumns("name").where().eq("spellType", spellType).query();
            if (query.isEmpty()) {
                return null;
            }

            return query.get(0).name;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static SpellName getSpellName(String spellName) {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SpellName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellName.class);
            List<SpellName> query = dao.queryBuilder().where().eq("name", spellName).query();
            return query.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static void createSkillNameTable() {
        recreateTable(SkillName.class);

        ConnectionSource connectionSource = getConnectionSource();
        final Dao<SkillName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillName.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        final Map<Integer, String> skillTypeToNameMap = new HashMap<Integer, String>() {{
            put(1, I18N.INSTANCE.getMessage("lightCombatArts"));
            put(2, I18N.INSTANCE.getMessage("heavyCombatArts"));
            put(3, I18N.INSTANCE.getMessage("archery"));
            put(4, I18N.INSTANCE.getMessage("whiteMagic"));
            put(5, I18N.INSTANCE.getMessage("elementalMagic"));
            put(6, I18N.INSTANCE.getMessage("mindMagic"));
            put(7, I18N.INSTANCE.getMessage("blackMagic"));
        }};

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Map.Entry<Integer, String> integerStringEntry : skillTypeToNameMap.entrySet()) {
                        SkillName spellName = new SkillName(integerStringEntry.getKey(), integerStringEntry.getValue());
                        dao.create(spellName);
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static <T extends OffsetableObject> void updateObject(OffsetableObject objectToSave, Class<T> tClass) {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            Dao<T, Integer> dao = DaoManager.createDao(connectionSource, tClass);
            dao.update((T) objectToSave);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static Set<Integer> getSpellLevels(int spellId) {
        ConnectionSource connectionSource = getConnectionSource();
        try {
            Dao<SpellParameters, Integer> dao = DaoManager.createDao(connectionSource, SpellParameters.class);
            List<SpellParameters> parameters = dao.queryBuilder().selectColumns("requirementLevel1", "requirementLevel2", "requirementLevel3").where().eq("spellNameId", spellId).query();
            Set<Integer> spellLevels = new TreeSet<>();
            for (SpellParameters parameter : parameters) {
                Integer requirementLevel1 = parameter.requirementLevel1;
                if (requirementLevel1 != 0) {
                    spellLevels.add(requirementLevel1);
                }

                Integer requirementLevel2 = parameter.requirementLevel2;
                if (requirementLevel2 != 0) {
                    spellLevels.add(requirementLevel2);
                }

                Integer requirementLevel3 = parameter.requirementLevel3;
                if (requirementLevel3 != 0) {
                    spellLevels.add(requirementLevel3);
                }
            }

            return spellLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public static List<SpellParameters> getSpells(String spellSchoolName) {
        ConnectionSource connectionSource = getConnectionSource();
        List<SpellParameters> spellParameters = Collections.emptyList();
        try {
            Map<Integer, String> spellSchoolMap = Mappings.INSTANCE.SPELL_SCHOOL_MAP;
            for (Map.Entry<Integer, String> integerStringEntry : spellSchoolMap.entrySet()) {
                if (!integerStringEntry.getValue().equals(spellSchoolName)) {
                    continue;
                }

                Integer spellSchoolId = integerStringEntry.getKey();
                int spellSchoolClass = spellSchoolId / 10;
                int spellSchoolSubClass = spellSchoolId % 10;
                Dao<SpellParameters, Integer> dao = DaoManager.createDao(connectionSource, SpellParameters.class);
                Where<SpellParameters, Integer> where = dao.queryBuilder().where();
                Where<SpellParameters, Integer> spellRequirementClass1Where =
                        where.eq("requirementClass1", spellSchoolClass)
                                .and().eq("requirementSubClass1", spellSchoolSubClass);
                Where<SpellParameters, Integer> spellRequirementClass2Where =
                        where.eq("requirementClass2", spellSchoolClass)
                                .and().eq("requirementSubClass2", spellSchoolSubClass);
                Where<SpellParameters, Integer> spellRequirementClass3Where =
                        where.eq("requirementClass3", spellSchoolClass)
                                .and().eq("requirementSubClass3", spellSchoolSubClass);
                spellParameters = where.or(spellRequirementClass1Where, spellRequirementClass2Where, spellRequirementClass3Where).query();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        return spellParameters;
    }

    public static List<SkillParameters> getSkillParameters(int skillSchoolId, int skillLevel) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<SkillParameters, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            return dao.queryBuilder().where().eq("skillTypeId", skillSchoolId).and().eq("level", skillLevel).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static List<SpellParameters> getSpellParameters(int selectedSpellId, int selectedLevel) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<SpellParameters, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            Where<SpellParameters, Integer> where = dao.queryBuilder().where();
            Where<SpellParameters, Integer> spellNameId = where.eq("spellNameId", selectedSpellId);
            Where<SpellParameters, Integer> or = where.or(where.eq("requirementLevel1", selectedLevel),
                    where.eq("requirementLevel2", selectedLevel),
                    where.eq("requirementLevel3", selectedLevel));
            where.and(spellNameId, or);
            return where.query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static void createSkillParametersTable() {
        recreateTable(SkillParameters.class);
    }

    public static void addRecordsToSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
        addRecordsToTable(SkillParameters.class, offsettedData);
    }

    public static void addRecordsToSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        addRecordsToTable(SpellParameters.class, offsettedData);
    }

    private static <T extends OffsetableObject> void addRecordsToTable(final Class<T> ormClass, final List<Pair<byte[], Long>> offsetedData) {
        ConnectionSource connectionSource = getConnectionSource();
        final Dao<T, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ormClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        try {
            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Pair<byte[], Long> longPair : offsetedData) {
                        T object = createObject(ormClass, longPair.getKey());
                        object.setOffset(longPair.getValue());
                        dao.create(object);
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private static <T> T createObject(Class<T> objectClass, byte[] buffer) {
        T object;
        try {
            object = objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        ObjectDataMappingService.INSTANCE.fillObjectWithData(object, buffer);
        return object;
    }

    public static void createSpellParametersTable() {
        recreateTable(SpellParameters.class);
    }

    private static void recreateTable(Class<?> ormObjectClass) {
        ConnectionSource connectionSource = getConnectionSource();
        if (connectionSource == null) return;

        try {
            TableUtils.dropTable(connectionSource, ormObjectClass, true);
            TableUtils.createTableIfNotExists(connectionSource, ormObjectClass);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                connectionSource.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private static ConnectionSource getConnectionSource() {
        ConnectionSource connectionSource;
        try {
            String databaseUrl = "jdbc:h2:file:./sfeditorDatabase;DB_CLOSE_ON_EXIT=FALSE";
            connectionSource =
                    new JdbcConnectionSource(databaseUrl);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return connectionSource;
    }

    public static List<SpellSchoolName> getAllSpellSchoolNames() {
        return getAllTableData(SpellSchoolName.class);
    }

    public static List<SkillParameters> getAllSkillParameters() {
        return getAllTableData(SkillParameters.class);
    }

    public static List<SpellParameters> getAllSpellParameters() {
        return getAllTableData(SpellParameters.class);
    }

    private static <T> List<T> getAllTableData(Class<T> tableClass) {
        ConnectionSource connectionSource = getConnectionSource();
        Dao<T, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, tableClass);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
