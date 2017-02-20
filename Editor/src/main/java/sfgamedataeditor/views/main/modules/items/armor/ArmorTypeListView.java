package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class ArmorTypeListView extends AbstractModulesView {

    public ArmorTypeListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "armorTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        List<Pair<String, Class<? extends PresentableView>>> mappings = new ArrayList<>();
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.helmets"), ArmorPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.chest.armor"), ArmorPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.robes"), ArmorPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.legs.armor"), ArmorPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.shield"), ArmorPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.rings"), ArmorPiecesView.class));

        addMappings(mappings);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ArmorTypesPresenter.class;
    }
}
