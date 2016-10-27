package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.objects.SpellSchoolName;
import sfgamedataeditor.utils.I18N;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public enum  SpellSchoolNameTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SpellSchoolNameTableService.class);

    public void createSpellSchoolNameTable() {
        CommonTableService.INSTANCE.recreateTable(SpellSchoolName.class);
        fillSpellSchoolTable();
    }

    private void fillSpellSchoolTable() {
        final Set<SpellSchoolName> spellSchoolsNames = createSpellSchoolsNames();

        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
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
                    for (SpellSchoolName spellSchoolName : spellSchoolsNames) {
                        dao.create(spellSchoolName);
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

    private Set<SpellSchoolName> createSpellSchoolsNames() {
        Set<SpellSchoolName> spellSchoolsNames = new HashSet<>();
        List<SpellParameters> allSpellParameters = SpellParametersTableService.INSTANCE.getAllSpellParameters();
        for (SpellParameters allSpellParameter : allSpellParameters) {
            extractSpellSchoolNamesFromSpell(allSpellParameter, spellSchoolsNames);
        }

        return spellSchoolsNames;
    }

    private void extractSpellSchoolNamesFromSpell(SpellParameters spellParameter, Set<SpellSchoolName> spellSchoolsNames) {
        Map<Integer, String> spellSchoolMap = new HashMap<Integer, String>() {{
            put(0, getMessage("other"));
            put(10, getMessage("lightCombatArts"));
            put(11, getMessage("lightCombatArts", "piercingWeapon"));
            put(12, getMessage("lightCombatArts", "lightBlades"));
            put(13, getMessage("lightCombatArts", "lightBlunts"));
            put(14, getMessage("lightCombatArts", "lightArmor"));
            put(20, getMessage("heavyCombatArts"));
            put(21, getMessage("heavyCombatArts", "heavyBlades"));
            put(22, getMessage("heavyCombatArts", "heavyBlunts"));
            put(23, getMessage("heavyCombatArts", "heavyArmor"));
            put(24, getMessage("heavyCombatArts", "shields"));
            put(30, getMessage("archery"));
            put(31, getMessage("archery", "bows"));
            put(32, getMessage("archery", "crossbows"));
            put(40, getMessage("whiteMagic"));
            put(41, getMessage("whiteMagic", "life"));
            put(42, getMessage("whiteMagic", "nature"));
            put(43, getMessage("whiteMagic", "boons"));
            put(50, getMessage("elementalMagic"));
            put(51, getMessage("elementalMagic", "fire"));
            put(52, getMessage("elementalMagic", "ice"));
            put(53, getMessage("elementalMagic", "earth"));
            put(60, getMessage("mindMagic"));
            put(61, getMessage("mindMagic", "enchantment"));
            put(62, getMessage("mindMagic", "offensive"));
            put(63, getMessage("mindMagic", "defensive"));
            put(70, getMessage("blackMagic"));
            put(71, getMessage("blackMagic", "death"));
            put(72, getMessage("blackMagic", "necromancy"));
            put(73, getMessage("blackMagic", "curses"));
        }};

        List<SpellSchoolName> possibleNonZeroSchoolNames = getPossibleNonZeroSchoolNames(spellParameter, spellSchoolMap);
        if (!possibleNonZeroSchoolNames.isEmpty()) {
            spellSchoolsNames.addAll(possibleNonZeroSchoolNames);
        } else {
            // add spell school for "Other" spells, that is which have only zeros in school/subSchool requirement fields
            spellSchoolsNames.add(new SpellSchoolName(spellSchoolMap.get(0), 0));
        }
    }

    private List<SpellSchoolName> getPossibleNonZeroSchoolNames(SpellParameters spellParameter, Map<Integer, String> spellSchoolMap) {
        int schoolRequirement1 = spellParameter.requirementClass1;
        int subSchoolRequirement1 = spellParameter.requirementSubClass1;
        int schoolRequirement2 = spellParameter.requirementClass2;
        int subSchoolRequirement2 = spellParameter.requirementSubClass2;
        int schoolRequirement3 = spellParameter.requirementClass3;
        int subSchoolRequirement3 = spellParameter.requirementSubClass3;

        List<SpellSchoolName> result = new ArrayList<>();
        addNonZeroSchoolName(schoolRequirement1, subSchoolRequirement1, spellSchoolMap, result);
        addNonZeroSchoolName(schoolRequirement2, subSchoolRequirement2, spellSchoolMap, result);
        addNonZeroSchoolName(schoolRequirement3, subSchoolRequirement3, spellSchoolMap, result);

        return result;
    }

    private void addNonZeroSchoolName(int schoolRequirement, int subSchoolRequirement, Map<Integer, String> spellSchoolMap, List<SpellSchoolName> result) {
        int schoolId = schoolRequirement * 10 + subSchoolRequirement;
        if (schoolId == 0) {
            return;
        }

        result.add(new SpellSchoolName(spellSchoolMap.get(schoolId), schoolId));
    }

    private String getMessage(String mainSchool, String... subSchools) {
        StringBuilder builder = new StringBuilder();
        builder.append(I18N.INSTANCE.getMessage(mainSchool));
        if (subSchools != null && subSchools.length != 0) {
            builder.append(" : ");
            for (String subSchool : subSchools) {
                builder.append(I18N.INSTANCE.getMessage(subSchool));
                builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    public List<SpellSchoolName> getAllSpellSchoolNames() {
        return CommonTableService.INSTANCE.getAllTableData(SpellSchoolName.class);
    }

    public SpellSchoolName getSpellSchoolId(String spellSchoolName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<SpellSchoolName, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SpellSchoolName.class);
            return dao.queryBuilder().selectColumns("spellSchoolId").where().eq("name", spellSchoolName).query().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return new SpellSchoolName();
        }
    }
}
