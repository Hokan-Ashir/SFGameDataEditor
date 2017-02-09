package sfgamedataeditor.views.main.modules.units.races.units;

import org.apache.log4j.Logger;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class UnitListView extends AbstractModulesView {

    private static final Logger LOGGER = Logger.getLogger(UnitListView.class);

    public UnitListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "units"));
    }

    @Override
    protected void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitListPresenter.class;
    }

    @Override
    protected Image getPanelImageByPanelName(String panelName) {
        String unitNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.CREATURES);
        if (unitNameKey == null) {
            return null;
        }

        try {
            URL resource = getClass().getResource("/images/units/" + unitNameKey + ".png");
            if (resource == null) {
                return null;
            }

            return ImageIO.read(resource);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
