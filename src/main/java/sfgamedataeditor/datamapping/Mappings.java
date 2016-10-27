package sfgamedataeditor.datamapping;

import sfgamedataeditor.utils.I18N;

import java.util.*;

public enum Mappings {
    INSTANCE;

    public Map<String, Integer> SKILL_SCHOOL_MAP = new HashMap<String, Integer>() {{
        put(I18N.INSTANCE.getMessage("other"), 0);
        put(I18N.INSTANCE.getMessage("lightCombatArts"), 1);
        put(I18N.INSTANCE.getMessage("heavyCombatArts"), 2);
        put(I18N.INSTANCE.getMessage("archery"), 3);
        put(I18N.INSTANCE.getMessage("whiteMagic"), 4);
        put(I18N.INSTANCE.getMessage("elementalMagic"), 5);
        put(I18N.INSTANCE.getMessage("mindMagic"), 6);
        put(I18N.INSTANCE.getMessage("blackMagic"), 7);
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
}
