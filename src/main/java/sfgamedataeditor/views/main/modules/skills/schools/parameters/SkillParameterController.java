package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.skillparameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.database.tableservices.SkillParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

public class SkillParameterController extends AbstractController<SkillParameterModelParameter, SkillParameterView> {

    private static final Logger LOGGER = Logger.getLogger(SkillParameterController.class);
    private Map<Integer, List<String>> i18nStrings = new HashMap<>();

    public SkillParameterController(SkillParameterView view) {
        super(view);
        crateI18NMap();

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
//                Model<SkillParameterModelParameter> model = getModel();
//                model.getParameter().setSkillLevel(Integer.valueOf(selectedItem));
//                ShowContentViewEvent event = new ShowContentViewEvent(SkillParameterView.class, model);
//                EventProcessor.INSTANCE.process(event);
//            }
//        });
    }

    private void crateI18NMap() {
        i18nStrings.put(GUIElements.STRENGTH, Collections.singletonList(I18N.INSTANCE.getMessage("strength")));
        i18nStrings.put(GUIElements.STAMINA, Collections.singletonList(I18N.INSTANCE.getMessage("stamina")));
        i18nStrings.put(GUIElements.AGILITY, Collections.singletonList(I18N.INSTANCE.getMessage("agility")));
        i18nStrings.put(GUIElements.DEXTERITY, Collections.singletonList(I18N.INSTANCE.getMessage("dexterity")));
        i18nStrings.put(GUIElements.CHARISMA, Collections.singletonList(I18N.INSTANCE.getMessage("charisma")));
        i18nStrings.put(GUIElements.INTELLIGENCE, Collections.singletonList(I18N.INSTANCE.getMessage("intelligence")));
        i18nStrings.put(GUIElements.WISDOM, Collections.singletonList(I18N.INSTANCE.getMessage("wisdom")));
        i18nStrings.put(GUIElements.SKILL_LEVEL, Collections.singletonList(I18N.INSTANCE.getMessage("levelLabel")));
    }

    @Override
    public void updateView() {
        SkillParameterModelParameter parameter = getModel().getParameter();
        int selectedLevel = parameter.getSkillLevel();
        Set<Integer> getSkillPossibleLevels = SkillParametersTableService.INSTANCE.getSkillPossibleLevels(parameter.getSkillSchoolId());
        SkillParameters skillParameter = SkillParametersTableService.INSTANCE.getSkillParameter(parameter.getSkillSchoolId(), selectedLevel);

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
                if (guiElementId != GUIElements.SKILL_LEVEL) {
                    widget.getListener().updateWidgetValue(skillParameter);
                } else {
                    LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, getSkillPossibleLevels);
                    widget.getListener().updateWidgetValue(levelComboBoxParameter);
                }

                List<String> strings = i18nStrings.get(guiElementId);
                widget.updateI18N(strings);
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
}
