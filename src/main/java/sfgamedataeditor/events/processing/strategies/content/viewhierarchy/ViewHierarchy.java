package sfgamedataeditor.events.processing.strategies.content.viewhierarchy;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.ModulesModuleCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.creatures.CreatureRacesFromCreaturesModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.creatures.CreaturesFromCreatureParametersModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.armor.ArmorPiecesFromArmorParametersModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.armor.ArmorTypesFromArmorPiecesModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.armor.ItemsFromArmorModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.miscellaneous.ItemsFromMiscellaneousModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.miscellaneous.MiscellaneousFromMiscellaneousParametersModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.scrolls.ItemsFromScrollsModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.scrolls.ScrollsFromScrollsParametersModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons.ItemsFromWeaponModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons.WeaponPiecesFromWeaponParametersModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons.WeaponTypesFromWeaponPiecesModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.merchants.MerchantsFromMerchantItemsModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.modules.*;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.skills.SkillSchoolsFromSkillParameterModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.spells.SpellSchoolsFromSpellsModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.spells.SpellsFromSpellParameterModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.units.UnitRacesFromUnitsModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.units.UnitsFromUnitParametersModelCreator;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.merchants.MerchantsView;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.main.modules.units.races.UnitRacesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;

import java.util.ArrayList;
import java.util.List;

public enum  ViewHierarchy {
    INSTANCE;

    private final ViewHierarchyNode rootNode = new ViewHierarchyNode(null, ModulesView.class, new ModulesModuleCreator());

    ViewHierarchy() {
        rootNode.addChildren(createBuildingsNodes(rootNode),
                createItemsNodes(rootNode),
                createMerchantsNodes(rootNode),
                createSkillNodes(rootNode),
                createSpellNodes(rootNode),
                createCreaturesNodes(rootNode),
                createUnitsNodes(rootNode));
    }

    private ViewHierarchyNode createUnitsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode units = new ViewHierarchyNode(rootNode, UnitRacesView.class, new ModulesFromUnitsModelCreator());
        ViewHierarchyNode unitList = new ViewHierarchyNode(units, UnitListView.class, new UnitRacesFromUnitsModelCreator());
        units.addChild(unitList);

        ViewHierarchyNode unitParameters = new ViewHierarchyNode(unitList, UnitsParametersView.class, new UnitsFromUnitParametersModelCreator());
        unitList.addChild(unitParameters);

