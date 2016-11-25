package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

public class SpellParameterController extends AbstractController<SpellParameterModelParameter, SpellParameterView> {

    private static final Logger LOGGER = Logger.getLogger(SpellParameterController.class);
    private static final String SPELL_NAME_MAPPING_FILE_NAME = "spellNameMapping";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(SPELL_NAME_MAPPING_FILE_NAME, Locale.getDefault());
    private static final String SPELL_I18N_NAME_POSTFIX = ".name";
    private Map<Integer, List<String>> i18nStrings = new HashMap<>();
    private Map<String, Integer> i18nDTOFieldsToGUIElementsIdsMap = new HashMap<String, Integer>() {{
        put("field1", GUIElements.PARAMETER_1);
        put("field2", GUIElements.PARAMETER_2);
        put("field3", GUIElements.PARAMETER_3);
        put("field4", GUIElements.PARAMETER_4);
        put("field5", GUIElements.PARAMETER_5);
        put("field6", GUIElements.PARAMETER_6);
        put("field7", GUIElements.PARAMETER_7);
        put("field8", GUIElements.PARAMETER_8);
        put("field9", GUIElements.PARAMETER_9);
    }};

    public SpellParameterController(SpellParameterView view) {
        super(view);
        createI18NMap();
//        getView().getLevelComboBox().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() != ItemEvent.SELECTED) {
//                    return;
//                }
//
//                String selectedItem = (String) getView().getLevelComboBox().getSelectedItem();
//                if (selectedItem == null) {
//                    return;
//                }
//
//                Model<SpellParameterModelParameter> model = getModel();
//                model.getParameter().setSpellLevel(Integer.valueOf(selectedItem));
//                ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
//                EventProcessor.INSTANCE.process(event);
//            }
//        });
    }
    
