package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.notimplemented.NotImplementedView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.*;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.*;

public class MerchantInventoryController extends AbstractModulesController<MerchantInventoryModelParameter, MerchantInventoryView, Model> {

    private Map<Integer, Pair<Class<? extends ControllableView>, ModelCreator>> itemTypesClassViews = new HashMap<>();

    public MerchantInventoryController(MerchantInventoryView view) {
        super(view);
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
    protected Model createModel() {
        int itemId = getItemId();
        int itemTypeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        Pair<Class<? extends ControllableView>, ModelCreator> pair = itemTypesClassViews.get(itemTypeId);
        // TODO remove in future, stub for not implemented items sold by merchants
        if (pair == null) {
            return null;
        } else {
            return pair.getValue().createModel(itemId);
        }
    }

    private int getItemId() {
        String selectedArmorPiece = getView().getSelectedModuleValue();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        int itemId = 0;
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (bundle.getString(key).equals(selectedArmorPiece)) {
                itemId = Integer.parseInt(key);
                break;
            }
        }
        return itemId;
    }

    @Override
    public void updateView() {
        List<Integer> itemIds = getModel().getParameter().getItemIds();
        getView().clearComboBoxAndMapping();

        for (Integer itemId : itemIds) {
            String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
            Class<? extends ControllableView> itemParametersViewClass = getItemParametersViewClassByItemId(itemId);
            getView().addMapping(itemName, itemParametersViewClass);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(getModel().getParameter().getSelectedItem());
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
}
