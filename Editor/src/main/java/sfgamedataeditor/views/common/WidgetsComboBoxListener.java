package sfgamedataeditor.views.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class WidgetsComboBoxListener<T extends OffsetableObject, V extends PresentableView> implements ItemListener {

    private static final Logger LOGGER = Logger.getLogger(WidgetsComboBoxListener.class);

    private List<T> widgetObjects;
    private final V view;
    private final Class<T> objectClass;
    private final JComboBox<String> comboBox;

    public WidgetsComboBoxListener(V view, Class<T> objectClass, JComboBox<String> comboBox) {
        this.view = view;
        this.objectClass = objectClass;
        this.comboBox = comboBox;
    }

    public void setWidgetObjects(List<T> widgetObjects) {
        this.widgetObjects = widgetObjects;
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
                    int selectedIndex = comboBox.getSelectedIndex();
                    widget.getListener().updateWidgetValue(widgetObjects.get(selectedIndex));
                }

            } catch (IllegalAccessException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
