package sfgamedataeditor.common.viewconfigurations.player.level.stats;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class PlayerLevelStatsConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new PlayerLevelStatsViewConfiguration());
    }
}
