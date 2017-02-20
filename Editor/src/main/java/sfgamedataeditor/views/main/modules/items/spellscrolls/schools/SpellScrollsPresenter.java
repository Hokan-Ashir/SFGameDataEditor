package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.SpellScrollsModelCreator;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellScrollsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private final SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsPresenter(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
        int itemId;
        try {
            String selectedSpellScroll = getView().getSelectedModuleValue() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " 1";
            itemId = ViewTools.getKeyByPropertyValue(selectedSpellScroll, I18NTypes.ITEMS);
        } catch (NumberFormatException e) {
            String selectedSpellScroll = getView().getSelectedModuleValue() + " - " + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level") + " 13";
            itemId = ViewTools.getKeyByPropertyValue(selectedSpellScroll, I18NTypes.ITEMS);
        }
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
