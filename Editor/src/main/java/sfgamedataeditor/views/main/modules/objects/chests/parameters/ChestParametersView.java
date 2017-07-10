package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.objects.chests.GUIElements;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

@SuppressWarnings("unused")
public class ChestParametersView implements PresentableView {
    private JPanel mainPanel;
    private JComboBox<String> dropItemsComboBox;

    @GUIElement(GUIElementId = GUIElements.DROP_POSSIBILITY_ITEM_1, DTOColumnNames = "chanceToGetItem1", DTOClass = ChestCorpseLootObject.class)
    private JPanel dropPossibilityItem1Panel;

    @GUIElement(GUIElementId = GUIElements.DROP_ITEM_1, DTOColumnNames = "itemId1", DTOClass = ChestCorpseLootObject.class)
    private JPanel dropItem1Panel;

    @GUIElement(GUIElementId = GUIElements.DROP_POSSIBILITY_ITEM_2, DTOColumnNames = "chanceToGetItem2", DTOClass = ChestCorpseLootObject.class)
    private JPanel dropPossibilityIItem2Panel;

    @GUIElement(GUIElementId = GUIElements.DROP_ITEM_2, DTOColumnNames = "itemId2", DTOClass = ChestCorpseLootObject.class)
    private JPanel dropItem2Panel;

    @GUIElement(GUIElementId = GUIElements.DROP_ITEM_3, DTOColumnNames = "itemId3", DTOClass = ChestCorpseLootObject.class)
    private JPanel dropItem3Panel;

    @IconElement
    private JLabel iconLabel;

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ChestParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox<String> getDropItemsComboBox() {
        return dropItemsComboBox;
    }

    @Override
    public void localize() {

    }
}
