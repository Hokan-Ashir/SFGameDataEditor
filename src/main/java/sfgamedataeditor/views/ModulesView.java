package sfgamedataeditor.views;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.NotImplementedView;
import sfgamedataeditor.views.skills.SkillSchoolsView;
import sfgamedataeditor.views.spells.SpellSchoolsView;
import sfgamedataeditor.views.utility.ButtonsView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class ModulesView extends AbstractModulesView {

    private ButtonsView<ModulesView> viewButtonsView;

    public ModulesView(AbstractModulesView parentView) {
        super(parentView);
        getMainPanel().setLayout(new BoxLayout(getMainPanel(), BoxLayout.Y_AXIS));
        setModulesLabelText(I18N.getMessage("modulesList"));
        viewButtonsView = new ButtonsView<>(this);
        getMainPanel().add(viewButtonsView.getMainPanel());
        selectFirstComboBoxItem();
    }

    public static void showModulesView() {
        JFrame frame = new JFrame(I18N.getMessage("sfmodFilesCreationWindowCaption"));
        final ModulesView modulesView = new ModulesView(null);
        frame.setContentPane(modulesView.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
    }

    @Override
    public void show() {

    }

    @Override
    protected void fillComboBoxMapping() {
        getComboBoxMapping().put(I18N.getMessage("skills"), SkillSchoolsView.class);
        getComboBoxMapping().put(I18N.getMessage("spells"), SpellSchoolsView.class);
        getComboBoxMapping().put(I18N.getMessage("merchants"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.getMessage("buildings"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.getMessage("units"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.getMessage("items"), NotImplementedView.class);
    }
}
