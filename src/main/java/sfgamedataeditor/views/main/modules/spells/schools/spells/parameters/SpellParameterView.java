package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;

public class SpellParameterView implements ControllableView {

    // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
    private JPanel mainPanel;

    private JLabel parametersLabel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.NUMBER, DTOColumnNames = "spellNumber", DTOClass = SpellParameters.class)
    private JPanel numberPanel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.NAME_ID, DTOColumnNames = "spellNameId", DTOClass = SpellParameters.class)
    private JPanel nameIdPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_LEVEL)
    private JPanel levelPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, DTOColumnNames = {"requirementClass1", "requirementSubClass1"}, DTOClass = SpellParameters.class)
    private JPanel requirementClassSubClassPanel1;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_1, DTOColumnNames = "requirementLevel1", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel1;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, DTOColumnNames = {"requirementClass2", "requirementSubClass2"}, DTOClass = SpellParameters.class)
    private JPanel requirementClassSubClassPanel2;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_2, DTOColumnNames = "requirementLevel2", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel2;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, DTOColumnNames = {"requirementClass3", "requirementSubClass3"}, DTOClass = SpellParameters.class)
    private JPanel requirementClassSubClassPanel3;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_3, DTOColumnNames = "requirementLevel3", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel3;

    @GUIElement(GUIElementId = GUIElements.MANA_USAGE, DTOColumnNames = "requirementSubClass1", DTOClass = SpellParameters.class)
    private JPanel manaActivationPanel;

    @GUIElement(GUIElementId = GUIElements.CAST_TYPE, DTOColumnNames = "castType", DTOClass = SpellParameters.class)
    private JPanel castTypePanel;

    @GUIElement(GUIElementId = GUIElements.CAST_TIME, DTOColumnNames = "castTime", DTOClass = SpellParameters.class)
    private JPanel castTimePanel;

    @GUIElement(GUIElementId = GUIElements.COOLDOWN, DTOColumnNames = "cooldown", DTOClass = SpellParameters.class)
    private JPanel cooldownPanel;

    @GUIElement(GUIElementId = GUIElements.MIN_RANGE, DTOColumnNames = "minRange", DTOClass = SpellParameters.class)
    private JPanel minRangePanel;

    @GUIElement(GUIElementId = GUIElements.MAX_RANGE, DTOColumnNames = "maxRange", DTOClass = SpellParameters.class)
    private JPanel maxRangePanel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_1, DTOColumnNames = "parameter1", DTOClass = SpellParameters.class)
    private JPanel parameter1Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_2, DTOColumnNames = "parameter2", DTOClass = SpellParameters.class)
    private JPanel parameter2Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_3, DTOColumnNames = "parameter3", DTOClass = SpellParameters.class)
    private JPanel parameter3Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_4, DTOColumnNames = "parameter4", DTOClass = SpellParameters.class)
    private JPanel parameter4Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_5, DTOColumnNames = "parameter5", DTOClass = SpellParameters.class)
    private JPanel parameter5Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_6, DTOColumnNames = "parameter6", DTOClass = SpellParameters.class)
    private JPanel parameter6Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_7, DTOColumnNames = "parameter7", DTOClass = SpellParameters.class)
    private JPanel parameter7Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_8, DTOColumnNames = "parameter8", DTOClass = SpellParameters.class)
    private JPanel parameter8Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_9, DTOColumnNames = "parameter9", DTOClass = SpellParameters.class)
    private JPanel parameter9Panel;
    private JPanel parametersPanel;

    public SpellParameterView() {
        parametersLabel.setText(I18N.INSTANCE.getMessage("spellParameters"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellParameterController.class;
    }
}
