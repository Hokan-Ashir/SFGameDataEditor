package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.workerrunes.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillObject;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkersRunesParameterPresenter extends AbstractParametersPresenter<WorkersRunesParametersModelParameter, WorkersRunesParametersView> {

    private static final Map<Integer, Integer> SKILL_NUMBER_MAPPING = new HashMap<>();

    public WorkersRunesParameterPresenter(WorkersRunesParametersView view) {
        super(view);
        initializeSkillNumberMapping();
    }

    private void initializeSkillNumberMapping() {
        SKILL_NUMBER_MAPPING.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, 0);
        SKILL_NUMBER_MAPPING.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, 1);
        SKILL_NUMBER_MAPPING.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, 2);
        SKILL_NUMBER_MAPPING.put(GUIElements.REQUIREMENT_CLASS_SUBCLASS_4, 3);
        SKILL_NUMBER_MAPPING.put(GUIElements.SKILL_LEVEL_1, 0);
        SKILL_NUMBER_MAPPING.put(GUIElements.SKILL_LEVEL_2, 1);
        SKILL_NUMBER_MAPPING.put(GUIElements.SKILL_LEVEL_3, 2);
        SKILL_NUMBER_MAPPING.put(GUIElements.SKILL_LEVEL_4, 3);
    }

    @Override
    public void updateView() {
        super.updateView();
        ViewTools.setFirstActiveTab(getView().getTabPane());
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        WorkersRunesParametersModelParameter parameter = getModel().getParameter();
        Integer runeLevel = parameter.getLevel();
        Integer itemId = parameter.getRuneLevelToItemIdMap().get(runeLevel);

        ItemPriceParametersObject priceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureObjectByStatsId(priceParametersObject.unitStatsId);
        List<CreatureSkillObject> creatureSkills = CreatureSkillTableService.INSTANCE.getCreatureSkillsByStatsId(priceParametersObject.unitStatsId);
        Set<Integer> runesLevels = parameter.getRuneLevelToItemIdMap().keySet();

        int guiElementId = annotation.GUIElementId();
        if (guiElementId == GUIElements.LEVEL) {
            LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(runeLevel, runesLevels);
            widget.getListener().updateWidgetValue(levelComboBoxParameter);
        } else {
            Class<?> dtoClass = annotation.DTOClass();
            if (dtoClass.equals(ItemPriceParametersObject.class)) {
                widget.getListener().updateWidgetValue(priceParametersObject);
            } else if (dtoClass.equals(CreatureParameterObject.class)) {
                widget.getListener().updateWidgetValue(creatureParameterObject);
            } else if (dtoClass.equals(CreatureSkillObject.class)) {
                if (creatureSkills == null || creatureSkills.isEmpty()) {
                    getView().getTabPane().setEnabledAt(WorkersRunesParametersView.SKILL_PARAMETERS_TAB_INDEX, false);
                } else {
                    getView().getTabPane().setEnabledAt(WorkersRunesParametersView.SKILL_PARAMETERS_TAB_INDEX, true);
                    Integer skillIndex = SKILL_NUMBER_MAPPING.get(annotation.GUIElementId());
                    if (skillIndex >= creatureSkills.size()) {
                        widget.setVisible(false);
                    } else {
                        widget.setVisible(true);
                        widget.getListener().updateWidgetValue(creatureSkills.get(skillIndex));
                    }
                }
            }
        }
    }
}
