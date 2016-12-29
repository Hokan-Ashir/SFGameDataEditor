package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class UnitListView extends AbstractModulesView {

    public UnitListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "units"));
    }

    @Override
    protected void fillComboBoxMapping() {

    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return UnitListController.class;
    }
}
