package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellsView extends AbstractModulesView {

    public SpellsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    @Override
    protected String getIconPath() {
        return "/images/spells_and_scrolls/";
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

    @Override
    public void localize() {

    }
}
