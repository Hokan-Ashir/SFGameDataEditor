package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.notimplemented.NotImplementedView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.*;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantInventoryController extends AbstractController<MerchantInventoryModelParameter, MerchantInventoryView> implements ListSelectionListener, ActionListener {

    private Map<Integer, Pair<Class<? extends ControllableView>, ModelCreator>> itemTypesClassViews = new HashMap<>();

    public MerchantInventoryController(MerchantInventoryView view) {
        super(view);
        getView().getMerchantInventoryItemList().addListSelectionListener(this);
        getView().getGoToObjectButton().addActionListener(this);
        initializeItemTypesClassViewMap();
    }

    private void initializeItemTypesClassViewMap() {
        addArmorViewsMapping();
        addWeaponsViewsMapping();
        addMiscellaneousViewsMapping();
        addSpellScrollsViewsMapping();
    }

    private void addSpellScrollsViewsMapping() {
        SpellScrollsModelCreator creator = new SpellScrollsModelCreator();
        Pair<Class<? extends ControllableView>, ModelCreator> pair = new Pair<Class<? extends ControllableView>, ModelCreator>(SpellScrollsParametersView.class, creator);
        int scrollsTypeId = getItemTypeByNameMapping("items.scrolls");
        itemTypesClassViews.put(scrollsTypeId, pair);
    }

    private void addMiscellaneousViewsMapping() {
        MiscellaneousModelCreator creator = new MiscellaneousModelCreator();
        Pair<Class<? extends ControllableView>, ModelCreator> pair = new Pair<Class<? extends ControllableView>, ModelCreator>(MiscellaneousParametersView.class, creator);
        int miscellaneousTypeId = getItemTypeByNameMapping("items.miscellaneous");
        itemTypesClassViews.put(miscellaneousTypeId, pair);
    }

    private void addWeaponsViewsMapping() {
        WeaponModelCreator creator = new WeaponModelCreator();
        Pair<Class<? extends ControllableView>, ModelCreator> pair = new Pair<Class<? extends ControllableView>, ModelCreator>(WeaponParametersView.class, creator);
        int oneHandWeaponTypeId = getItemTypeByNameMapping("items.1h.weapon");
        itemTypesClassViews.put(oneHandWeaponTypeId, pair);

        int twoHandWeaponTypeId = getItemTypeByNameMapping("items.2h.weapon");
        itemTypesClassViews.put(twoHandWeaponTypeId, pair);

        int bowsTypeId = getItemTypeByNameMapping("items.bow");
        itemTypesClassViews.put(bowsTypeId, pair);
    }

    private void addArmorViewsMapping() {
        ArmorModelCreator creator = new ArmorModelCreator();
        Pair<Class<? extends ControllableView>, ModelCreator> pair = new Pair<Class<? extends ControllableView>, ModelCreator>(ArmorParametersView.class, creator);
        int helmetsId = getItemTypeByNameMapping("items.armor.helmets");
        itemTypesClassViews.put(helmetsId, pair);

        int chestArmorTypeId = getItemTypeByNameMapping("items.armor.chest.armor");
        itemTypesClassViews.put(chestArmorTypeId, pair);

        int robesTypeId = getItemTypeByNameMapping("items.armor.robes");
        itemTypesClassViews.put(robesTypeId, pair);

        int legsArmorTypeId = getItemTypeByNameMapping("items.armor.legs.armor");
        itemTypesClassViews.put(legsArmorTypeId, pair);

        int shieldsTypeId = getItemTypeByNameMapping("items.armor.shield");
        itemTypesClassViews.put(shieldsTypeId, pair);

        int ringsTypeId = getItemTypeByNameMapping("items.armor.rings");
        itemTypesClassViews.put(ringsTypeId, pair);
    }

    private Integer getItemTypeByNameMapping(String nameMapping) {
        return Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, nameMapping));
    }

    @Override
    public void updateView() {
        List<Integer> itemIds = getModel().getParameter().getItemIds();
        DefaultListModel model = (DefaultListModel) getView().getMerchantInventoryItemList().getModel();
        model.removeAllElements();

        for (Integer itemId : itemIds) {
            String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
            model.addElement(itemName);
        }

        getView().getMerchantInventoryItemList().setSelectedValue(getModel().getParameter().getSelectedItem(), true);
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

    private Class<? extends ControllableView> getItemParametersViewClassByItemId(int itemId) {
        int itemTypeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        Pair<Class<? extends ControllableView>, ModelCreator> pair = itemTypesClassViews.get(itemTypeId);
        // TODO remove in future, stub for not implemented items sold by merchants
        if (pair == null) {
            return NotImplementedView.class;
        } else {
            return pair.getKey();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // because list permit only to select one item at a time, it's not important which (first or last) selected
        // index get from click event
        // TODO temporary; remove later
//        int firstIndex = e.getFirstIndex();
//        ListModel<String> model = getView().getMerchantInventoryItemList().getModel();
//        String selectedItemName = model.getElementAt(firstIndex);
//        Integer itemId = getModel().getParameter().getItemIds().get(firstIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedItemIndex = getView().getMerchantInventoryItemList().getSelectedIndex();
        Integer itemId = getModel().getParameter().getItemIds().get(selectedItemIndex);
        Class<? extends ControllableView> classViewToShow = getItemParametersViewClassByItemId(itemId);
        Model model = createModel();
        EventProcessor.INSTANCE.process(new ShowContentViewEvent(classViewToShow, model));
    }

    private Model createModel() {
        String selectedArmorPiece = getView().getMerchantInventoryItemList().getSelectedValue();
        int itemId = ViewTools.getKeyByPropertyValue(selectedArmorPiece, I18NTypes.ITEMS);
        int itemTypeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        Pair<Class<? extends ControllableView>, ModelCreator> pair = itemTypesClassViews.get(itemTypeId);
        // TODO remove in future, stub for not implemented items sold by merchants
        if (pair == null) {
            return null;
        } else {
            return pair.getValue().createModel(itemId);
        }
    }
}
