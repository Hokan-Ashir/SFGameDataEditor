package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellsView extends AbstractModulesView {

    public SpellsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellController.class;
    }
}
