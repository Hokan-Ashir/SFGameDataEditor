package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.item.workerrunes.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillObject;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

public class WorkersRunesParameterPresenter extends AbstractPresenter<WorkersRunesParametersModelParameter, WorkersRunesParametersView> {

    private static final Logger LOGGER = Logger.getLogger(WorkersRunesParameterPresenter.class);
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
        WorkersRunesParametersModelParameter parameter = getModel().getParameter();
        String runeName = parameter.getRuneName();
        Integer runeLevel = parameter.getLevel();
        String fullRuneName = runeName + " - level " + String.valueOf(runeLevel);
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(fullRuneName);
        ItemPriceParametersObject priceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureObjectByStatsId(priceParametersObject.unitStatsId);
        List<CreatureSkillObject> creatureSkills = CreatureSkillTableService.INSTANCE.getCreatureSkillsByStatsId(priceParametersObject.unitStatsId);
        Set<Integer> runesLevels = getRunesLevels(runeName);
        Icon icon = parameter.getIcon();

        Field[] declaredFields = getView().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                IconElement iconElement = declaredField.getAnnotation(IconElement.class);
                if (iconElement != null) {
                    declaredField.setAccessible(true);
                    JLabel panel = (JLabel) declaredField.get(getView());
                    panel.setIcon(icon);
                    continue;
                }

                GUIElement annotation = declaredField.getAnnotation(GUIElement.class);
                if (annotation == null) {
                    continue;
                }

                int guiElementId = annotation.GUIElementId();
                declaredField.setAccessible(true);
                JPanel panel = (JPanel) declaredField.get(getView());
                AbstractWidget widget = (AbstractWidget) panel.getComponent(0);

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
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private Set<Integer> getRunesLevels(String runeBaseName) {
        Set<Integer> runesLevels = new TreeSet<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        String prefix = runeBaseName + " - level ";
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (value.startsWith(prefix)) {
                runesLevels.add(Integer.valueOf(value.split(" - ")[1].replace("level ", "")));
            }
        }

        return runesLevels;
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView().getMainPanel());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unRenderViewInsideContentPanel(getView().getMainPanel());
    }
}
