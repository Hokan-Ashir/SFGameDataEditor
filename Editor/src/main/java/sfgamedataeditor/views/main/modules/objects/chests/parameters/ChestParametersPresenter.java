package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.dropitems.BaseDropItemsComboBoxListener;
import sfgamedataeditor.views.common.dropitems.ChestLootDropItemsComboBoxListener;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class ChestParametersPresenter extends AbstractPresenter<ChestParametersModelParameter, ChestParametersView> {

    private static final Logger LOGGER = Logger.getLogger(ChestParametersPresenter.class);
    private final BaseDropItemsComboBoxListener dropItemsListener;

    public ChestParametersPresenter(ChestParametersView view) {
        super(view);
        dropItemsListener = new ChestLootDropItemsComboBoxListener(getView(), ChestCorpseLootObject.class);
        getView().getDropItemsComboBox().addItemListener(dropItemsListener);
    }

    @Override
    public void updateView() {
        final List<ChestCorpseLootObject> chestCorpseLootObjects = getModel().getParameter().getChestCorpseLootObjects();
        Icon icon = getModel().getParameter().getIcon();

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

        Field[] declaredFields = getView().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                IconElement iconElement = declaredField.getAnnotation(IconElement.class);
                if (iconElement != null) {
                    declaredField.setAccessible(true);
                    JLabel panel = (JLabel) declaredField.get(getView());
                    panel.setIcon(icon);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
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
