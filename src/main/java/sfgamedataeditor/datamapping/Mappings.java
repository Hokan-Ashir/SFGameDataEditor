package sfgamedataeditor.datamapping;

import sfgamedataeditor.utils.I18N;

import java.util.*;

public enum Mappings {
    INSTANCE;

    public Map<String, Integer> SKILL_SCHOOL_MAP = new HashMap<String, Integer>() {{
        put(I18N.getMessage("lightCombatArts"), 1);
        put(I18N.getMessage("heavyCombatArts"), 2);
        put(I18N.getMessage("archery"), 3);
        put(I18N.getMessage("whiteMagic"), 4);
        put(I18N.getMessage("elementalMagic"), 5);
        put(I18N.getMessage("mindMagic"), 6);
        put(I18N.getMessage("blackMagic"), 7);
    }};

    public Map<String, SpellRequirementTuple> SPELL_SCHOOL_MAP = new HashMap<String, SpellRequirementTuple>() {{
        put(I18N.getMessage("lightCombatArts"), new SpellRequirementTuple(1));
        put(I18N.getMessage("heavyCombatArts"), new SpellRequirementTuple(2));
        put(I18N.getMessage("archery"), new SpellRequirementTuple(3));
        put(getMessage("whiteMagic", "all"), new SpellRequirementTuple(4, 1, 4, 2, 4, 3));
        put(getMessage("whiteMagic", "life", "nature"), new SpellRequirementTuple(4, 1, 4, 2));
        put(getMessage("whiteMagic", "life", "boons"), new SpellRequirementTuple(4, 1, 4, 3));
        put(getMessage("whiteMagic", "nature", "boons"), new SpellRequirementTuple(4, 2, 4, 3));
        put(getMessage("whiteMagic", "life"), new SpellRequirementTuple(4, 1));
        put(getMessage("whiteMagic", "nature"), new SpellRequirementTuple(4, 2));
        put(getMessage("whiteMagic", "boons"), new SpellRequirementTuple(4, 3));
        put(getMessage("elementalMagic", "all"), new SpellRequirementTuple(5, 1, 5, 2, 5, 3));
        put(getMessage("elementalMagic", "fire", "ice"), new SpellRequirementTuple(5, 1, 5, 2));
        put(getMessage("elementalMagic", "fire", "earth"), new SpellRequirementTuple(5, 1, 5, 3));
        put(getMessage("elementalMagic", "ice", "earth"), new SpellRequirementTuple(5, 2, 5, 3));
        put(getMessage("elementalMagic", "fire"), new SpellRequirementTuple(5, 1));
        put(getMessage("elementalMagic", "ice"), new SpellRequirementTuple(5, 2));
        put(getMessage("elementalMagic", "earth"), new SpellRequirementTuple(5, 3));
        put(getMessage("mindMagic", "all"), new SpellRequirementTuple(6, 1, 6, 2, 6, 3));
        put(getMessage("mindMagic", "offensive", "enchantment"), new SpellRequirementTuple(6, 1, 6, 2));
        put(getMessage("mindMagic", "offensive", "defensive"), new SpellRequirementTuple(6, 1, 6, 3));
        put(getMessage("mindMagic", "enchantment", "defensive"), new SpellRequirementTuple(6, 2, 6, 3));
        put(getMessage("mindMagic", "offensive"), new SpellRequirementTuple(6, 1));
        put(getMessage("mindMagic", "enchantment"), new SpellRequirementTuple(6, 2));
        put(getMessage("mindMagic", "defensive"), new SpellRequirementTuple(6, 3));
        put(getMessage("blackMagic", "all"), new SpellRequirementTuple(7, 1, 7, 2, 7, 3));
        put(getMessage("blackMagic", "death", "necromancy"), new SpellRequirementTuple(7, 1, 7, 2));
        put(getMessage("blackMagic", "death", "curses"), new SpellRequirementTuple(7, 1, 7, 3));
        put(getMessage("blackMagic", "necromancy", "curses"), new SpellRequirementTuple(7, 2, 7, 3));
        put(getMessage("blackMagic", "death"), new SpellRequirementTuple(7, 1));
        put(getMessage("blackMagic", "necromancy"), new SpellRequirementTuple(7, 2));
        put(getMessage("blackMagic", "curses"), new SpellRequirementTuple(7, 3));
    }};
    public Map<String, List<String>> CLASS_SUBCLASS_COMBOBOX_MAP = new LinkedHashMap<String, List<String>>() {{
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        put("", Collections.singletonList(""));
        put(I18N.getMessage("lightCombatArts"),
                Arrays.asList("",
                        I18N.getMessage("piercingWeapon"),
                        I18N.getMessage("lightBlades"),
                        I18N.getMessage("lightBlunts"),
                        I18N.getMessage("lightArmor")
                )
        );
        put(I18N.getMessage("heavyCombatArts"),
                Arrays.asList("",
                        I18N.getMessage("heavyBlades"),
                        I18N.getMessage("heavyBlunts"),
                        I18N.getMessage("heavyArmor"),
                        I18N.getMessage("shields")
                )
        );
        put(I18N.getMessage("archery"),
                Arrays.asList("",
                        I18N.getMessage("bows"),
                        I18N.getMessage("crossbows")
                )
        );
        put(I18N.getMessage("whiteMagic"),
                Arrays.asList("",
                        I18N.getMessage("life"),
                        I18N.getMessage("nature"),
                        I18N.getMessage("boons")
                )
        );
        put(I18N.getMessage("elementalMagic"),
                Arrays.asList("",
                        I18N.getMessage("fire"),
                        I18N.getMessage("ice"),
                        I18N.getMessage("earth")
                )
        );
        put(I18N.getMessage("mindMagic"),
                Arrays.asList("",
                        I18N.getMessage("enchantment"),
                        I18N.getMessage("offensive"),
                        I18N.getMessage("defensive")
                )
        );
        put(I18N.getMessage("blackMagic"),
                Arrays.asList("",
                        I18N.getMessage("death"),
                        I18N.getMessage("necromancy"),
                        I18N.getMessage("curses")
                )
        );
    }};

    private String getMessage(String mainSchool, String... subSchools) {
        StringBuilder builder = new StringBuilder();
        builder.append(I18N.getMessage(mainSchool));
        if (subSchools != null) {
            builder.append(" : ");
            for (String subSchool : subSchools) {
                builder.append(I18N.getMessage(subSchool));
                builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
