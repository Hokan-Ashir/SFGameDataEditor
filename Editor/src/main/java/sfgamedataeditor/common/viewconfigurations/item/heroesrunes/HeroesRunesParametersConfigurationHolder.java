package sfgamedataeditor.common.viewconfigurations.item.heroesrunes;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class HeroesRunesParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new HeroesRunesParametersViewConfiguration());
    }
}
