package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.database.tableservices.SkillParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class SkillParameterController extends AbstractController<SkillParameterModelParameter, SkillParameterView> {

    public SkillParameterController(SkillParameterView view) {
        super(view);
        getView().getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                String selectedItem = (String) getView().getLevelComboBox().getSelectedItem();
                if (selectedItem == null) {
                    return;
                }

                Model<SkillParameterModelParameter> model = getModel();
                model.getParameter().setSkillLevel(Integer.valueOf(selectedItem));
                ShowContentViewEvent event = new ShowContentViewEvent(SkillParameterView.class, model);
                EventProcessor.INSTANCE.process(event);
            }
        });
    }

    @Override
    public void updateView() {
        SkillParameterModelParameter parameter = getModel().getParameter();
        fillPossibleSkillLevelsComboBox(parameter);
        setFieldsData(parameter.getSkillSchoolId(), parameter.getSkillLevel());
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unrenderViewInsideContentPanel(getView());
    }

    private void fillPossibleSkillLevelsComboBox(final SkillParameterModelParameter parameter) {
        final JComboBox<String> comboBox = getView().getLevelComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                List<SkillParameters> skillPossibleValues = SkillParametersTableService.INSTANCE.getSkillPossibleValues(parameter.getSkillSchoolId());
                for (SkillParameters skillPossibleValue : skillPossibleValues) {
                    comboBox.addItem(String.valueOf(skillPossibleValue.level));
                }
                comboBox.setSelectedItem(String.valueOf(parameter.getSkillLevel()));
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
