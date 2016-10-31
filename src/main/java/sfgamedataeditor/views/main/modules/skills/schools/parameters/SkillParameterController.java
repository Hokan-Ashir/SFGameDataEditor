package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.database.tableservices.SkillParametersTableService;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.util.List;

public class SkillParameterController extends AbstractController<SkillParameterModel, SkillParameterView> {

    public SkillParameterController(Model<SkillParameterModel> model, SkillParameterView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        SkillParameterModelParameter parameter = getModel().getParameter().getParameter();
        fillPossibleSkillLevelsComboBox(parameter.getSkillSchoolId());
        setFieldsData(parameter.getSkillSchoolId(), parameter.getSkillLevel());
    }

    private void fillPossibleSkillLevelsComboBox(final int skillSchoolId) {
        final JComboBox<String> comboBox = getView().getLevelComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                List<SkillParameters> skillPossibleValues = SkillParametersTableService.INSTANCE.getSkillPossibleValues(skillSchoolId);
                for (SkillParameters skillPossibleValue : skillPossibleValues) {
                    comboBox.addItem(String.valueOf(skillPossibleValue.level));
                }
            }
        });
    }

    private void setFieldsData(int skillSchoolId, int skillLevel) {
        SkillParameters skillParameter = SkillParametersTableService.INSTANCE.getSkillParameter(skillSchoolId, skillLevel);
        if (skillParameter != null) {
            for (IDataField dataField : getView().getDataFields()) {
                dataField.mapValues(skillParameter);
            }
        }
    }
}
