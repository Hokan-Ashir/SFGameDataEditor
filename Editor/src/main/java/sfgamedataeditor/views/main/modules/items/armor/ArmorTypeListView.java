package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
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
        List<ObjectTuple> mappings = new ArrayList<ObjectTuple>() {{
            add(createTuple( "items.armor.helmets"));
            add(createTuple("items.armor.chest.armor"));
            add(createTuple("items.armor.robes"));
            add(createTuple("items.armor.legs.armor"));
            add(createTuple("items.armor.shield"));
            add(createTuple("items.armor.rings"));
        }};

        addMappings(mappings, ArmorPiecesView.class);
    }

    private ObjectTuple createTuple(String i18nKey) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey), Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, i18nKey)));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ArmorTypesPresenter.class;
    }

    @Override
    public void localize() {
        setModuleName(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "armorTypes"));
    }
}
