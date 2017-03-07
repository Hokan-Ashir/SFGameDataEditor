package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellsView extends AbstractModulesView {

    public SpellsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        String spellNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.SPELLS_GUI);
        if (spellNameKey == null) {
            return null;
        }

        spellNameKey = spellNameKey.replaceAll("(.*)\\.name", "$1");

        String iconPath = "/images/spells_and_scrolls/" + spellNameKey + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellPresenter.class;
    }
}
