package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.spellname.SpellNameObject;
import sfgamedataeditor.database.spellname.SpellNameTableService;
import sfgamedataeditor.database.spellparameters.SpellParametersObject;
import sfgamedataeditor.database.spellparameters.SpellParametersTableService;
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
    private final Map<Integer, List<String>> i18nStrings = new HashMap<>();
    private final Map<Integer, String> i18nDTOFieldsToGUIElementsIdsMap = new HashMap<Integer, String>() {{
        put(GUIElements.PARAMETER_1, "field1");
        put(GUIElements.PARAMETER_2, "field2");
        put(GUIElements.PARAMETER_3, "field3");
        put(GUIElements.PARAMETER_4, "field4");
        put(GUIElements.PARAMETER_5, "field5");
        put(GUIElements.PARAMETER_6, "field6");
        put(GUIElements.PARAMETER_7, "field7");
        put(GUIElements.PARAMETER_8, "field8");
        put(GUIElements.PARAMETER_9, "field9");
    }};

    public SpellParameterController(SpellParameterView view) {
        super(view);
        createI18NMap();
    }
    
    private void createI18NMap() {
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
        SpellParametersObject spellParameter = SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(selectedSpellId, selectedLevel);
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

                if (i18nDTOFieldsToGUIElementsIdsMap.containsKey(guiElementId)) {
                    List<String> strings = i18nStrings.get(guiElementId);
                    widget.updateI18N(strings);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void updateI18NWidgetsData(SpellParametersObject spellParametersObject) {
        Integer spellNameId = spellParametersObject.spellNameId;
        String spellName = bundle.getString(String.valueOf(spellNameId));
        SpellNameObject spellNameObject = SpellNameTableService.INSTANCE.getSpellName(I18N.INSTANCE.getMessage(spellName + SPELL_I18N_NAME_POSTFIX));
        for (Map.Entry<Integer, String> stringIntegerEntry : i18nDTOFieldsToGUIElementsIdsMap.entrySet()) {
            i18nStrings.get(stringIntegerEntry.getKey()).clear();
            try {
                Field field = spellNameObject.getClass().getDeclaredField(stringIntegerEntry.getValue());
                if (field == null) {
                    continue;
                }

                field.setAccessible(true);
                String parameterI18nValue = (String) field.get(spellNameObject);
                if (parameterI18nValue != null) {
                    i18nStrings.get(stringIntegerEntry.getKey()).add(parameterI18nValue);
                }
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
        mainView.unRenderViewInsideContentPanel(getView());
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
}
