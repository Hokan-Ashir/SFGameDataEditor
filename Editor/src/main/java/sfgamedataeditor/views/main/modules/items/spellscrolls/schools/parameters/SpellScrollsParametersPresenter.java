package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.views.common.AbstractParametersPresenter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.*;

public class SpellScrollsParametersPresenter extends AbstractParametersPresenter<SpellScrollsParametersModelParameter, SpellScrollsParametersView> {

    private static final Integer SCROLL_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.scrolls"));
    private static final Integer SPELL_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));

    private List<Integer> scrollGUIIds = new ArrayList<>();
    private List<Integer> spellGUIIds = new ArrayList<>();

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

        int selectedLevel = parameter.getLevel();
        String scrollBaseName = parameter.getScrollBaseName();
        Set<Integer> scrollLevels = getScrollLevels(scrollBaseName);
        String scrollName = scrollBaseName + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " " + selectedLevel;

        int scrollId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(scrollName, SCROLL_TYPE_ID);
        ItemPriceParametersObject scrollPriceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(scrollId);
        List<ItemSpellEffectsObject> scrollItemSpellEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(scrollId);

        int spellId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(scrollName, SPELL_TYPE_ID);
        ItemPriceParametersObject spellPriceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(spellId);
        List<ItemSpellEffectsObject> spellItemSpellEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(spellId);

        int guiElementId = annotation.GUIElementId();
        if (guiElementId == GUIElements.LEVEL) {
            LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, scrollLevels);
            widget.getListener().updateWidgetValue(levelComboBoxParameter);
        } else {
            Class<?> dtoClass = annotation.DTOClass();
            if (dtoClass.equals(ItemPriceParametersObject.class)) {
                if (scrollGUIIds.contains(guiElementId)) {
                    widget.getListener().updateWidgetValue(scrollPriceParametersObject);
                } else if (spellGUIIds.contains(guiElementId)) {
                    widget.getListener().updateWidgetValue(spellPriceParametersObject);
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
            }
        }
    }

    private Set<Integer> getScrollLevels(String scrollBaseName) {
        Set<Integer> scrollLevels = new TreeSet<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        String prefix = scrollBaseName + " - ";
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (value.startsWith(prefix)) {
                scrollLevels.add(Integer.valueOf(value.split("\\s")[3]));
            }
        }

        return scrollLevels;
    }
}
