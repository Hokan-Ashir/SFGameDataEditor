package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
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

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SpellScrollsListView extends AbstractModulesView {

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
            String originalScrollName = scrollName.getName().replaceAll(",?(\\s+)?\\b(\\d+)?(\\s+)?(уровень|ур.|niveau|level|Stufe)(\\s+)?(\\d+)?", "");

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
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/spells_and_scrolls/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
