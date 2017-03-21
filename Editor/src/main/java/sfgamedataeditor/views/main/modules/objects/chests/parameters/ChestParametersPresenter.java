package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.views.common.AbstractParametersPresenter;
import sfgamedataeditor.views.common.dropitems.BaseDropItemsComboBoxListener;
import sfgamedataeditor.views.common.dropitems.ChestLootDropItemsComboBoxListener;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class ChestParametersPresenter extends AbstractParametersPresenter<ChestParametersModelParameter, ChestParametersView> {

    private final BaseDropItemsComboBoxListener dropItemsListener;

    public ChestParametersPresenter(ChestParametersView view) {
        super(view);
        dropItemsListener = new ChestLootDropItemsComboBoxListener(getView(), ChestCorpseLootObject.class);
        getView().getDropItemsComboBox().addItemListener(dropItemsListener);
    }

    @Override
    public void updateView() {
        final List<ChestCorpseLootObject> chestCorpseLootObjects = getModel().getParameter().getChestCorpseLootObjects();

        final JComboBox<String> dropItemsComboBox = getView().getDropItemsComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(dropItemsComboBox) {
            @Override
            protected void setValues() {
                dropItemsComboBox.removeAllItems();
                String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "drop.item");
                for (int i = 0; i < chestCorpseLootObjects.size(); i++) {
                    dropItemsComboBox.addItem(dropItem + " - " + (i + 1));
                }
                dropItemsComboBox.setSelectedItem(null);
            }
        });

        dropItemsListener.setDroppingObjects(chestCorpseLootObjects);
        dropItemsComboBox.setSelectedItem(dropItemsComboBox.getItemAt(0));

        super.updateView();
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {

    }
}