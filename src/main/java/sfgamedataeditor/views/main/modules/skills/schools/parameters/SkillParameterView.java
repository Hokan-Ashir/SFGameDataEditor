package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.mvc.ShowViewDispatcher;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

public class SkillParameterView implements RenderableView {

    private final SkillParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    // TODO merge levelView into SkillParameterView
    private final LevelableView<SkillSchoolsView> view = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(LevelableView.class);
    private final SkillSchoolsView skillSchoolsView = (SkillSchoolsView) ViewRegister.INSTANCE.getView(SkillSchoolsView.class);

    public SkillParameterView() {
        this.stub = new SkillParameterViewStub();
        this.dataFields = FieldsWrapperCreator.createFieldWrappers(stub);

        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                // TODO set parameters
                SkillParameterModelParameter parameter = new SkillParameterModelParameter(0, 0);
                SkillParameterModel model = new SkillParameterModel(parameter);
                ShowViewDispatcher.INSTANCE.showView(SkillParameterView.class, model);
            }
        });
    }

    public Collection<IDataField> getDataFields() {
        return dataFields;
    }

    public JComboBox<String> getLevelComboBox() {
        return view.getLevelComboBox();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }

    @Override
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SkillParameterController.class;
    }
}
