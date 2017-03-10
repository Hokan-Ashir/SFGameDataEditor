package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.views.common.AbstractParametersPresenter;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class WeaponParametersPresenter extends AbstractParametersPresenter<WeaponParametersModelParameter, WeaponParametersView> {

    private static final Logger LOGGER = Logger.getLogger(WeaponParametersPresenter.class);
    private final ItemRequirementsComboBoxListener itemRequirementsListener = new ItemRequirementsComboBoxListener();
    private final ItemEffectsComboBoxListener itemEffectsListener = new ItemEffectsComboBoxListener();

    public WeaponParametersPresenter(WeaponParametersView view) {
        super(view);
        getView().getItemRequirementsComboBox().addItemListener(itemRequirementsListener);
        getView().getEffectsComboBox().addItemListener(itemEffectsListener);
    }

    @Override
    public void updateView() {
        WeaponParametersModelParameter parameter = getModel().getParameter();
        List<ItemSpellEffectsObject> itemSpellEffectsObjects = parameter.getItemSpellEffectsObjects();
        final List<ItemRequirementsObject> requirementsObjects = parameter.getRequirementsObjects();

        updateItemRequirementsWidgets(requirementsObjects);
        updateWeaponEffectWidgets(itemSpellEffectsObjects);

        super.updateView();
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        WeaponParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        WeaponParametersObject weaponParametersObject = parameter.getWeaponParametersObject();

        Class<?> dtoClass = annotation.DTOClass();
        if (dtoClass.equals(ItemPriceParametersObject.class)) {
            widget.getListener().updateWidgetValue(priceParametersObject);
        } else if (dtoClass.equals(WeaponParametersObject.class)) {
            if (weaponParametersObject == null) {
                panel.setVisible(false);
            } else {
                panel.setVisible(true);
                widget.getListener().updateWidgetValue(weaponParametersObject);
            }
        }
    }

    private void updateWeaponEffectWidgets(final List<ItemSpellEffectsObject> itemSpellEffectsObjects) {
        if (itemSpellEffectsObjects == null || itemSpellEffectsObjects.isEmpty()) {
            getView().getItemEffectPanel().setVisible(false);
            getView().getEffectsComboBox().setVisible(false);
        } else {
            getView().getItemEffectPanel().setVisible(true);
            getView().getEffectsComboBox().setVisible(true);

            final JComboBox<String> effectsComboBox = getView().getEffectsComboBox();
            ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(effectsComboBox) {
                @Override
                protected void setValues() {
                    effectsComboBox.removeAllItems();
                    String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "weapon.effect");
                    for (int i = 0; i < itemSpellEffectsObjects.size(); i++) {
                        effectsComboBox.addItem(dropItem + " - " + (i + 1));
                    }
                    effectsComboBox.setSelectedItem(null);
                }
            });

            itemEffectsListener.setItemEffectsObjects(itemSpellEffectsObjects);
            effectsComboBox.setSelectedItem(effectsComboBox.getItemAt(0));
        }
    }

    private void updateItemRequirementsWidgets(final List<ItemRequirementsObject> requirementsObjects) {
        final JComboBox<String> itemRequirementsComboBox = getView().getItemRequirementsComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(itemRequirementsComboBox) {
            @Override
            protected void setValues() {
                itemRequirementsComboBox.removeAllItems();
                String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "requirement.object");
                for (int i = 0; i < requirementsObjects.size(); i++) {
                    itemRequirementsComboBox.addItem(dropItem + " - " + (i + 1));
                }
                itemRequirementsComboBox.setSelectedItem(null);
            }
        });

        itemRequirementsListener.setItemRequirementsObjects(requirementsObjects);
        itemRequirementsComboBox.setSelectedItem(itemRequirementsComboBox.getItemAt(0));
    }

    // TODO get rid of duplications (copy-&-paste from ArmorParametersPresenter)
    private final class ItemRequirementsComboBoxListener implements ItemListener {

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
                        int selectedIndex = getView().getItemRequirementsComboBox().getSelectedIndex();
                        widget.getListener().updateWidgetValue(itemRequirementsObjects.get(selectedIndex));
                    }

                } catch (IllegalAccessException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
        }
    }

    private final class ItemEffectsComboBoxListener implements ItemListener {

        private List<ItemSpellEffectsObject> itemEffectsObjects;

        public void setItemEffectsObjects(List<ItemSpellEffectsObject> itemEffectsObjects) {
            this.itemEffectsObjects = itemEffectsObjects;
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
                    if (dtoClass.equals(ItemSpellEffectsObject.class)) {
                        int selectedIndex = getView().getEffectsComboBox().getSelectedIndex();
                        widget.getListener().updateWidgetValue(itemEffectsObjects.get(selectedIndex));
                    }

                } catch (IllegalAccessException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
        }
    }
}
