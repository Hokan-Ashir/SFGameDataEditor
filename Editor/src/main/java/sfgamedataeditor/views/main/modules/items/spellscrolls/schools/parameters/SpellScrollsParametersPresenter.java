package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.effects.ItemEffectsTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpellScrollsParametersPresenter extends AbstractParametersPresenter<SpellScrollsParametersModelParameter, SpellScrollsParametersView> {

    private final List<Integer> scrollGUIIds = new ArrayList<>();
    private final List<Integer> spellGUIIds = new ArrayList<>();

    public SpellScrollsParametersPresenter(SpellScrollsParametersView view) {
        super(view);
        initializeScrollGUIIdList();
        initializeSpellGUIIdList();
    }

    private void initializeSpellGUIIdList() {
        spellGUIIds.add(GUIElements.SPELL_BUY_OUT_PRICE);
        spellGUIIds.add(GUIElements.SPELL_SELL_PRICE);
        spellGUIIds.add(GUIElements.SPELL_ITEM_EFFECT);
        spellGUIIds.add(GUIElements.SPELL_ITEM_SET);
    }

    private void initializeScrollGUIIdList() {
        scrollGUIIds.add(GUIElements.SCROLL_BUY_OUT_PRICE);
        scrollGUIIds.add(GUIElements.SCROLL_SELL_PRICE);
        scrollGUIIds.add(GUIElements.SCROLL_ITEM_EFFECT);
        scrollGUIIds.add(GUIElements.SCROLL_ITEM_SET);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        SpellScrollsParametersModelParameter parameter = getModel().getParameter();

        Integer selectedLevel = parameter.getLevel();
        Set<Integer> scrollLevels = parameter.getLevelToItemsIdMap().keySet();

        Integer scrollId = parameter.getLevelToItemsIdMap().get(selectedLevel).getValue();
        ItemPriceParametersObject scrollPriceParametersObject = null;
        List<ItemSpellEffectsObject> scrollItemSpellEffectsObjects = null;
        if (scrollId != null) {
            scrollPriceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(scrollId);
            scrollItemSpellEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(scrollId);
        }

        Integer spellId = parameter.getLevelToItemsIdMap().get(selectedLevel).getKey();
        ItemEffectsObject itemEffectsObject = null;
        List<ItemSpellEffectsObject> spellItemSpellEffectsObjects = null;
        ItemPriceParametersObject spellPriceParametersObject = null;
        if (spellId != null) {
            spellPriceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(spellId);
            spellItemSpellEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(spellId);
            itemEffectsObject = ItemEffectsTableService.INSTANCE.getObjectByItemId(spellId);
        }

        int guiElementId = annotation.GUIElementId();
        if (guiElementId == GUIElements.LEVEL) {
            LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, scrollLevels);
            widget.getListener().updateWidgetValue(levelComboBoxParameter);
        } else {
            Class<?> dtoClass = annotation.DTOClass();
            if (dtoClass.equals(ItemPriceParametersObject.class)) {
                if (scrollGUIIds.contains(guiElementId)) {
                    if (scrollPriceParametersObject == null) {
                        widget.setVisible(false);
                    } else {
                        widget.setVisible(true);
                        widget.getListener().updateWidgetValue(scrollPriceParametersObject);
                    }
                } else if (spellGUIIds.contains(guiElementId)) {
                    if (spellPriceParametersObject == null) {
                        widget.setVisible(false);
                    } else {
                        widget.setVisible(true);
                        widget.getListener().updateWidgetValue(spellPriceParametersObject);
                    }
                }
            } else if (dtoClass.equals(ItemSpellEffectsObject.class)) {
                if (scrollGUIIds.contains(guiElementId)) {
                    if (scrollItemSpellEffectsObjects == null || scrollItemSpellEffectsObjects.isEmpty()) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        // it's guaranteed that scroll has only one spell effect on it
                        widget.getListener().updateWidgetValue(scrollItemSpellEffectsObjects.get(0));
                    }
                } else if (spellGUIIds.contains(guiElementId)) {
                    if (spellItemSpellEffectsObjects == null || spellItemSpellEffectsObjects.isEmpty()) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        // it's guaranteed that spell has only one spell effect on it
                        widget.getListener().updateWidgetValue(spellItemSpellEffectsObjects.get(0));
                    }
                }
            } else if (dtoClass.equals(ItemEffectsObject.class)) {
                if (itemEffectsObject == null) {
                    panel.setVisible(false);
                } else {
                    panel.setVisible(true);
                    // it's guaranteed that spell has only one item effect on it
                    widget.getListener().updateWidgetValue(itemEffectsObject);
                }
            }
        }
    }
}
