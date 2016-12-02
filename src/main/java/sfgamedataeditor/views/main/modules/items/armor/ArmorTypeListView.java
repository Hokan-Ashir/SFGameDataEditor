package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.parameters.ArmorParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorTypeListView extends AbstractModulesView {

    public ArmorTypeListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "armorTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.helmets"), ArmorParametersView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.chest.armor"), ArmorParametersView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.robes"), ArmorParametersView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.legs.armor"), ArmorParametersView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.shield"), ArmorParametersView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor.rings"), ArmorParametersView.class);
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ArmorTypesController.class;
    }
}
