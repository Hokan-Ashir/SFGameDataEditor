package sfgamedataeditor.common.widgets.items.weapons.type;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public enum WeaponTypesMap {
    INSTANCE;

    //    01 00 - default weapon type
    //    02 00 - Weapon Type Hand
    //    03 00 - Weapon Type 1H Dagger
    //    04 00 - Weapon Type 1H Sword
    //    05 00 - Weapon Type 1H Axe
    //    06 00 - Weapon Type 1H Mace Spiky
    //    07 00 - Weapon Type 1H Hammer
    //    08 00 - Weapon Type 1H Staff
    //    09 00 - Weapon Type 2H Sword
    //    0A 00 - Weapon Type 2H Axe
    //    0B 00 - Weapon Type 2H Mace
    //    0C 00 - Weapon Type 2H Hammer
    //    0D 00 - Weapon Type 2H Staff
    //    0E 00 - Weapon Type 2H Spear
    //    0F 00 - Weapon Type 2H Halberd
    //    10 00 - Weapon Type 2H Bow
    //    11 00 - Weapon Type 2H Crossbow
    //    12 00 - Weapon Type 1H Mace Blunt
    //    13 00 - Weapon Type Claw
    //    14 00 - Weapon Type Mouth
    //    15 00 - Weapon Type Stone Thrower
    //    16 00 - Weapon Type Orb (artificially created; cause "Orbs" considered weapons, though do not have weapons parameters)
    private final Map<Integer, String> typesMap = new HashMap<Integer, String>() {{
        put(1, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.default"));
        put(2, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.hand"));
        put(3, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.dagger"));
        put(4, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.sword"));
        put(5, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.axe"));
        put(6, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.mace.spiky"));
        put(7, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.hammer"));
        put(8, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.staff"));
        put(9, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.sword"));
        put(10, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.axe"));
        put(11, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.mace"));
        put(12, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.hammer"));
        put(13, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.staff"));
        put(14, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.spear"));
        put(15, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.halberd"));
        put(16, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.bow"));
        put(17, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.2h.crossbow"));
        put(18, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.1h.mace.blunt"));
        put(19, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.claw"));
        put(20, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.mouth"));
        put(21, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.stone.thrower"));
        put(22, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "type.orb"));
    }};

    public Map<Integer, String> getTypesMap() {
        return typesMap;
    }

    public Integer getWeaponTypeByName(String weaponTypeName) {
        for (Map.Entry<Integer, String> entry : typesMap.entrySet()) {
            if (entry.getValue().equals(weaponTypeName)) {
                return entry.getKey();
            }
        }

        return 0;
    }

    public String getWeaponTypeNameById(Integer weaponTypeId) {
        return typesMap.get(weaponTypeId);
    }
}
