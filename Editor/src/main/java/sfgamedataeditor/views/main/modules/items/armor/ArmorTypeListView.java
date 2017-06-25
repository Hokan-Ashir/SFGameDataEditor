package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
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
    public void fillSubViewsMappings() {
        List<SubViewPanelTuple> mappings = new ArrayList<SubViewPanelTuple>() {{
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.helmets")));
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.chest.armor")));
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.robes")));
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.legs.armor")));
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.shield")));
            add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.rings")));
        }};

        addMappings(mappings, ArmorPiecesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ArmorTypesPresenter.class;
    }
}
