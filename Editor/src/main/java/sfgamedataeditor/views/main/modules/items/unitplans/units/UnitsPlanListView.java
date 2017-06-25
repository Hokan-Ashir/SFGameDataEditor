package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class UnitsPlanListView extends AbstractModulesView {

    public UnitsPlanListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "moduleName"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitsPlanListPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/units/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
