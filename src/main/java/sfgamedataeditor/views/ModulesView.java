package sfgamedataeditor.views;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.buildings.BuildingRacesView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.NotImplementedView;
import sfgamedataeditor.views.items.ItemTypesView;
import sfgamedataeditor.views.merchants.MerchantLocationsView;
import sfgamedataeditor.views.skills.SkillSchoolsView;
import sfgamedataeditor.views.spells.SpellSchoolsView;
import sfgamedataeditor.views.units.UnitsRacesView;
import sfgamedataeditor.views.utility.ButtonsView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class ModulesView extends AbstractModulesView {

    public ModulesView(AbstractModulesView parentView) {
        super(parentView);
        getMainPanel().setLayout(new BoxLayout(getMainPanel(), BoxLayout.Y_AXIS));
        setModulesLabelText(I18N.INSTANCE.getMessage("modulesList"));
        ButtonsView<ModulesView> viewButtonsView = new ButtonsView<>(this);
        getMainPanel().add(viewButtonsView.getMainPanel());
        selectFirstComboBoxItem();
    }

    public static void showModulesView() {
        JFrame frame = new JFrame(I18N.INSTANCE.getMessage("sfmodFilesCreationWindowCaption"));
        final ModulesView modulesView = new ModulesView(null);
        frame.setContentPane(modulesView.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("skills"), SkillSchoolsView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("spells"), SpellSchoolsView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("merchants"), MerchantLocationsView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("buildings"), BuildingRacesView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("units"), UnitsRacesView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items"), ItemTypesView.class);
    }
}