        return units;
    }

    private ViewHierarchyNode createMerchantsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode merchants = new ViewHierarchyNode(rootNode, MerchantsView.class, new ModulesFromMerchantsModelCreator());
        ViewHierarchyNode merchantItems = new ViewHierarchyNode(merchants, MerchantInventoryView.class, new MerchantsFromMerchantItemsModelCreator());
        merchants.addChild(merchantItems);

        return merchants;
    }

    private ViewHierarchyNode createItemsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode itemTypes = new ViewHierarchyNode(rootNode, ItemTypesView.class, new ModulesFromItemsModelCreator());
        ViewHierarchyNode armor = createArmorNodes(itemTypes);

        ViewHierarchyNode buildingPlans = new ViewHierarchyNode(itemTypes, BuildingPlansListView.class, null);
        ViewHierarchyNode miscellaneous = createMiscellaneousNodes(itemTypes);

        ViewHierarchyNode runes = new ViewHierarchyNode(itemTypes, RuneRacesListView.class, null);
        ViewHierarchyNode spellScrolls = createSpellScrollsNodes(itemTypes);

        ViewHierarchyNode weapons = createWeaponsNodes(itemTypes);

        itemTypes.addChildren(armor, buildingPlans, miscellaneous, runes, spellScrolls, weapons);
        return itemTypes;
    }

    private ViewHierarchyNode createSpellScrollsNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode spellScrolls = new ViewHierarchyNode(itemTypes, SpellScrollsListView.class, new ItemsFromScrollsModelCreator());
        ViewHierarchyNode spellScrollsParameters = new ViewHierarchyNode(spellScrolls, SpellScrollsParametersView.class, new ScrollsFromScrollsParametersModelCreator());
        spellScrolls.addChild(spellScrollsParameters);
        return spellScrolls;
    }

    private ViewHierarchyNode createMiscellaneousNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode miscellaneous = new ViewHierarchyNode(itemTypes, MiscellaneousListView.class, new ItemsFromMiscellaneousModelCreator());
        ViewHierarchyNode miscellaneousParameters = new ViewHierarchyNode(miscellaneous, MiscellaneousParametersView.class, new MiscellaneousFromMiscellaneousParametersModelCreator());
        miscellaneous.addChild(miscellaneousParameters);
        return miscellaneous;
    }

    private ViewHierarchyNode createWeaponsNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode weapons = new ViewHierarchyNode(itemTypes, WeaponsTypesListView.class, new ItemsFromWeaponModelCreator());
        ViewHierarchyNode weaponPieces = new ViewHierarchyNode(weapons, WeaponPiecesView.class, new WeaponTypesFromWeaponPiecesModelCreator());
        weapons.addChild(weaponPieces);
        ViewHierarchyNode weaponParameters = new ViewHierarchyNode(weaponPieces, WeaponParametersView.class, new WeaponPiecesFromWeaponParametersModelCreator());
        weaponPieces.addChild(weaponParameters);
        return weapons;
    }

    private ViewHierarchyNode createArmorNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode armor = new ViewHierarchyNode(itemTypes, ArmorTypeListView.class, new ItemsFromArmorModelCreator());
        ViewHierarchyNode armorPieces = new ViewHierarchyNode(armor, ArmorPiecesView.class, new ArmorTypesFromArmorPiecesModelCreator());
        armor.addChild(armorPieces);
        ViewHierarchyNode armorParameters = new ViewHierarchyNode(armorPieces, ArmorParametersView.class, new ArmorPiecesFromArmorParametersModelCreator());
        armorPieces.addChild(armorParameters);
        return armor;
    }

    private ViewHierarchyNode createBuildingsNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, BuildingRacesView.class, new ModulesFromBuildingsModelCreator());
    }

    private ViewHierarchyNode createSkillNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode skillSchools = new ViewHierarchyNode(rootNode, SkillSchoolsView.class, new ModulesFromSkillSchoolsModelCreator());
        ViewHierarchyNode skillParameters = new ViewHierarchyNode(skillSchools, SkillParameterView.class, new SkillSchoolsFromSkillParameterModelCreator());
        skillSchools.addChild(skillParameters);
        return skillSchools;
    }

    private ViewHierarchyNode createSpellNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode spellSchools = new ViewHierarchyNode(rootNode, SpellSchoolsView.class, new ModulesFromSpellSchoolsModelCreator());
        ViewHierarchyNode spells = new ViewHierarchyNode(spellSchools, SpellsView.class, new SpellSchoolsFromSpellsModelCreator());
        spellSchools.addChild(spells);
        ViewHierarchyNode spellParameters = new ViewHierarchyNode(spells, SpellParameterView.class, new SpellsFromSpellParameterModelCreator());
        spells.addChild(spellParameters);

        return spellSchools;
    }

    private ViewHierarchyNode createCreaturesNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode creatureRaces = new ViewHierarchyNode(rootNode, CreaturesRacesView.class, new ModulesFromCreaturesModelCreator());
        ViewHierarchyNode creatures = new ViewHierarchyNode(creatureRaces, CreaturesView.class, new CreatureRacesFromCreaturesModelCreator());
        creatureRaces.addChild(creatures);
        ViewHierarchyNode creatureParameters = new ViewHierarchyNode(creatures, CreaturesParametersView.class, new CreaturesFromCreatureParametersModelCreator());
        creatures.addChild(creatureParameters);

        return creatureRaces;
    }

    public List<ViewHierarchyNode> getNodesToShow(Class<? extends PresentableView> leafViewClass) {
        List<ViewHierarchyNode> result = new ArrayList<>();
        ViewHierarchyNode node = findLeafNode(leafViewClass);
        while (node != null) {
            result.add(node);
            node = node.getParentNode();
        }

        return result;
    }

    private ViewHierarchyNode findLeafNode(Class<? extends PresentableView> leafViewClass) {
        return findNode(rootNode, leafViewClass);
    }

    private ViewHierarchyNode findNode(ViewHierarchyNode node, Class<? extends PresentableView> leafViewClass) {
        if (node.getViewClass().equals(leafViewClass)) {
            return node;
        }

        for (ViewHierarchyNode viewHierarchyNode : node.getChildren()) {
            ViewHierarchyNode result = findNode(viewHierarchyNode, leafViewClass);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public List<ViewHierarchyNode> getRenderedNodes() {
        return getRenderedNodes(rootNode);
    }

    private List<ViewHierarchyNode> getRenderedNodes(ViewHierarchyNode node) {
        List<ViewHierarchyNode> renderedNodes = new ArrayList<>();
        if (node.isRenderedOnScreen()) {
            renderedNodes.add(node);
        }

        for (ViewHierarchyNode viewHierarchyNode : node.getChildren()) {
            renderedNodes.addAll(getRenderedNodes(viewHierarchyNode));
        }

        return renderedNodes;
    }
}
