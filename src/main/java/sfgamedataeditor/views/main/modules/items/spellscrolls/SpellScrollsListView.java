package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;

public class SpellScrollsListView extends AbstractModulesView {

    public SpellScrollsListView() {
        super(I18N.INSTANCE.getMessage("spellSchools"));
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
