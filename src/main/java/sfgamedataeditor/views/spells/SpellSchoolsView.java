package sfgamedataeditor.views.spells;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.ModulesView;
import sfgamedataeditor.views.common.AbstractModulesView;

public class SpellSchoolsView extends AbstractModulesView<ModulesView> {

    public SpellSchoolsView(ModulesView parentView) {
        super(parentView);
        setModulesLabelText(I18N.INSTANCE.getMessage("spellSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        for (String s : Mappings.INSTANCE.SPELL_SCHOOL_MAP.keySet()) {
            getComboBoxMapping().put(s, SpellsView.class);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        getParentView().getSubModulesPanel().removeAll();
        getParentView().getSubModulesPanel().add(getMainPanel());
        getParentView().getSubModulesPanel().revalidate();
        getParentView().getSubModulesPanel().repaint();
    }
}
