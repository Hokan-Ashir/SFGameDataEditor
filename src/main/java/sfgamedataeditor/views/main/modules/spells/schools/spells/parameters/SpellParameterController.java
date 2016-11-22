package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class SpellParameterController extends AbstractController<SpellParameterModelParameter, SpellParameterView> {

    private static final Logger LOGGER = Logger.getLogger(SpellParameterController.class);

    public SpellParameterController(SpellParameterView view) {
        super(view);
//        getView().getLevelComboBox().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() != ItemEvent.SELECTED) {
//                    return;
//                }
//
//                String selectedItem = (String) getView().getLevelComboBox().getSelectedItem();
//                if (selectedItem == null) {
//                    return;
//                }
//
//                Model<SpellParameterModelParameter> model = getModel();
//                model.getParameter().setSpellLevel(Integer.valueOf(selectedItem));
//                ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
//                EventProcessor.INSTANCE.process(event);
//            }
//        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        SpellParameterModelParameter parameter = getModel().getParameter();
        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(selectedSpellId);
        setSpellAvaliableLevels(spellLevels, selectedLevel);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        selectedLevel = adjustSelectedLevel(selectedLevel, spellMinLevel, spellMaxLevel);
        SpellParameters spellParameter = SpellParametersTableService.INSTANCE.getSpellParameter(selectedSpellId, selectedLevel);
//        for (IDataField dataField : getView().getDataFields()) {
//            dataField.mapValues(spellParameter);
//        }

        setSpellParameterLabelNames();
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

    private void setSpellAvaliableLevels(final Set<Integer> spellLevels, final int selectedLevel) {
        final JComboBox<String> comboBox = getView().getLevelComboBox();
//        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
//            @Override
//            protected void setValues() {
//                comboBox.removeAllItems();
//                for (Integer spellLevel : spellLevels) {
//                    comboBox.addItem(String.valueOf(spellLevel));
//                }
//
//                comboBox.setSelectedItem(String.valueOf(selectedLevel));
//            }
//        });
    }

    private void setSpellParameterLabelNames() {
        SpellsView spellsView = ViewRegister.INSTANCE.getView(SpellsView.class);
        String selectedSpellName = spellsView.getSelectedModuleValue();
        SpellName spellName = SpellNameTableService.INSTANCE.getSpellName(selectedSpellName);
        for (Field field : getView().getClass().getDeclaredFields()) {
            MappedColumn annotation = field.getAnnotation(MappedColumn.class);
            if (annotation == null) {
                continue;
            }

            if (!annotation.daoClass().equals(SpellName.class)) {
                continue;
            }

            String mappedFieldName = annotation.name();
            String parameterName = getParameterName(spellName, mappedFieldName);
            try {
                field.setAccessible(true);
                JLabel label = ((JLabel) field.get(getView()));
                if (parameterName.equals(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage("spellParameterNotUsed")))) {
                    label.setVisible(false);
                    label.getLabelFor().setVisible(false);
                } else {
                    label.setVisible(true);
                    label.getLabelFor().setVisible(true);
                    label.setText(parameterName);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }

        getView().getMainPanel().repaint();
    }

    private String getParameterName(SpellName spellName, String fieldName) {
        try {
            Field field = spellName.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(spellName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }
}
