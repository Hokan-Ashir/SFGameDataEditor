package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.GUIElements;
import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

@SuppressWarnings("unused")
public class SpellScrollsParametersView implements PresentableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SCROLL_SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPriceScrollPanel;

    @GUIElement(GUIElementId = GUIElements.SCROLL_BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPriceScrollPanel;

    @GUIElement(GUIElementId = GUIElements.SCROLL_ITEM_EFFECT, DTOColumnNames = "effectNumber", DTOClass = ItemSpellEffectsObject.class)
    private JPanel itemEffectScrollPanel;

    @IconElement
    private JLabel iconLabel;

    @GUIElement(GUIElementId = GUIElements.SCROLL_ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetScrollPanel;

    @GUIElement(GUIElementId = GUIElements.LEVEL)
    private JPanel levelPanel;
    private JPanel scrollObjectPanel;
    private JPanel spellObjectPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPriceSpellPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPriceSpellPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetSpellPanel;

    @GUIElement(GUIElementId = GUIElements.SPELL_ITEM_EFFECT, DTOColumnNames = "effectNumber", DTOClass = ItemSpellEffectsObject.class)
    private JPanel itemEffectSpellPanel;
    private JLabel scrollParametersLabel;
    private JLabel spellParametersLabel;

    @GUIElement(GUIElementId = GUIElements.ITEM_EFFECT_NUMBER, DTOColumnNames = "effectNumber", DTOClass = ItemEffectsObject.class)
    private JPanel itemEffectPanel;

    public SpellScrollsParametersView() {
        scrollParametersLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SCROLLS_AND_SPELLS_GUI, "scroll.parameters"));
        spellParametersLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SCROLLS_AND_SPELLS_GUI, "spell.parameters"));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellScrollsParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
