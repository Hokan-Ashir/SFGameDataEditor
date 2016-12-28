package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
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

    public CreaturesParametersController(CreaturesParametersView view) {
        super(view);
        initializeSlotNumberMapping();
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
        CreatureParameterObject creatureParameterObject = getModel().getParameter().getCreatureParameterObject();
        List<CreatureEquipmentObject> creatureEquipment = getModel().getParameter().getCreatureEquipment();

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

                if (annotation.DTOClass().equals(CreatureParameterObject.class)) {
                    widget.getListener().updateWidgetValue(creatureParameterObject);
                } else if (annotation.DTOClass().equals(CreatureEquipmentObject.class)) {
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
