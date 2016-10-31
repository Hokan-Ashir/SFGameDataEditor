package sfgamedataeditor.mvc.viewhierarchy;

import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
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

    private final ViewHierarchyNode rootNode = new ViewHierarchyNode(null, MainView.class);

    ViewHierarchy() {
        rootNode.addChildren(createEventHistoryNodes(rootNode),
                createModulesNodes(rootNode),
                createButtonsNodes(rootNode));
    }

    private ViewHierarchyNode createButtonsNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, ButtonsView.class);
    }

    private ViewHierarchyNode createEventHistoryNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, EventHistoryView.class);
    }

    private ViewHierarchyNode createModulesNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode modules = new ViewHierarchyNode(rootNode, ModulesView.class);
        modules.addChildren(createBuildingsNodes(modules),
                createItemsNodes(modules),
                createMerchantsNodes(modules),
                createSkillNodes(modules),
                createSpellNodes(modules),
                createUnitNodes(modules)
        );
        return modules;
    }

    private ViewHierarchyNode createMerchantsNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, MerchantLocationsView.class);
    }

    private ViewHierarchyNode createItemsNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode itemTypes = new ViewHierarchyNode(rootNode, ItemTypesView.class);
        ViewHierarchyNode armor = new ViewHierarchyNode(itemTypes, ArmorTypeListView.class);
        ViewHierarchyNode buildingPlans = new ViewHierarchyNode(itemTypes, ArmorTypeListView.class);
        ViewHierarchyNode miscellaneous = new ViewHierarchyNode(itemTypes, MiscellaneousListView.class);
        ViewHierarchyNode runes = new ViewHierarchyNode(itemTypes, RuneRacesListView.class);
        ViewHierarchyNode spellScrolls = new ViewHierarchyNode(itemTypes, SpellScrollsListView.class);
        ViewHierarchyNode weapons = new ViewHierarchyNode(itemTypes, WeaponsTypesListView.class);
        itemTypes.addChildren(armor, buildingPlans, miscellaneous, runes, spellScrolls, weapons);
        return itemTypes;
    }

    private ViewHierarchyNode createBuildingsNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, BuildingRacesView.class);
    }

    private ViewHierarchyNode createSkillNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode skillSchools = new ViewHierarchyNode(rootNode, SkillSchoolsView.class);
        ViewHierarchyNode skillParameters = new ViewHierarchyNode(skillSchools, SkillParameterView.class);
        skillSchools.addChild(skillParameters);
        return skillSchools;
    }

    private ViewHierarchyNode createSpellNodes(ViewHierarchyNode rootNode) {
        ViewHierarchyNode spellSchools = new ViewHierarchyNode(rootNode, SpellSchoolsView.class);
        ViewHierarchyNode spells = new ViewHierarchyNode(spellSchools, SpellsView.class);
        spellSchools.addChild(spells);
        ViewHierarchyNode spellParameters = new ViewHierarchyNode(spells, SpellParameterView.class);
        spells.addChild(spellParameters);

        return spellSchools;
    }

    private ViewHierarchyNode createUnitNodes(ViewHierarchyNode rootNode) {
        return new ViewHierarchyNode(rootNode, UnitsRacesView.class);
    }
    
    public List<Class<? extends RenderableView>> getViewsToShow(Class<? extends RenderableView> leafViewClass) {
        List<Class<? extends RenderableView>> viewBranch = new ArrayList<>();
        ViewHierarchyNode node = findLeafNode(leafViewClass);
        while (node != null) {
            viewBranch.add(node.getViewClass());
            node = node.getParentNode();
        }

        return viewBranch;
    }

    private ViewHierarchyNode findLeafNode(Class<? extends RenderableView> leafViewClass) {
        return findNode(rootNode, leafViewClass);
    }

    private ViewHierarchyNode findNode(ViewHierarchyNode node, Class<? extends RenderableView> leafViewClass) {
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
}
