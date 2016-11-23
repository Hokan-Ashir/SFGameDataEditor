package sfgamedataeditor.common.widgets.combobox.level;

import sfgamedataeditor.common.widgets.combobox.AbstractComboBoxWidget;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class ComboBoxWidget extends AbstractComboBoxWidget<List<String>> {

    private static final int COMPONENTS_WIDTH = 60;
    private static final int COMPONENTS_HEIGHT = 50;

    public ComboBoxWidget(Field DTOField) {
        super(DTOField);
        setComponentsSize();
        getComboBox().addItemListener(new ComboBoxListener(getComboBox(), DTOField));
    }

    private void setComponentsSize() {
        Dimension dimension = new Dimension(COMPONENTS_WIDTH, COMPONENTS_HEIGHT);

        setComponentSize(getComboBox(), dimension);
        setComponentSize(getLabel(), dimension);
        setComponentSize(getMainPanel(), dimension);
    }

    private void setComponentSize(JComponent component, Dimension dimension) {
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);
    }

    @Override
    public void update(List<String> model) {
        SpellParameterModelParameter parameter = ((SpellParameterModel) model).getParameter();
        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(selectedSpellId);
        setSpellAvailableLevels(spellLevels, selectedLevel);
    }

    private void setSpellAvailableLevels(final Set<Integer> spellLevels, final int selectedLevel) {
        final JComboBox<String> comboBox = getComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                for (Integer spellLevel : spellLevels) {
                    comboBox.addItem(String.valueOf(spellLevel));
                }

                comboBox.setSelectedItem(String.valueOf(selectedLevel));
            }
        });
    }

    @Override
    public void updateI18N() {

    }
}
