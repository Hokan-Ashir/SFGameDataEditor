package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModelParameter;

import javax.swing.*;

public class UnitPlansModelCreator implements ModelCreator<UnitsPlansParametersModel> {
    @Override
    public UnitsPlansParametersModel createModel(int objectId) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        UnitsPlansParametersModelParameter parameter = new UnitsPlansParametersModelParameter(itemPriceObject, icon);
        return new UnitsPlansParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "/images/units/";
    }
}
