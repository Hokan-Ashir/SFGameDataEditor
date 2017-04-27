package sfgamedataeditor.events.processing.strategies.content.viewhierarchy;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.ModulesModuleCreator;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.buildings.races.BuildingRacesView;
import sfgamedataeditor.views.main.modules.buildings.races.ModulesFromBuildingsModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingRacesFromBuildingsModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsView;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsFromBuildingsParametersModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.ModulesFromCreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreatureRacesFromCreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesFromCreatureParametersModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.items.ModulesFromItemsModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.armor.ItemsFromArmorModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorTypesFromArmorPiecesModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorPiecesFromArmorParametersModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansRacesView;
import sfgamedataeditor.views.main.modules.items.buildingplans.ItemsFromBuildingPlansModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingPlansFromBuildingPlansListModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingPlansFromBuildingPlansParameterModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.herorunes.HeroesRunesListView;
import sfgamedataeditor.views.main.modules.items.herorunes.ItemsFromHeroesRunesListModelCreator;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesListFromHeroesRunesParametersModelCreator;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.ItemsFromMiscellaneousModelCreator;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousFromMiscellaneousParametersModelCreator;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.ItemsFromScrollsModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.ScrollsFromScrollsParametersModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.main.modules.items.unitplans.ItemsFromUnitPlansModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitPlansRacesView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlansFromUnitsPlansListModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansFromUnitsPlansParameterModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.ItemsFromWeaponModelCreator;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponTypesFromWeaponPiecesModelCreator;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponPiecesFromWeaponParametersModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.ItemsFromWorkersRunesRacesModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.WorkersRuneRacesView;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesFromWorkersRunesParameterModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;
import sfgamedataeditor.views.main.modules.objects.chests.ChestsListView;
import sfgamedataeditor.views.main.modules.objects.chests.ModulesFromChestsListModelCreator;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersView;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestsListFromChestsParametersModelCreator;
import sfgamedataeditor.views.main.modules.skills.schools.ModulesFromSkillSchoolsModelCreator;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillSchoolsFromSkillParameterModelCreator;
import sfgamedataeditor.views.main.modules.spells.schools.ModulesFromSpellSchoolsModelCreator;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellSchoolsFromSpellsModelCreator;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellsFromSpellParameterModelCreator;
import sfgamedataeditor.views.main.modules.units.races.ModulesFromUnitsModelCreator;
import sfgamedataeditor.views.main.modules.units.races.UnitRacesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitRacesFromUnitsModelCreator;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsFromUnitParametersModelCreator;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;

import java.util.ArrayList;
import java.util.List;

public enum  ViewHierarchy {
    INSTANCE;

    private final ViewHierarchyNode rootNode = new ViewHierarchyNode(null, ModulesView.class, new ModulesModuleCreator());

    ViewHierarchy() {
        rootNode.addChildren(createBuildingsNodes(rootNode),
                createItemsNodes(rootNode),
                createSkillNodes(rootNode),
                createSpellNodes(rootNode),
                createCreaturesNodes(rootNode),
                createUnitsNodes(rootNode),
                createChestNodes(rootNode));
    }

    private ViewHierarchyNode createChestNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode chestsList = new ViewHierarchyNode(rootNode, ChestsListView.class, new ModulesFromChestsListModelCreator());
        ViewHierarchyNode parameters = new ViewHierarchyNode(chestsList, ChestParametersView.class, new ChestsListFromChestsParametersModelCreator());
        chestsList.addChild(parameters);

