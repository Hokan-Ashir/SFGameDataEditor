package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellScrollsListView extends AbstractModulesView {

    public SpellScrollsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spellSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellScrollsController.class;
    }
}
