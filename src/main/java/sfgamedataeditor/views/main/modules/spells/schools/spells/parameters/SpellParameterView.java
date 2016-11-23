package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class SpellParameterView implements ControllableView {

    // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
    private JPanel mainPanel;

    private JSeparator parametersSeparator;
    private JLabel parametersLabel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.NUMBER, DTOColumnName = "spellNumber", DTOClass = SpellParameters.class)
    private JPanel numberPanel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.NAME_ID, DTOColumnName = "spellNameId", DTOClass = SpellParameters.class)
    private JPanel nameIdPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_LEVEL)
    private JPanel levelPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_1, DTOColumnName = "requirementClass1", DTOClass = SpellParameters.class)
    private JPanel requirementClassPanel1;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_SUB_CLASS_1, DTOColumnName = "requirementSubClass1", DTOClass = SpellParameters.class)
    private JPanel requirementSubClassPanel1;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_1, DTOColumnName = "requirementLevel1", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel1;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_2, DTOColumnName = "requirementClass2", DTOClass = SpellParameters.class)
    private JPanel requirementClassPanel2;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_SUB_CLASS_2, DTOColumnName = "requirementSubClass2", DTOClass = SpellParameters.class)
    private JPanel requirementSubClassPanel2;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_2, DTOColumnName = "requirementLevel2", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel2;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_3, DTOColumnName = "requirementClass3", DTOClass = SpellParameters.class)
    private JPanel requirementClassPanel3;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_SUB_CLASS_3, DTOColumnName = "requirementSubClass3", DTOClass = SpellParameters.class)
    private JPanel requirementSubClassPanel3;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL_3, DTOColumnName = "requirementLevel3", DTOClass = SpellParameters.class)
    private JPanel requirementLevelPanel3;

    @GUIElement(GUIElementId = GUIElements.MANA_USAGE, DTOColumnName = "requirementSubClass1", DTOClass = SpellParameters.class)
    private JPanel manaActivationPanel;

    @GUIElement(GUIElementId = GUIElements.CAST_TYPE, DTOColumnName = "castType", DTOClass = SpellParameters.class)
    private JPanel castTypePanel;

    @GUIElement(GUIElementId = GUIElements.CAST_TIME, DTOColumnName = "castTime", DTOClass = SpellParameters.class)
    private JPanel castTimePanel;

    @GUIElement(GUIElementId = GUIElements.COOLDOWN, DTOColumnName = "cooldown", DTOClass = SpellParameters.class)
    private JPanel cooldownPanel;

    @GUIElement(GUIElementId = GUIElements.MIN_RANGE, DTOColumnName = "minRange", DTOClass = SpellParameters.class)
    private JPanel minRangePanel;

    @GUIElement(GUIElementId = GUIElements.MAX_RANGE, DTOColumnName = "maxRange", DTOClass = SpellParameters.class)
    private JPanel maxRangePanel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_1, DTOColumnName = "parameter1", DTOClass = SpellParameters.class)
    private JPanel parameter1Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_2, DTOColumnName = "parameter2", DTOClass = SpellParameters.class)
    private JPanel parameter2Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_3, DTOColumnName = "parameter3", DTOClass = SpellParameters.class)
    private JPanel parameter3Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_4, DTOColumnName = "parameter4", DTOClass = SpellParameters.class)
    private JPanel parameter4Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_5, DTOColumnName = "parameter5", DTOClass = SpellParameters.class)
    private JPanel parameter5Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_6, DTOColumnName = "parameter6", DTOClass = SpellParameters.class)
    private JPanel parameter6Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_7, DTOColumnName = "parameter7", DTOClass = SpellParameters.class)
    private JPanel parameter7Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_8, DTOColumnName = "parameter8", DTOClass = SpellParameters.class)
    private JPanel parameter8Panel;

    @GUIElement(GUIElementId = GUIElements.PARAMETER_9, DTOColumnName = "parameter9", DTOClass = SpellParameters.class)
    private JPanel parameter9Panel;

    public SpellParameterView() {
        parametersLabel.setText(I18N.INSTANCE.getMessage("spellParameters"));
        setCommonLabelsI18nNames();
//        initializeRequirementsComboBoxes();
    }

    private void setCommonLabelsI18nNames() {
//        setLabelText(numberLabel, "spellNumber");
//        setLabelText(typeLabel, "spellType");
//        setLabelText(requirementClassLabel, "spellRequirementClass");
//        setLabelText(requirementSubClassLabel, "spellRequirementSubClass");
//        setLabelText(requirementLevelLabel, "spellRequirementLevel");
//        setLabelText(requirementClassLabel2, "spellRequirementClass");
//        setLabelText(requirementSubClassLabel2, "spellRequirementSubClass");
//        setLabelText(requirementLevelLabel2, "spellRequirementLevel");
//        setLabelText(requirementClassLabel3, "spellRequirementClass");
//        setLabelText(requirementSubClassLabel3, "spellRequirementSubClass");
//        setLabelText(requirementLevelLabel3, "spellRequirementLevel");
//        setLabelText(manaUsageLabel, "spellManaUsage");
//        setLabelText(castTimeLabel, "spellCastTime");
//        setLabelText(cooldownLabel, "spellCooldown");
//        setLabelText(minRangeLabel, "spellMinRange");
//        setLabelText(maxRangeLabel, "spellMaxRange");
//        setLabelText(castTypeLabel, "spellCastType");
//        setLabelText(levelLabel, "levelLabel");
    }

    private void setLabelText(JLabel label, String not18nText) {
        label.setText(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage(not18nText)));
    }

//    private void initializeRequirementsComboBoxes() {
//        Map<String, List<String>> map = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP;
//        for (String s : map.keySet()) {
//            requirementClassComboBox.addItem(s);
//            requirementClassComboBox2.addItem(s);
//            requirementClassComboBox3.addItem(s);
//        }
//
//        attachSubClassListenerToClassComboBox(requirementClassComboBox, requirementSubClassComboBox);
//        attachSubClassListenerToClassComboBox(requirementClassComboBox2, requirementSubClassComboBox2);
//        attachSubClassListenerToClassComboBox(requirementClassComboBox3, requirementSubClassComboBox3);
//    }
//
//    private void attachSubClassListenerToClassComboBox(JComboBox classComboBox, JComboBox subClassComboBox) {
//        ClassRequirementComboBoxListener listener = new ClassRequirementComboBoxListener(subClassComboBox);
//        classComboBox.addItemListener(listener);
//        classComboBox.setSelectedItem(null);
//    }

    public JComboBox<String> getLevelComboBox() {
        return null;
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
