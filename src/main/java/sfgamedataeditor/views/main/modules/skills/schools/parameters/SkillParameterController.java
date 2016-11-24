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
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Set;

public class SkillParameterController extends AbstractController<SkillParameterModelParameter, SkillParameterView> {

    private static final Logger LOGGER = Logger.getLogger(SkillParameterController.class);

    public SkillParameterController(SkillParameterView view) {
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
//                Model<SkillParameterModelParameter> model = getModel();
//                model.getParameter().setSkillLevel(Integer.valueOf(selectedItem));
//                ShowContentViewEvent event = new ShowContentViewEvent(SkillParameterView.class, model);
//                EventProcessor.INSTANCE.process(event);
//            }
//        });
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

                if (annotation.GUIElementId() != GUIElements.SKILL_LEVEL) {
                    widget.getListener().updateWidgetValue(skillParameter);
                } else {
                    LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, getSkillPossibleLevels);
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
}
