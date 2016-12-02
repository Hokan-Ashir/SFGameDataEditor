package sfgamedataeditor.database.creatures.skills;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureSkillTableService {
    INSTANCE;

    public void createCreatureSkillParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureSkillObject.class);
    }

    public void addRecordsToCreatureSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureSkillObject.class, offsettedData);
    }
}
