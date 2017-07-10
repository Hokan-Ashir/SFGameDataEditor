package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModelParameter;

import javax.swing.*;

public class MiscellaneousModelCreator implements ModelCreator<MiscellaneousParametersModel> {

    @Override
    public MiscellaneousParametersModel createModel(int objectId) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        MiscellaneousParametersModelParameter parameter = new MiscellaneousParametersModelParameter(itemPriceObject, icon);
        return new MiscellaneousParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "";
    }
}
