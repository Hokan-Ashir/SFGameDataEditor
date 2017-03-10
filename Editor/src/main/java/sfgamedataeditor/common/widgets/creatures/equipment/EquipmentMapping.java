package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.common.notimplemented.NotImplementedView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingPlansModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.herorunes.HeroRunesModelCreator;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousModelCreator;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.SpellScrollsModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitPlansModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponModelCreator;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.items.workersrunes.WorkerRunesModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public enum EquipmentMapping {
    INSTANCE;

    private final Map<Integer, Pair<Class<? extends PresentableView>, ModelCreator>> itemTypesClassViews = new HashMap<>();

    EquipmentMapping() {
        initializeItemTypesClassViewMap();
    }

    private void initializeItemTypesClassViewMap() {
        addArmorViewsMapping();
        addWeaponsViewsMapping();
        addSpellScrollsViewsMapping();
        addMiscellaneousViewsMapping();
        addHeroesRunesViewMapping();
        addWorkerRunesViewMapping();
        addUnitPlansViewMapping();
        addBuildingPlansViewMapping();
        addObjectsWithoutParametersMapping();
    }

    private void addObjectsWithoutParametersMapping() {
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(NotImplementedView.class, null);
        int figureNPCId = getItemTypeByNameMapping("items.figureNPC");
        itemTypesClassViews.put(figureNPCId, pair);

        int heroArmyUnitsId = getItemTypeByNameMapping("items.hero.army.units");
        itemTypesClassViews.put(heroArmyUnitsId, pair);

        int blankScrollsId =  getItemTypeByNameMapping("items.blank.scrolls");
        itemTypesClassViews.put(blankScrollsId, pair);
    }

    private void addBuildingPlansViewMapping() {
        BuildingPlansModelCreator creator = new BuildingPlansModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(BuildingsPlansParametersView.class, creator);

        int humanBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.humans");
        itemTypesClassViews.put(humanBuildingPlansId, pair);

        int elvesBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.elves");
        itemTypesClassViews.put(elvesBuildingPlansId, pair);

        int dwarvesBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.dwarves");
        itemTypesClassViews.put(dwarvesBuildingPlansId, pair);

        int orcsBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.orcs");
        itemTypesClassViews.put(orcsBuildingPlansId, pair);

        int trollsBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.trolls");
        itemTypesClassViews.put(trollsBuildingPlansId, pair);

        int darkElvesBuildingPlansId = getItemTypeByNameMapping("items.building.plan.in.inventory.dark.elves");
        itemTypesClassViews.put(darkElvesBuildingPlansId, pair);
    }

    private void addUnitPlansViewMapping() {
        UnitPlansModelCreator creator = new UnitPlansModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(UnitsPlansParametersView.class, creator);

        int humanUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.humans");
        itemTypesClassViews.put(humanUnitPlansId, pair);

        int elvesUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.elves");
        itemTypesClassViews.put(elvesUnitPlansId, pair);

        int dwarvesUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.dwarves");
        itemTypesClassViews.put(dwarvesUnitPlansId, pair);

        int orcsUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.orcs");
        itemTypesClassViews.put(orcsUnitPlansId, pair);

        int trollsUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.trolls");
        itemTypesClassViews.put(trollsUnitPlansId, pair);

        int darkElvesUnitPlansId = getItemTypeByNameMapping("items.unit.plan.in.inventory.dark.elves");
        itemTypesClassViews.put(darkElvesUnitPlansId, pair);
    }

    private void addHeroesRunesViewMapping() {
        HeroRunesModelCreator creator = new HeroRunesModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(HeroesRunesParametersView.class, creator);
        int heroRunesTypeId = getItemTypeByNameMapping("items.rune.hero.in.inventory");
        itemTypesClassViews.put(heroRunesTypeId, pair);
    }

    private void addWorkerRunesViewMapping() {
        WorkerRunesModelCreator creator = new WorkerRunesModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(WorkersRunesParametersView.class, creator);

        int humanWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.humans");
        itemTypesClassViews.put(humanWorkerRunesId, pair);

        int elvesWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.elves");
        itemTypesClassViews.put(elvesWorkerRunesId, pair);

        int dwarvesWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.dwarves");
        itemTypesClassViews.put(dwarvesWorkerRunesId, pair);

        int orcsWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.orcs");
        itemTypesClassViews.put(orcsWorkerRunesId, pair);

        int trollsWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.trolls");
        itemTypesClassViews.put(trollsWorkerRunesId, pair);

        int darkElvesWorkerRunesId = getItemTypeByNameMapping("items.rune.workers.in.inventory.dark.elves");
        itemTypesClassViews.put(darkElvesWorkerRunesId, pair);
    }

    private void addSpellScrollsViewsMapping() {
        SpellScrollsModelCreator creator = new SpellScrollsModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(SpellScrollsParametersView.class, creator);
        int scrollsTypeId = getItemTypeByNameMapping("items.scrolls");
        itemTypesClassViews.put(scrollsTypeId, pair);
    }

    private void addMiscellaneousViewsMapping() {
        MiscellaneousModelCreator creator = new MiscellaneousModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(MiscellaneousParametersView.class, creator);
        int miscellaneousTypeId = getItemTypeByNameMapping("items.miscellaneous");
        itemTypesClassViews.put(miscellaneousTypeId, pair);
    }

    private void addWeaponsViewsMapping() {
        WeaponModelCreator creator = new WeaponModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(WeaponParametersView.class, creator);
        int oneHandWeaponTypeId = getItemTypeByNameMapping("items.1h.weapon");
        itemTypesClassViews.put(oneHandWeaponTypeId, pair);

        int twoHandWeaponTypeId = getItemTypeByNameMapping("items.2h.weapon");
        itemTypesClassViews.put(twoHandWeaponTypeId, pair);

        int bowsTypeId = getItemTypeByNameMapping("items.bow");
        itemTypesClassViews.put(bowsTypeId, pair);
    }

    private void addArmorViewsMapping() {
        ArmorModelCreator creator = new ArmorModelCreator();
        Pair<Class<? extends PresentableView>, ModelCreator> pair = new Pair<Class<? extends PresentableView>, ModelCreator>(ArmorParametersView.class, creator);
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
        return Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, nameMapping));
    }

    public Class<? extends PresentableView> getItemParametersViewClassByItemId(int itemId) {
        int itemTypeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        Pair<Class<? extends PresentableView>, ModelCreator> pair = itemTypesClassViews.get(itemTypeId);
        return pair.getKey();
    }

    public Model createModel(Integer itemId) {
        int itemTypeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        Pair<Class<? extends PresentableView>, ModelCreator> pair = itemTypesClassViews.get(itemTypeId);
        if (pair.getValue() != null) {
            // TODO add Icon creation
            return pair.getValue().createModel(itemId, null);
        } else {
            return null;
        }
    }

    public boolean isItemTypeHasNoParameters(int itemType) {
        Pair<Class<? extends PresentableView>, ModelCreator> pair = itemTypesClassViews.get(itemType);
        if (pair == null) {
            String weaponTypeNameById = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(itemType);
            if(weaponTypeNameById != null) {
                return false;
            } else {
                return true;
            }
        } else {
            return pair.getKey().equals(NotImplementedView.class);
        }
    }
}
