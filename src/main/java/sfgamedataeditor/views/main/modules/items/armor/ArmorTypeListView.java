package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorTypeListView extends AbstractModulesView {

    public ArmorTypeListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "armorTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.helmets"), ArmorPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.chest.armor"), ArmorPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.robes"), ArmorPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.legs.armor"), ArmorPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.shield"), ArmorPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.rings"), ArmorPiecesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ArmorTypesPresenter.class;
    }
}
