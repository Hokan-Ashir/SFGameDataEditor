package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.skill.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.skill.parameters.SkillParameterObject;
import sfgamedataeditor.database.skill.parameters.SkillParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;

import javax.swing.*;
import java.util.Set;

public class SkillParameterPresenter extends AbstractParametersPresenter<SkillParameterModelParameter, SkillParameterView> {

    private int selectedLevel;
    private Set<Integer> getSkillPossibleLevels;
    private SkillParameterObject skillParameter;

    public SkillParameterPresenter(SkillParameterView view) {
        super(view);
    }

    @Override
    public void updateView() {
        SkillParameterModelParameter parameter = getModel().getParameter();
        selectedLevel = parameter.getLevel();
        getSkillPossibleLevels = SkillParametersTableService.INSTANCE.getSkillPossibleLevels(parameter.getSkillSchoolId());
        skillParameter = SkillParametersTableService.INSTANCE.getSkillParameter(parameter.getSkillSchoolId(), selectedLevel);
        super.updateView();
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        int guiElementId = annotation.GUIElementId();
        if (guiElementId != GUIElements.SKILL_LEVEL) {
            widget.getListener().updateWidgetValue(skillParameter);
        } else {
            LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, getSkillPossibleLevels);
            widget.getListener().updateWidgetValue(levelComboBoxParameter);
        }
    }
}
