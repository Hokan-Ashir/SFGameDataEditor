package sfgamedataeditor.datamapping;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.*;

public enum Mappings {
    INSTANCE;

    public final Map<String, Integer> SKILL_SCHOOL_MAP = new HashMap<String, Integer>() {{
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "lightCombatArts"), 1);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "heavyCombatArts"), 2);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "archery"), 3);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "whiteMagic"), 4);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "elementalMagic"), 5);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "mindMagic"), 6);
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "blackMagic"), 7);
    }};

    public final Map<String, List<String>> CLASS_SUBCLASS_COMBOBOX_MAP = new LinkedHashMap<String, List<String>>() {{
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        put("", Collections.singletonList(""));
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "lightCombatArts"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "piercingWeapon"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "lightBlades"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "lightBlunts"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "lightArmor")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "heavyCombatArts"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "heavyBlades"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "heavyBlunts"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "heavyArmor"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "shields")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "archery"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "bows"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "crossbows")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "whiteMagic"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "life"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "nature"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "boons")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "elementalMagic"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fire"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "ice"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "earth")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "mindMagic"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "enchantment"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "offensive"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "defensive")
                )
        );
        put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "blackMagic"),
                Arrays.asList("",
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "death"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "necromancy"),
                        I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "curses")
                )
        );
    }};
}
