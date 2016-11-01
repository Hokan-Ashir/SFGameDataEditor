package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.ControllableView;

import javax.swing.*;
import java.util.Collection;

public class SpellParameterView implements ControllableView {

    private final SpellParameterViewStub stub;
    private final Collection<IDataField> dataFields;

    public SpellParameterView() {
        stub = new SpellParameterViewStub();
        dataFields = FieldsWrapperCreator.createFieldWrappers(stub);
    }

    public SpellParameterViewStub getStub() {
        return stub;
    }

    public Collection<IDataField> getDataFields() {
        return dataFields;
    }

    public JComboBox<String> getLevelComboBox() {
        return getStub().getLevelComboBox();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellParameterController.class;
    }
}
