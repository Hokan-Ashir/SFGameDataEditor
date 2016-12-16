package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class MerchantsView extends AbstractModulesView {

    private static final Integer MERCHANTS_RACE_ID = 168;

    public MerchantsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        List<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(MERCHANTS_RACE_ID);
        for (String creatureName : creatureNames) {
            addMapping(creatureName, MerchantInventoryView.class);
        }
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MerchantsController.class;
    }
}
