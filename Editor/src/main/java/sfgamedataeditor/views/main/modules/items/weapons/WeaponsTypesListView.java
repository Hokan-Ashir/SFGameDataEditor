package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public class WeaponsTypesListView extends AbstractModulesView {

    public WeaponsTypesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        Set<String> mappings = new TreeSet<>();
        mappings.add(createMappingName("type.default"));
        mappings.add(createMappingName("type.hand"));
        mappings.add(createMappingName("type.1h.dagger"));
        mappings.add(createMappingName("type.1h.sword"));
        mappings.add(createMappingName("type.1h.axe"));
        mappings.add(createMappingName("type.1h.mace.spiky"));
        mappings.add(createMappingName("type.1h.hammer"));
        mappings.add(createMappingName("type.1h.staff"));
        mappings.add(createMappingName("type.2h.sword"));
        mappings.add(createMappingName("type.2h.axe"));
        mappings.add(createMappingName("type.2h.mace"));
        mappings.add(createMappingName("type.2h.hammer"));
        mappings.add(createMappingName("type.2h.staff"));
        mappings.add(createMappingName("type.2h.spear"));
        mappings.add(createMappingName("type.2h.halberd"));
        mappings.add(createMappingName("type.2h.bow"));
        mappings.add(createMappingName("type.2h.crossbow"));
        mappings.add(createMappingName("type.1h.mace.blunt"));
        mappings.add(createMappingName("type.claw"));
        mappings.add(createMappingName("type.mouth"));
        mappings.add(createMappingName("type.stone.thrower"));

        addMappings(mappings, WeaponPiecesView.class);
    }
    
    private String createMappingName(String weaponTypeKey) {
        return I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, weaponTypeKey);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponsTypesPresenter.class;
    }
}
