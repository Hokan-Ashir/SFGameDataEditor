package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
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

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ArmorTypesController.class;
    }
}