    private void createI18NMap() {
        i18nStrings.put(GUIElements.NUMBER, Collections.singletonList(I18N.INSTANCE.getMessage("spellNumber")));
        i18nStrings.put(GUIElements.NAME_ID, Collections.singletonList(I18N.INSTANCE.getMessage("spellType")));
        i18nStrings.put(GUIElements.SPELL_LEVEL, Collections.singletonList(I18N.INSTANCE.getMessage("levelLabel")));
        i18nStrings.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, Arrays.asList(
                I18N.INSTANCE.getMessage("spellRequirementClass"),
                I18N.INSTANCE.getMessage("spellRequirementSubClass")
        ));
        i18nStrings.put(GUIElements.REQUIREMENT_LEVEL_1, Collections.singletonList(I18N.INSTANCE.getMessage("spellRequirementLevel")));
        i18nStrings.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, Arrays.asList(
                I18N.INSTANCE.getMessage("spellRequirementClass"),
                I18N.INSTANCE.getMessage("spellRequirementSubClass")
        ));
        i18nStrings.put(GUIElements.REQUIREMENT_LEVEL_2, Collections.singletonList(I18N.INSTANCE.getMessage("spellRequirementLevel")));
        i18nStrings.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, Arrays.asList(
                I18N.INSTANCE.getMessage("spellRequirementClass"),
                I18N.INSTANCE.getMessage("spellRequirementSubClass")
        ));
        i18nStrings.put(GUIElements.REQUIREMENT_LEVEL_3, Collections.singletonList(I18N.INSTANCE.getMessage("spellRequirementLevel")));
        i18nStrings.put(GUIElements.MANA_USAGE, Collections.singletonList(I18N.INSTANCE.getMessage("spellManaUsage")));
        i18nStrings.put(GUIElements.CAST_TIME, Collections.singletonList(I18N.INSTANCE.getMessage("spellCastTime")));
        i18nStrings.put(GUIElements.COOLDOWN, Collections.singletonList(I18N.INSTANCE.getMessage("spellCooldown")));
        i18nStrings.put(GUIElements.MIN_RANGE, Collections.singletonList(I18N.INSTANCE.getMessage("spellMinRange")));
        i18nStrings.put(GUIElements.MAX_RANGE, Collections.singletonList(I18N.INSTANCE.getMessage("spellMaxRange")));
        i18nStrings.put(GUIElements.CAST_TYPE, Arrays.asList(
                I18N.INSTANCE.getMessage("spellCastType"),
                I18N.INSTANCE.getMessage("spellCastType.projectileToEnemy"),
                I18N.INSTANCE.getMessage("spellCastType.projectileToAllies"),
                I18N.INSTANCE.getMessage("spellCastType.targetArea"),
                I18N.INSTANCE.getMessage("spellCastType.worldInstantArea"),
                I18N.INSTANCE.getMessage("spellCastType.instantArea"),
                I18N.INSTANCE.getMessage("spellCastType.worldTargetArea"),
                I18N.INSTANCE.getMessage("spellCastType.alliesArea")
        ));
        i18nStrings.put(GUIElements.PARAMETER_1, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_2, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_3, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_4, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_5, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_6, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_7, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_8, new ArrayList<String>());
        i18nStrings.put(GUIElements.PARAMETER_9, new ArrayList<String>());
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        SpellParameterModelParameter parameter = getModel().getParameter();
        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(selectedSpellId);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        selectedLevel = adjustSelectedLevel(selectedLevel, spellMinLevel, spellMaxLevel);
        SpellParameters spellParameter = SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(selectedSpellId, selectedLevel);
        updateI18NWidgetsData(spellParameter);

        Field[] declaredFields = getView().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            GUIElement annotation = declaredField.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            try {
                declaredField.setAccessible(true);
                JPanel panel = (JPanel) declaredField.get(getView());
                AbstractWidget widget = (AbstractWidget) panel.getComponent(0);

                int guiElementId = annotation.GUIElementId();
                if (guiElementId != GUIElements.SPELL_LEVEL) {
                    widget.getListener().updateWidgetValue(spellParameter);
                } else {
                    LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, spellLevels);
                    widget.getListener().updateWidgetValue(levelComboBoxParameter);
                }

                List<String> strings = i18nStrings.get(guiElementId);
                widget.updateI18N(strings);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void updateI18NWidgetsData(SpellParameters spellParameters) {
        Integer spellNameId = spellParameters.spellNameId;
        String spellName = bundle.getString(String.valueOf(spellNameId));
        SpellName spellNameObject = SpellNameTableService.INSTANCE.getSpellName(I18N.INSTANCE.getMessage(spellName + SPELL_I18N_NAME_POSTFIX));
        for (Map.Entry<String, Integer> stringIntegerEntry : i18nDTOFieldsToGUIElementsIdsMap.entrySet()) {
            i18nStrings.get(stringIntegerEntry.getValue()).clear();
            try {
                Field field = spellNameObject.getClass().getDeclaredField(stringIntegerEntry.getKey());
                if (field == null) {
                    continue;
                }

                field.setAccessible(true);
                String parameterI18nValue = (String) field.get(spellNameObject);
                i18nStrings.get(stringIntegerEntry.getValue()).add(parameterI18nValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.info(e.getMessage(), e);
            }
        }
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unrenderViewInsideContentPanel(getView());
    }

    // in case user selected spell with level-range [1; 12] with level 5
    // then selected spell with level range [13; 20]
    // we have to select level 13 to appropriately load correct data
    // and vice versa, if user selected spell with level range [13; 20]
    // and then selected spell with level range [1; 12]
    private int adjustSelectedLevel(int selectedLevel, int spellMinLevel, int spellMaxLevel) {
        if (selectedLevel <= spellMaxLevel && selectedLevel >= spellMinLevel) {
            return selectedLevel;
        } else {
            return spellMinLevel;
        }
    }

//    private void setSpellParameterLabelNames() {
//        SpellsView spellsView = ViewRegister.INSTANCE.getView(SpellsView.class);
//        String selectedSpellName = spellsView.getSelectedModuleValue();
//        SpellName spellName = SpellNameTableService.INSTANCE.getSpellName(selectedSpellName);
//        for (Field field : getView().getClass().getDeclaredFields()) {
//            MappedColumn annotation = field.getAnnotation(MappedColumn.class);
//            if (annotation == null) {
//                continue;
//            }
//
//            if (!annotation.daoClass().equals(SpellName.class)) {
//                continue;
//            }
//
//            String mappedFieldName = annotation.name();
//            String parameterName = getParameterName(spellName, mappedFieldName);
//            try {
//                field.setAccessible(true);
//                JLabel label = ((JLabel) field.get(getView()));
//                if (parameterName.equals(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage("spellParameterNotUsed")))) {
//                    label.setVisible(false);
//                    label.getLabelFor().setVisible(false);
//                } else {
//                    label.setVisible(true);
//                    label.getLabelFor().setVisible(true);
//                    label.setText(parameterName);
//                }
//            } catch (IllegalAccessException e) {
//                LOGGER.error(e.getMessage());
//            }
//        }
//
//        getView().getMainPanel().repaint();
//    }

    private String getParameterName(SpellName spellName, String fieldName) {
        try {
            Field field = spellName.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(spellName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }
}
