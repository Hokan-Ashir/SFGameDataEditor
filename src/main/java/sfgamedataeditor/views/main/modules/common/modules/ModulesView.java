package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.units.UnitsRacesView;

public class ModulesView extends AbstractModulesView {

    public ModulesView() {
        super(I18N.INSTANCE.getMessage("modulesList"));
    }

    @Override
    protected Model createModel() {
        return new Model();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18N.INSTANCE.getMessage("skills"), SkillSchoolsView.class);
        addMapping(I18N.INSTANCE.getMessage("spells"), SpellSchoolsView.class);
        addMapping(I18N.INSTANCE.getMessage("merchants"), MerchantLocationsView.class);
        addMapping(I18N.INSTANCE.getMessage("buildings"), BuildingRacesView.class);
        addMapping(I18N.INSTANCE.getMessage("units"), UnitsRacesView.class);
        addMapping(I18N.INSTANCE.getMessage("items"), ItemTypesView.class);
    }

    @Override
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideNavigationPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ModulesController.class;
    }
}