        return chestsList;
    }

    private ViewHierarchyNode createUnitsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode units = new ViewHierarchyNode(rootNode, UnitRacesView.class, new ModulesFromUnitsModelCreator());
        ViewHierarchyNode unitList = new ViewHierarchyNode(units, UnitListView.class, new UnitRacesFromUnitsModelCreator());
        units.addChild(unitList);

        ViewHierarchyNode unitParameters = new ViewHierarchyNode(unitList, UnitsParametersView.class, new UnitsFromUnitParametersModelCreator());
        unitList.addChild(unitParameters);

        return units;
    }

    private ViewHierarchyNode createItemsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode itemTypes = new ViewHierarchyNode(rootNode, ItemTypesView.class, new ModulesFromItemsModelCreator());
        ViewHierarchyNode armor = createArmorNodes(itemTypes);

        ViewHierarchyNode miscellaneous = createMiscellaneousNodes(itemTypes);

        ViewHierarchyNode spellScrolls = createSpellScrollsNodes(itemTypes);

        ViewHierarchyNode weapons = createWeaponsNodes(itemTypes);

        ViewHierarchyNode buildingPlans = createBuildingPlansNodes(itemTypes);
        ViewHierarchyNode unitPlans = createUnitPlansNodes(itemTypes);

        ViewHierarchyNode workersRunes = createWorkersRunesNodes(itemTypes);
        ViewHierarchyNode heroesRunes = createHeroesRunesNodes(itemTypes);

        itemTypes.addChildren(armor, unitPlans, buildingPlans, miscellaneous, spellScrolls, weapons, workersRunes, heroesRunes);
        return itemTypes;
    }

    private ViewHierarchyNode createHeroesRunesNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode runesList = new ViewHierarchyNode(itemTypes, HeroesRunesListView.class, new ItemsFromHeroesRunesListModelCreator());
        ViewHierarchyNode parameters = new ViewHierarchyNode(runesList, HeroesRunesParametersView.class, new HeroesRunesListFromHeroesRunesParametersModelCreator());
        runesList.addChild(parameters);

        return runesList;
    }

    private ViewHierarchyNode createWorkersRunesNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode races = new ViewHierarchyNode(itemTypes, WorkersRuneRacesView.class, new ItemsFromWorkersRunesRacesModelCreator());
        ViewHierarchyNode parameters = new ViewHierarchyNode(races, WorkersRunesParametersView.class, new WorkersRunesFromWorkersRunesParameterModelCreator());
        races.addChild(parameters);

        return races;
    }

    private ViewHierarchyNode createUnitPlansNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode races = new ViewHierarchyNode(itemTypes, UnitPlansRacesView.class, new ItemsFromUnitPlansModelCreator());
        ViewHierarchyNode units = new ViewHierarchyNode(races, UnitsPlanListView.class, new UnitsPlansFromUnitsPlansListModelCreator());
        races.addChild(units);

        ViewHierarchyNode parameters = new ViewHierarchyNode(units, UnitsPlansParametersView.class, new UnitsPlansFromUnitsPlansParameterModelCreator());
        units.addChild(parameters);

        return races;
    }

    private ViewHierarchyNode createBuildingPlansNodes(ViewHierarchyNode itemTypes) {
        ViewHierarchyNode races = new ViewHierarchyNode(itemTypes, BuildingPlansRacesView.class, new ItemsFromBuildingPlansModelCreator());
        ViewHierarchyNode buildings = new ViewHierarchyNode(races, BuildingsPlanListView.class, new BuildingPlansFromBuildingPlansListModelCreator());
        races.addChild(buildings);

        ViewHierarchyNode parameters = new ViewHierarchyNode(buildings, BuildingsPlansParametersView.class, new BuildingPlansFromBuildingPlansParameterModelCreator());
        buildings.addChild(parameters);

        return races;
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
        ViewHierarchyNode buildingRaces = new ViewHierarchyNode(rootNode, BuildingRacesView.class, new ModulesFromBuildingsModelCreator());
        ViewHierarchyNode buildings = new ViewHierarchyNode(buildingRaces, BuildingsView.class, new BuildingRacesFromBuildingsModelCreator());
        buildingRaces.addChild(buildings);
        ViewHierarchyNode buildingParameters = new ViewHierarchyNode(buildings, BuildingsParametersView.class, new BuildingsFromBuildingsParametersModelCreator());
        buildings.addChild(buildingParameters);

        return buildingRaces;
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
