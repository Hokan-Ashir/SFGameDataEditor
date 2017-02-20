package sfgamedataeditor.common.viewconfigurations;

import org.apache.log4j.Logger;
import sfgamedataeditor.mvc.objects.Model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfigurationHolder {

    private static final Logger LOGGER = Logger.getLogger(AbstractConfigurationHolder.class);
    private final Map<Model, AbstractConfiguration> configurationMap = new HashMap<>();
    private AbstractConfiguration currentConfiguration;

    protected AbstractConfigurationHolder() {
        fillConfigurationMapping();
    }

    protected abstract void fillConfigurationMapping();

    protected void addConfiguration(Model model, AbstractConfiguration configuration) {
        configurationMap.put(model, configuration);
    }

    public AbstractConfiguration getConfiguration(Model model) {
        AbstractConfiguration configuration = null;
        for (Map.Entry<Model, AbstractConfiguration> modelAbstractConfigurationEntry : configurationMap.entrySet()) {
            Model key = modelAbstractConfigurationEntry.getKey();
            if (isModelsAreEqual(key, model)) {
                configuration = modelAbstractConfigurationEntry.getValue();
                break;
            }
        }

        if (configuration != null) {
            return configuration;
        }

        AbstractConfiguration defaultConfiguration = configurationMap.get(null);
        if (defaultConfiguration != null) {
            return defaultConfiguration;
        }

        return null;
    }

    // TODO add also exact model matching, currently if any parameter equal
    private boolean isModelsAreEqual(Model configurationModel, Model incomingModel) {
        if ((configurationModel == null && incomingModel != null)
                || (configurationModel != null && incomingModel == null)) {
            return false;
        }

        Object configurationModelParameter = configurationModel.getParameter();
        Object incomingModelParameter = incomingModel.getParameter();
        if (!configurationModelParameter.getClass().equals(incomingModelParameter.getClass())) {
            return false;
        }

        Field[] declaredFields = configurationModelParameter.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);

                Object configurationModelValue = declaredField.get(configurationModelParameter);
                Object incomingModelValue = declaredField.get(incomingModelParameter);
                if (configurationModelValue != null && configurationModelValue.equals(incomingModelValue)) {
                    return true;
                }

            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }
        }

        return false;
    }

    public AbstractConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void setCurrentConfiguration(AbstractConfiguration currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    }
}
