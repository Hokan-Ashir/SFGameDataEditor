package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class SpellParameterController extends AbstractController<SpellParameterModelParameter, SpellParameterView> {

    private static final Logger LOGGER = Logger.getLogger(SpellParameterController.class);

    public SpellParameterController(SpellParameterView view) {
        super(view);
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

                if (annotation.GUIElementId() != GUIElements.SPELL_LEVEL) {
                    widget.getListener().updateWidgetValue(spellParameter);
                } else {
                    LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, spellLevels);
                    widget.getListener().updateWidgetValue(levelComboBoxParameter);
                }

                widget.updateI18N();
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
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
