package sfgamedataeditor.events.processing.strategies.content.viewhierarchy;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.ModulesModuleCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.SkillSchoolsFromSkillParameterModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.SpellSchoolsFromSpellsModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.SpellsFromSpellParameterModelCreator;
import sfgamedataeditor.events.processing.strategies.content.modelcreators.modules.*;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.main.modules.units.UnitsRacesView;

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
                createUnitNodes(rootNode));
    }

    private ViewHierarchyNode createMerchantsNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, MerchantLocationsView.class, new ModulesFromMerchantsModelCreator());
    }

    private ViewHierarchyNode createItemsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode itemTypes = new ViewHierarchyNode(rootNode, ItemTypesView.class, new ModulesFromItemsModelCreator());
        ViewHierarchyNode armor = new ViewHierarchyNode(itemTypes, ArmorTypeListView.class, null);
        ViewHierarchyNode buildingPlans = new ViewHierarchyNode(itemTypes, ArmorTypeListView.class, null);
        ViewHierarchyNode miscellaneous = new ViewHierarchyNode(itemTypes, MiscellaneousListView.class, null);
        ViewHierarchyNode runes = new ViewHierarchyNode(itemTypes, RuneRacesListView.class, null);
        ViewHierarchyNode spellScrolls = new ViewHierarchyNode(itemTypes, SpellScrollsListView.class, null);
        ViewHierarchyNode weapons = new ViewHierarchyNode(itemTypes, WeaponsTypesListView.class, null);
        itemTypes.addChildren(armor, buildingPlans, miscellaneous, runes, spellScrolls, weapons);
        return itemTypes;
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

    private ViewHierarchyNode createUnitNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, UnitsRacesView.class, new ModulesFromUnitsModelCreator());
    }

    public List<ViewHierarchyNode> getNodesToShow(Class<? extends ControllableView> leafViewClass) {
        List<ViewHierarchyNode> result = new ArrayList<>();
        ViewHierarchyNode node = findLeafNode(leafViewClass);
        while (node != null) {
            result.add(node);
            node = node.getParentNode();
        }

        return result;
    }

    private ViewHierarchyNode findLeafNode(Class<? extends ControllableView> leafViewClass) {
        return findNode(rootNode, leafViewClass);
    }

    private ViewHierarchyNode findNode(ViewHierarchyNode node, Class<? extends ControllableView> leafViewClass) {
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
