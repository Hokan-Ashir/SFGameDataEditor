package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class ArmorParametersPresenter extends AbstractPresenter<ArmorParametersModelParameter, ArmorParametersView> {

    private static final Logger LOGGER = Logger.getLogger(ArmorParametersPresenter.class);

    private final ArmorRequirementsComboBoxListener armorRequirementsListener = new ArmorRequirementsComboBoxListener();

    public ArmorParametersPresenter(ArmorParametersView view) {
        super(view);
        getView().getRequirementsComboBox().addItemListener(armorRequirementsListener);
    }

    @Override
    public void updateView() {
        ArmorParametersModelParameter parameter = getModel().getParameter();
        ArmorParametersObject armorParametersObject = parameter.getArmorParametersObject();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        List<ItemRequirementsObject> requirementsObjects = parameter.getRequirementsObjects();

        updateItemRequirementsWidgets(requirementsObjects);

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
                if (dtoClass.equals(ItemPriceParametersObject.class)) {
                    widget.getListener().updateWidgetValue(priceParametersObject);
                } else if (dtoClass.equals(ArmorParametersObject.class)) {
                    widget.getListener().updateWidgetValue(armorParametersObject);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void updateItemRequirementsWidgets(final List<ItemRequirementsObject> requirementsObjects) {
        final JComboBox<String> comboBox = getView().getRequirementsComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "requirement.object");
                for (int i = 0; i < requirementsObjects.size(); i++) {
                    comboBox.addItem(dropItem + " - " + (i + 1));
                }
                comboBox.setSelectedItem(null);
            }
        });

        armorRequirementsListener.setItemRequirementsObjects(requirementsObjects);
        comboBox.setSelectedItem(comboBox.getItemAt(0));
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

    private final class ArmorRequirementsComboBoxListener implements ItemListener {

        private List<ItemRequirementsObject> itemRequirementsObjects;

        public void setItemRequirementsObjects(List<ItemRequirementsObject> itemRequirementsObjects) {
            this.itemRequirementsObjects = itemRequirementsObjects;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }

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
                    if (dtoClass.equals(ItemRequirementsObject.class)) {
                        int selectedIndex = getView().getRequirementsComboBox().getSelectedIndex();
                        widget.getListener().updateWidgetValue(itemRequirementsObjects.get(selectedIndex));
                    }

                } catch (IllegalAccessException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
        }
    }
}
