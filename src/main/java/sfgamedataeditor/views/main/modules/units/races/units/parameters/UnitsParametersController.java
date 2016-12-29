package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.unit.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitsParametersController extends AbstractController<UnitsParametersModelParameter, UnitsParametersView> {

    private static final Logger LOGGER = Logger.getLogger(UnitsParametersController.class);
    private static final Map<Integer, Integer> SLOT_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> SPELL_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, String> RESOURCES_NAME_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> RESOURCES_NUMBER_MAPPING = new HashMap<>();

    public UnitsParametersController(UnitsParametersView view) {
        super(view);
        initializeSlotNumberMapping();
        initializeSpellNumberMapping();
        initializeResourcesNumberMapping();
        initializeResourcesNameNumberMapping();
    }

    private void initializeResourcesNumberMapping() {
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE1, 0);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE2, 1);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE3, 2);
    }

    private void initializeResourcesNameNumberMapping() {
        //    01 - wood
        //    02 - stone
        //    03 - log
        //    04 - moonsilver
        //    05 - food
        //    06 - berry
        //    07 - iron
        //    08 - tree
        //    09 - grain
        //    0B - fish
        //    0F - mushroom
        //    10 - meat
        //    12 - aria
        //    13 - lenya
        RESOURCES_NAME_NUMBER_MAPPING.put(1, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.wood"));
        RESOURCES_NAME_NUMBER_MAPPING.put(2, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.stone"));
        RESOURCES_NAME_NUMBER_MAPPING.put(3, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.log"));
        RESOURCES_NAME_NUMBER_MAPPING.put(4, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.moonsilver"));
        RESOURCES_NAME_NUMBER_MAPPING.put(5, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.food"));
        RESOURCES_NAME_NUMBER_MAPPING.put(6, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.berry"));
        RESOURCES_NAME_NUMBER_MAPPING.put(7, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.iron"));
        RESOURCES_NAME_NUMBER_MAPPING.put(8, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.tree"));
        RESOURCES_NAME_NUMBER_MAPPING.put(9, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.grain"));
        RESOURCES_NAME_NUMBER_MAPPING.put(11, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.fish"));
        RESOURCES_NAME_NUMBER_MAPPING.put(15, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.mushroom"));
        RESOURCES_NAME_NUMBER_MAPPING.put(16, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.meat"));
        RESOURCES_NAME_NUMBER_MAPPING.put(18, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.aria"));
        RESOURCES_NAME_NUMBER_MAPPING.put(19, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.lenya"));
    }

    private void initializeSpellNumberMapping() {
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL1, 0);
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL2, 1);
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL3, 2);
    }

    private void initializeSlotNumberMapping() {
        // see CreaturesEquipmentObject
        //    00 - helmet
        //    01 - right hand
        //    02 - chest
        //    03 - left hand
        //    04 - right ring
        //    05 - legs
        //    06 - left ring
        SLOT_NUMBER_MAPPING.put(GUIElements.HEAD_SLOT, 0);
        SLOT_NUMBER_MAPPING.put(GUIElements.RIGHT_HAND_SLOT, 1);
        SLOT_NUMBER_MAPPING.put(GUIElements.CHEST_SLOT, 2);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEFT_HAND_SLOT, 3);
        SLOT_NUMBER_MAPPING.put(GUIElements.RIGHT_RING_SLOT, 4);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEGS_SLOT, 5);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEFT_RING_SLOT, 6);
    }

    @Override
    public void updateView() {
        UnitsParametersModelParameter parameter = getModel().getParameter();
        CreaturesCommonParameterObject commonParameterObject = parameter.getCreatureCommonParameterObject();
        List<CreatureEquipmentObject> creatureEquipment = parameter.getCreatureEquipment();
        List<CreatureSpellObject> creatureSpells = parameter.getCreatureSpells();
        List<CreatureResourcesObject> creatureResources = parameter.getCreatureResources();
        List<CreatureBuildingsObject> creatureBuildings = parameter.getCreatureBuildings();

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

                Class<?> dtoClass = annotation.DTOClass();
                if (dtoClass.equals(CreaturesCommonParameterObject.class)) {
                    widget.getListener().updateWidgetValue(commonParameterObject);
                } else if (dtoClass.equals(CreatureEquipmentObject.class)) {
                    int elementId = annotation.GUIElementId();
                    Integer slotNumber = SLOT_NUMBER_MAPPING.get(elementId);
                    widget.setVisible(false);
                    for (CreatureEquipmentObject creatureEquipmentObject : creatureEquipment) {
                        if (creatureEquipmentObject.equipmentSlot.equals(slotNumber)) {
                            widget.setVisible(true);
                            widget.getListener().updateWidgetValue(creatureEquipmentObject);
                            break;
                        }
                    }
                } else {
                    JTabbedPane tabPane = getView().getTabPane();

                    if (dtoClass.equals(CreatureSpellObject.class)) {
                        if (creatureSpells == null || creatureSpells.isEmpty()) {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_SPELLS_TAB_INDEX, false);
                        } else {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_SPELLS_TAB_INDEX, true);
                            Integer spellIndex = SPELL_NUMBER_MAPPING.get(annotation.GUIElementId());
                            if (spellIndex >= creatureSpells.size()) {
                                widget.setVisible(false);
                            } else {
                                widget.setVisible(true);
                                widget.getListener().updateWidgetValue(creatureSpells.get(spellIndex));
                            }
                        }
                    } else if (dtoClass.equals(CreatureBuildingsObject.class)) {
                        if (creatureBuildings == null || creatureBuildings.isEmpty()) {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_BUILDINGS_TAB_INDEX, false);
                        } else {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_BUILDINGS_TAB_INDEX, true);
                        }
                    } else if (dtoClass.equals(CreatureResourcesObject.class)) {
                        if (creatureResources == null || creatureResources.isEmpty()) {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_RESOURCES_TAB_INDEX, false);
                        } else {
                            tabPane.setEnabledAt(UnitsParametersView.CREATURE_RESOURCES_TAB_INDEX, true);
                            Integer resourcesIndex = RESOURCES_NUMBER_MAPPING.get(annotation.GUIElementId());
                            if (resourcesIndex >= creatureResources.size()) {
                                widget.setVisible(false);
                            } else {
                                widget.setVisible(true);
                                CreatureResourcesObject resourcesObject = creatureResources.get(resourcesIndex);
                                widget.getListener().updateWidgetValue(resourcesObject);
                                String resourceName = RESOURCES_NAME_NUMBER_MAPPING.get(resourcesObject.resourceId);
                                widget.updateI18N(Collections.singletonList(resourceName));
                            }
                        }
                    }
                }

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
        mainView.unRenderViewInsideContentPanel(getView());
    }
}
