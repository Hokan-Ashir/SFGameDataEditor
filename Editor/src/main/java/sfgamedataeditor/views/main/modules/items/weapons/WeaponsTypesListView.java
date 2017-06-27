package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class WeaponsTypesListView extends AbstractModulesView {

    public WeaponsTypesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = new ArrayList<ObjectTuple>() {{
            add(createMappingName("type.default"));
            add(createMappingName("type.hand"));
            add(createMappingName("type.1h.dagger"));
            add(createMappingName("type.1h.sword"));
            add(createMappingName("type.1h.axe"));
            add(createMappingName("type.1h.mace.spiky"));
            add(createMappingName("type.1h.hammer"));
            add(createMappingName("type.1h.staff"));
            add(createMappingName("type.2h.sword"));
            add(createMappingName("type.2h.axe"));
            add(createMappingName("type.2h.mace"));
            add(createMappingName("type.2h.hammer"));
            add(createMappingName("type.2h.staff"));
            add(createMappingName("type.2h.spear"));
            add(createMappingName("type.2h.halberd"));
            add(createMappingName("type.2h.bow"));
            add(createMappingName("type.2h.crossbow"));
            add(createMappingName("type.1h.mace.blunt"));
            add(createMappingName("type.claw"));
            add(createMappingName("type.mouth"));
            add(createMappingName("type.stone.thrower"));
            add(createMappingName("type.orb"));
        }};

        addMappings(mappings, WeaponPiecesView.class);
    }
    
    private ObjectTuple createMappingName(String weaponTypeKey) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, weaponTypeKey));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponsTypesPresenter.class;
    }
}
