package sfgamedataeditor.datamapping;

import sfgamedataeditor.utils.I18N;

import java.util.*;

public enum Mappings {
    INSTANCE;

    public Map<String, Integer> SKILL_SCHOOL_MAP = new HashMap<String, Integer>() {{
        put(I18N.INSTANCE.getMessage("lightCombatArts"), 1);
        put(I18N.INSTANCE.getMessage("heavyCombatArts"), 2);
        put(I18N.INSTANCE.getMessage("archery"), 3);
        put(I18N.INSTANCE.getMessage("whiteMagic"), 4);
        put(I18N.INSTANCE.getMessage("elementalMagic"), 5);
        put(I18N.INSTANCE.getMessage("mindMagic"), 6);
        put(I18N.INSTANCE.getMessage("blackMagic"), 7);
    }};

    public Map<Integer, String> SPELL_SCHOOL_MAP = new HashMap<Integer, String>() {{
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

    public Map<String, List<String>> CLASS_SUBCLASS_COMBOBOX_MAP = new LinkedHashMap<String, List<String>>() {{
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        put("", Collections.singletonList(""));
        put(I18N.INSTANCE.getMessage("lightCombatArts"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("piercingWeapon"),
                        I18N.INSTANCE.getMessage("lightBlades"),
                        I18N.INSTANCE.getMessage("lightBlunts"),
                        I18N.INSTANCE.getMessage("lightArmor")
                )
        );
        put(I18N.INSTANCE.getMessage("heavyCombatArts"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("heavyBlades"),
                        I18N.INSTANCE.getMessage("heavyBlunts"),
                        I18N.INSTANCE.getMessage("heavyArmor"),
                        I18N.INSTANCE.getMessage("shields")
                )
        );
        put(I18N.INSTANCE.getMessage("archery"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("bows"),
                        I18N.INSTANCE.getMessage("crossbows")
                )
        );
        put(I18N.INSTANCE.getMessage("whiteMagic"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("life"),
                        I18N.INSTANCE.getMessage("nature"),
                        I18N.INSTANCE.getMessage("boons")
                )
        );
        put(I18N.INSTANCE.getMessage("elementalMagic"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("fire"),
                        I18N.INSTANCE.getMessage("ice"),
                        I18N.INSTANCE.getMessage("earth")
                )
        );
        put(I18N.INSTANCE.getMessage("mindMagic"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("enchantment"),
                        I18N.INSTANCE.getMessage("offensive"),
                        I18N.INSTANCE.getMessage("defensive")
                )
        );
        put(I18N.INSTANCE.getMessage("blackMagic"),
                Arrays.asList("",
                        I18N.INSTANCE.getMessage("death"),
                        I18N.INSTANCE.getMessage("necromancy"),
                        I18N.INSTANCE.getMessage("curses")
                )
        );
    }};

    private String getMessage(String mainSchool, String... subSchools) {
        StringBuilder builder = new StringBuilder();
        builder.append(I18N.INSTANCE.getMessage(mainSchool));
        if (subSchools != null) {
            builder.append(" : ");
            for (String subSchool : subSchools) {
                builder.append(I18N.INSTANCE.getMessage(subSchool));
                builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
