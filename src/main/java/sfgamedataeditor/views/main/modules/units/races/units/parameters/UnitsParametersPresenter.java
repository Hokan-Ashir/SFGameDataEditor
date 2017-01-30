package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.unit.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitsParametersPresenter extends AbstractPresenter<UnitsParametersModelParameter, UnitsParametersView> {

    private static final Logger LOGGER = Logger.getLogger(UnitsParametersPresenter.class);
    private static final Map<Integer, Integer> SLOT_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> SPELL_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> BUILDINGS_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> RESOURCES_NUMBER_MAPPING = new HashMap<>();

    public UnitsParametersPresenter(UnitsParametersView view) {
        super(view);
        initializeSlotNumberMapping();
        initializeSpellNumberMapping();
        initializeResourcesNumberMapping();
        initializeBuildingsNumberMapping();
    }

    private void initializeBuildingsNumberMapping() {
        BUILDINGS_NUMBER_MAPPING.put(GUIElements.BUILDING1, 0);
        BUILDINGS_NUMBER_MAPPING.put(GUIElements.BUILDING2, 1);
        BUILDINGS_NUMBER_MAPPING.put(GUIElements.BUILDING3, 2);
    }

    private void initializeResourcesNumberMapping() {
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE1, 0);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE2, 1);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE3, 2);
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
                            updateWidgetWithParametersList(annotation.GUIElementId(), widget, creatureSpells, SPELL_NUMBER_MAPPING);
                        }
                    } else if (dtoClass.equals(CreatureBuildingsObject.class)) {
                        if (creatureBuildings == null || creatureBuildings.isEmpty()) {
                            widget.setVisible(false);
                        } else {
                            updateWidgetWithParametersList(annotation.GUIElementId(), widget, creatureBuildings, BUILDINGS_NUMBER_MAPPING);
                        }
                    } else if (dtoClass.equals(CreatureResourcesObject.class)) {
                        if (creatureResources == null || creatureResources.isEmpty()) {
                            widget.setVisible(false);
                        } else {
                            updateWidgetWithParametersList(annotation.GUIElementId(), widget, creatureResources, RESOURCES_NUMBER_MAPPING);
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void updateWidgetWithParametersList(int guiElementId, AbstractWidget widget,
                                                List<? extends OffsetableObject> objects,
                                                Map<Integer, Integer> guiElementsMapping) {
        Integer objectIndex = guiElementsMapping.get(guiElementId);
        if (objectIndex >= objects.size()) {
            widget.setVisible(false);
        } else {
            widget.setVisible(true);
            widget.getListener().updateWidgetValue(objects.get(objectIndex));
        }
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
