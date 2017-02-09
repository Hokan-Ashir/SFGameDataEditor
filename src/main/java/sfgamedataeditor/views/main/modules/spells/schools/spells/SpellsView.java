package sfgamedataeditor.views.main.modules.spells.schools.spells;

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

public class SpellsView extends AbstractModulesView {

    private static final Logger LOGGER = Logger.getLogger(SpellsView.class);

    public SpellsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    @Override
    protected Image getPanelImageByPanelName(String panelName) {
        String spellNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.SPELLS_GUI);
        if (spellNameKey == null) {
            return null;
        }

        spellNameKey = spellNameKey.replaceAll("(.*)\\.name", "$1");

        try {
            URL resource = getClass().getResource("/images/spells_and_scrolls/" + spellNameKey + ".png");
            if (resource == null) {
                return null;
            }

            return ImageIO.read(resource);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
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
