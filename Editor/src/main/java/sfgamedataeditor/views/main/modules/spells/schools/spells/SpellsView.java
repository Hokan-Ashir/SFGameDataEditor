package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellsView extends AbstractModulesView {

    public SpellsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    @Override
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/spells_and_scrolls/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellPresenter.class;
    }
}
