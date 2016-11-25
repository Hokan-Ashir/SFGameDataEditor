package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.mvc.objects.Model;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfigurationHolder {

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
        AbstractConfiguration configuration = configurationMap.get(model);
        if (configuration != null) {
            return configuration;
        }

        AbstractConfiguration defaultConfiguration = configurationMap.get(null);
        if (defaultConfiguration != null) {
            return defaultConfiguration;
        }

        return null;
    }

    public AbstractConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void setCurrentConfiguration(AbstractConfiguration currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    }
}
