package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.mvc.ShowViewDispatcher;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

public class SpellParameterView implements RenderableView {

    private final SpellParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    // TODO get rid of this as private variable
    private LevelableView<SpellsView> view = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(LevelableView.class);

    public SpellParameterView() {
        stub = new SpellParameterViewStub();
        dataFields = FieldsWrapperCreator.createFieldWrappers(stub);
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                // TODO set values
                SpellParameterModelParameter parameter = new SpellParameterModelParameter(0, 0);
                SpellParameterModel model = new SpellParameterModel(parameter);
                ShowViewDispatcher.INSTANCE.showView(SpellParameterView.class, model);
            }
        });
    }

    public SpellParameterViewStub getStub() {
        return stub;
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
        return SpellParameterController.class;
    }
}
