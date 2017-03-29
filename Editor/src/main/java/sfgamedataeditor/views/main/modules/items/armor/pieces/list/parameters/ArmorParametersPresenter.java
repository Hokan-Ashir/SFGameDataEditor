package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.views.common.AbstractParametersPresenter;
import sfgamedataeditor.views.common.WidgetsComboBoxListener;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class ArmorParametersPresenter extends AbstractParametersPresenter<ArmorParametersModelParameter, ArmorParametersView> {

    private static final Logger LOGGER = Logger.getLogger(ArmorParametersPresenter.class);

    private final WidgetsComboBoxListener<ItemRequirementsObject, ArmorParametersView> armorRequirementsListener;

    public ArmorParametersPresenter(ArmorParametersView view) {
        super(view);
        armorRequirementsListener = new WidgetsComboBoxListener<>(getView(), ItemRequirementsObject.class, getView().getRequirementsComboBox());
        getView().getRequirementsComboBox().addItemListener(armorRequirementsListener);
    }

    @Override
    public void updateView() {
        ArmorParametersModelParameter parameter = getModel().getParameter();
        List<ItemRequirementsObject> requirementsObjects = parameter.getRequirementsObjects();
        updateItemRequirementsWidgets(requirementsObjects);

        super.updateView();
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        ArmorParametersModelParameter parameter = getModel().getParameter();
        ArmorParametersObject armorParametersObject = parameter.getArmorParametersObject();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();

        Class<?> dtoClass = annotation.DTOClass();
        if (dtoClass.equals(ItemPriceParametersObject.class)) {
            widget.getListener().updateWidgetValue(priceParametersObject);
        } else if (dtoClass.equals(ArmorParametersObject.class)) {
            if (armorParametersObject == null) {
                panel.setVisible(false);
            } else {
                panel.setVisible(true);
                try {
                    // TODO get rid of exception catching (in case of JLabels marked as GUIComponents or
                    // JPanels that contains many many other JPanels and do not contains widgets)
                    widget.getListener().updateWidgetValue(armorParametersObject);
                } catch (ClassCastException e) {
                    LOGGER.info(e.getMessage(), e);
                }
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

        armorRequirementsListener.setWidgetObjects(requirementsObjects);
        comboBox.setSelectedItem(comboBox.getItemAt(0));
    }
}
