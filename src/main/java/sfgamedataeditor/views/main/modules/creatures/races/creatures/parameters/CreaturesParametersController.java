package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaturesParametersController extends AbstractController<CreaturesParametersModelParameter, CreaturesParametersView> {

    private static final Logger LOGGER = Logger.getLogger(CreaturesParametersController.class);
    private static final Map<Integer, Integer> SLOT_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> SPELL_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> CORPSE_LOOT_NUMBER_MAPPING = new HashMap<>();

    public CreaturesParametersController(CreaturesParametersView view) {
        super(view);
        initializeSlotNumberMapping();
        initializeSpellNumberMapping();
        initializeCorpseLootNumberMapping();
    }

    private void initializeCorpseLootNumberMapping() {
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_1_1, 0);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_1_1, 0);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_1_2, 0);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_1_2, 0);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_1_3, 0);

        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_2_1, 1);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_2_1, 1);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_2_2, 1);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_2_2, 1);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_2_3, 1);

        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_3_1, 2);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_3_1, 2);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_POSSIBILITY_3_2, 2);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_3_2, 2);
        CORPSE_LOOT_NUMBER_MAPPING.put(GUIElements.DROP_ITEM_3_3, 2);
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
        CreaturesParametersModelParameter parameter = getModel().getParameter();
        CreatureParameterObject creatureParameterObject = parameter.getCreatureParameterObject();
        CreaturesCommonParameterObject commonParameterObject = parameter.getCreatureCommonParameterObject();
        List<CreatureEquipmentObject> creatureEquipment = parameter.getCreatureEquipment();
        List<CreatureSpellObject> creatureSpells = parameter.getCreatureSpells();
        List<CreatureCorpseLootObject> corpseLootObjects = parameter.getCorpseLootObjects();

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
                if (dtoClass.equals(CreatureParameterObject.class)) {
                    widget.getListener().updateWidgetValue(creatureParameterObject);
                } else if (dtoClass.equals(CreaturesCommonParameterObject.class)) {
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
                } else if (dtoClass.equals(CreatureSpellObject.class)) {
                    if (creatureSpells == null || creatureSpells.isEmpty()) {
                        getView().getTabPane().setEnabledAt(CreaturesParametersView.SPELLS_TAB_INDEX, false);
                    } else {
                        getView().getTabPane().setEnabledAt(CreaturesParametersView.SPELLS_TAB_INDEX, true);
                        Integer spellIndex = SPELL_NUMBER_MAPPING.get(annotation.GUIElementId());
                        if (spellIndex >= creatureSpells.size()) {
                            widget.setVisible(false);
                        } else {
                            widget.setVisible(true);
                            widget.getListener().updateWidgetValue(creatureSpells.get(spellIndex));
                        }
                    }
                } else if (dtoClass.equals(CreatureCorpseLootObject.class)) {
                    if (corpseLootObjects == null || corpseLootObjects.isEmpty()) {
                        getView().getTabPane().setEnabledAt(CreaturesParametersView.CORPSE_LOOT_TAB_INDEX, false);
                    } else {
                        getView().getTabPane().setEnabledAt(CreaturesParametersView.CORPSE_LOOT_TAB_INDEX, true);
                        Integer corpseItemIndex = CORPSE_LOOT_NUMBER_MAPPING.get(annotation.GUIElementId());
                        if (corpseItemIndex >= corpseLootObjects.size()) {
                            widget.setVisible(false);
                        } else {
                            widget.setVisible(true);
                            widget.getListener().updateWidgetValue(corpseLootObjects.get(corpseItemIndex));
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
