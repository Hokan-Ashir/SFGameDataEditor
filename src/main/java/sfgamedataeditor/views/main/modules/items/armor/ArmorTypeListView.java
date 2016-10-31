package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;

public class ArmorTypeListView extends AbstractModulesView {

    public ArmorTypeListView() {
        super(I18N.INSTANCE.getMessage("armorTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

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
        return null;
    }
}
