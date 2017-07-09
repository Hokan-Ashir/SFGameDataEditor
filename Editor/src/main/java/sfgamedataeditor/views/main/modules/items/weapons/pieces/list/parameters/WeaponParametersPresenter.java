package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.views.common.WidgetsComboBoxListener;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class WeaponParametersPresenter extends AbstractParametersPresenter<WeaponParametersModelParameter, WeaponParametersView> {

    private final WidgetsComboBoxListener<ItemRequirementsObject, WeaponParametersView> itemRequirementsListener;
    private final WidgetsComboBoxListener<ItemSpellEffectsObject, WeaponParametersView> itemEffectsListener;

    private ItemPriceParametersObject priceParametersObject;
    private WeaponParametersObject weaponParametersObject;
    private ArmorParametersObject armorParametersObject;

    public WeaponParametersPresenter(WeaponParametersView view) {
        super(view);
        itemRequirementsListener = new WidgetsComboBoxListener<>(getView(), ItemRequirementsObject.class, getView().getItemRequirementsComboBox());
        itemEffectsListener = new WidgetsComboBoxListener<>(getView(), ItemSpellEffectsObject.class, getView().getEffectsComboBox());
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

        priceParametersObject = parameter.getPriceParametersObject();
        weaponParametersObject = parameter.getWeaponParametersObject();
        armorParametersObject = parameter.getArmorParametersObject();

        super.updateView();
        ViewTools.setFirstActiveTab(getView().getTabPane());
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        Class<?> dtoClass = annotation.DTOClass();
        if (dtoClass.equals(ItemPriceParametersObject.class)) {
            widget.getListener().updateWidgetValue(priceParametersObject);
        } else if (dtoClass.equals(WeaponParametersObject.class)) {
            if (weaponParametersObject == null) {
                getView().getTabPane().setEnabledAt(WeaponParametersView.WEAPON_PARAMETERS_TAB_INDEX, false);
            } else {
                getView().getTabPane().setEnabledAt(WeaponParametersView.WEAPON_PARAMETERS_TAB_INDEX, true);
                widget.getListener().updateWidgetValue(weaponParametersObject);
            }
        } else if (dtoClass.equals(ArmorParametersObject.class)) {
            if (armorParametersObject == null) {
                getView().getTabPane().setEnabledAt(WeaponParametersView.ARMOR_PARAMETERS_TAB_INDEX, false);
            } else {
                getView().getTabPane().setEnabledAt(WeaponParametersView.ARMOR_PARAMETERS_TAB_INDEX, true);
                widget.getListener().updateWidgetValue(armorParametersObject);
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

            final JComboBox<String> comboBox = getView().getEffectsComboBox();
            ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
                @Override
                protected void setValues() {
                    comboBox.removeAllItems();
                    String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "weapon.effect");
                    for (int i = 0; i < itemSpellEffectsObjects.size(); i++) {
                        comboBox.addItem(dropItem + " - " + (i + 1));
                    }
                    comboBox.setSelectedItem(null);
                }
            });

            itemEffectsListener.setWidgetObjects(itemSpellEffectsObjects);
            comboBox.setSelectedItem(comboBox.getItemAt(0));
        }
    }

    private void updateItemRequirementsWidgets(final List<ItemRequirementsObject> requirementsObjects) {
        final JComboBox<String> comboBox = getView().getItemRequirementsComboBox();
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

        itemRequirementsListener.setWidgetObjects(requirementsObjects);
        comboBox.setSelectedItem(comboBox.getItemAt(0));
    }
}
