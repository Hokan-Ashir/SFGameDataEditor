package sfgamedataeditor.common.widgets.common.text;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class TextFieldWidget extends AbstractWidget {
    private JPanel mainPanel;
    private JTextField field;
    private JLabel label;
    private TextFieldListener listener;

    public TextFieldWidget(Field DTOField) {
        super(DTOField);
        listener = new TextFieldListener(field, DTOField);
        field.getDocument().addDocumentListener(listener);
        add(getMainPanel());
    }

    @Override
    public void update(Model model) {
        SpellParameterModelParameter parameter = ((SpellParameterModel) model).getParameter();
        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(selectedSpellId);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        selectedLevel = adjustSelectedLevel(selectedLevel, spellMinLevel, spellMaxLevel);
        SpellParameters spellParameter = SpellParametersTableService.INSTANCE.getSpellParameter(selectedSpellId, selectedLevel);
        listener.mapValues(spellParameter);
    }

    // in case user selected spell with level-range [1; 12] with level 5
    // then selected spell with level range [13; 20]
    // we have to select level 13 to appropriately load correct data
    // and vice versa, if user selected spell with level range [13; 20]
    // and then selected spell with level range [1; 12]
    private int adjustSelectedLevel(int selectedLevel, int spellMinLevel, int spellMaxLevel) {
        if (selectedLevel <= spellMaxLevel && selectedLevel >= spellMinLevel) {
            return selectedLevel;
        } else {
            return spellMinLevel;
        }
    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setEnabled(boolean enabled) {
        field.setEnabled(enabled);
    }
}
