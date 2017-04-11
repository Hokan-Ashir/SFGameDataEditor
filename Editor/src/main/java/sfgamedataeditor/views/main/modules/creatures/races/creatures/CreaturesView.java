package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class CreaturesView extends AbstractModulesView {

    public CreaturesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "moduleName"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return CreaturesPresenter.class;
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(panelName);
        String iconPath = "/images/creatures/" + creatureId + ".png";
        ImageIcon imageIcon = ImageIconsCache.INSTANCE.getImageIcon(iconPath);
        if (imageIcon == null) {
            // try to get merchant images, cause they are also creatures
            iconPath = "/images/merchants/" + creatureId + ".png";
            return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
        } else {
            return imageIcon;
        }
    }
}
