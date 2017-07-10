package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class SpellScrollsListView extends AbstractModulesView {

    public static final String SPELL_NAME_REG_EXP = ",?(\\s+)?\\b(\\d+)?(\\s+)?([Уу]ровень|[Уу]р.|liv.|[Nn]iveau|[Nn]ivel|[Ll]ivello|[Pp]oziom|[Ll]evel|[Ss]tufe)(\\s+)?(\\d+)?";

    public SpellScrollsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.scrolls.and.spells"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));
        List<ObjectTuple> mappings = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        mappings = getFilteredScrollNames(mappings);
        addMappings(mappings, SpellScrollsParametersView.class);
    }

    private List<ObjectTuple> getFilteredScrollNames(List<ObjectTuple> scrollNames) {
        List<ObjectTuple> result = new ArrayList<>();
        for (ObjectTuple scrollName : scrollNames) {
            String originalScrollName = scrollName.getName().replaceAll(SPELL_NAME_REG_EXP, "");

            boolean isSpellNameExists = false;
            for (ObjectTuple objectTuple : result) {
                if (objectTuple.getName().equals(originalScrollName)) {
                    isSpellNameExists = true;
                    break;
                }
            }

            if (!isSpellNameExists) {
                Integer spellId = SpellNameTableService.INSTANCE.getSpellId(originalScrollName);
                result.add(new ObjectTuple(originalScrollName, spellId));
            }
        }

        return result;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellScrollsPresenter.class;
    }

    @Override
    protected String getIconPath() {
        return "/images/spells_and_scrolls/";
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
