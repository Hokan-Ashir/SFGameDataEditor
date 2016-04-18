package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ArmorTypeListView extends AbstractModulesView<ItemTypesView> {

    public ArmorTypeListView(ItemTypesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("armorTypes"));
    }

    @Override
    protected void fillComboBoxMapping() {

    }

}
