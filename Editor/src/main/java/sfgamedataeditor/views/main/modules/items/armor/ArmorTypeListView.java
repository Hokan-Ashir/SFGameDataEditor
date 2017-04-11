package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public class ArmorTypeListView extends AbstractModulesView {

    public ArmorTypeListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "armorTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        Set<String> names = new TreeSet<>();
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.helmets"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.chest.armor"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.robes"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.legs.armor"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.shield"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.rings"));

        addMappings(names, ArmorPiecesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ArmorTypesPresenter.class;
    }
}
