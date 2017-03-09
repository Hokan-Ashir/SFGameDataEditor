package sfgamedataeditor.views.common.dropitems;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.common.OffsetableObject;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseDropItemsComboBoxListener<T extends OffsetableObject, V extends DropItemsListenerableView> implements ItemListener {

    private static final Logger LOGGER = Logger.getLogger(BaseDropItemsComboBoxListener.class);

    private List<T> corpseLootObjects;
    private final V view;
    private final Class<T> objectClass;

    public BaseDropItemsComboBoxListener(V view, Class<T> objectClass) {
        this.view = view;
        this.objectClass = objectClass;
    }

    public void setDroppingObjects(List<T> corpseLootObjects) {
        this.corpseLootObjects = corpseLootObjects;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        Field[] declaredFields = view.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            GUIElement annotation = declaredField.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            try {
                declaredField.setAccessible(true);
                JPanel panel = (JPanel) declaredField.get(view);
                AbstractWidget widget = (AbstractWidget) panel.getComponent(0);

                Class<?> dtoClass = annotation.DTOClass();
                if (dtoClass.equals(objectClass)) {
                    int selectedIndex = view.getDropItemsComboBox().getSelectedIndex();
                    widget.getListener().updateWidgetValue(corpseLootObjects.get(selectedIndex));
                }

            } catch (IllegalAccessException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
