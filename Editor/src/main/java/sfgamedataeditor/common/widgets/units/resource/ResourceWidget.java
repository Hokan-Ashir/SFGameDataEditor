package sfgamedataeditor.common.widgets.units.resource;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceWidget extends AbstractWidget<ResourceWidgetListener> {
    private static final Map<Integer, String> RESOURCES_NAME_NUMBER_MAPPING = new HashMap<>();

    private JPanel mainPanel;
    private JComboBox<String> resourceTypeComboBox;
    private JTextField resourceAmountTextField;

    public ResourceWidget() {
        add(getMainPanel());
        initializeResourcesNameNumberMapping();
        initializeResourcesTypesComboBox();
    }

    private void initializeResourcesTypesComboBox() {
        for (Map.Entry<Integer, String> entry : RESOURCES_NAME_NUMBER_MAPPING.entrySet()) {
            resourceTypeComboBox.addItem(entry.getValue());
        }
    }

    private void initializeResourcesNameNumberMapping() {
        //    01 - wood
        //    02 - stone
        //    03 - log
        //    04 - moonsilver
        //    05 - food
        //    06 - berry
        //    07 - iron
        //    08 - tree
        //    09 - grain
        //    0B - fish
        //    0F - mushroom
        //    10 - meat
        //    12 - aria
        //    13 - lenya
        RESOURCES_NAME_NUMBER_MAPPING.put(1, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.wood"));
        RESOURCES_NAME_NUMBER_MAPPING.put(2, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.stone"));
        RESOURCES_NAME_NUMBER_MAPPING.put(3, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.log"));
        RESOURCES_NAME_NUMBER_MAPPING.put(4, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.moonsilver"));
        RESOURCES_NAME_NUMBER_MAPPING.put(5, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.food"));
        RESOURCES_NAME_NUMBER_MAPPING.put(6, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.berry"));
        RESOURCES_NAME_NUMBER_MAPPING.put(7, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.iron"));
        RESOURCES_NAME_NUMBER_MAPPING.put(8, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.tree"));
        RESOURCES_NAME_NUMBER_MAPPING.put(9, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.grain"));
        RESOURCES_NAME_NUMBER_MAPPING.put(11, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.fish"));
        RESOURCES_NAME_NUMBER_MAPPING.put(15, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.mushroom"));
        RESOURCES_NAME_NUMBER_MAPPING.put(16, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.meat"));
        RESOURCES_NAME_NUMBER_MAPPING.put(18, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.aria"));
        RESOURCES_NAME_NUMBER_MAPPING.put(19, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "resource.type.lenya"));
    }

    public JTextField getResourceAmountTextField() {
        return resourceAmountTextField;
    }

    public JComboBox<String> getResourceTypeComboBox() {
        return resourceTypeComboBox;
    }

    public String getResourceTypeNameByResourceId(int resourceId) {
        return RESOURCES_NAME_NUMBER_MAPPING.get(resourceId);
    }

    public Integer getResourceIdByName(String resourceName) {
        for (Map.Entry<Integer, String> entry : RESOURCES_NAME_NUMBER_MAPPING.entrySet()) {
            if (entry.getValue().equals(resourceName)) {
                return entry.getKey();
            }
        }

        return 0;
    }

    @Override
    protected void insertListener(ResourceWidgetListener listener) {
        resourceAmountTextField.getDocument().addDocumentListener(listener);
        resourceTypeComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
