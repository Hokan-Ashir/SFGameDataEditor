package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;

public class CreaturesView extends AbstractModulesView {

    public CreaturesView() {
        // TODO set i18n value
        super("creatures");
    }

    @Override
    protected void fillComboBoxMapping() {

    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return CreaturesController.class;
    }
}
