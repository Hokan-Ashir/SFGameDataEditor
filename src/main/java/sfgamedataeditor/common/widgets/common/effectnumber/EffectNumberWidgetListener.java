package sfgamedataeditor.common.widgets.common.effectnumber;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.spells.names.SpellNameObject;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class EffectNumberWidgetListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener, ActionListener {

    private static final Logger LOGGER = Logger.getLogger(EffectNumberWidgetListener.class);

    public EffectNumberWidgetListener(EffectNumberWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        SpellParametersObject selectedSpell = getSelectedSpellParameterObject();
        return new int[] {selectedSpell.spellNumber};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int spellNumber = value[0];
        SpellParametersObject spellParametersObject = SpellParametersTableService.INSTANCE.getSpellParametersBySpellNumber(spellNumber);
        setSelectedSpellName(spellParametersObject);
        setSelectedSpellLevel(spellParametersObject);
    }

    private void setSelectedSpellName(SpellParametersObject spellParametersObject) {
        String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
        final JComboBox<String> spellNameComboBox = getWidget().getSpellNameComboBox();
        ComboBoxModel<String> comboBoxModel = spellNameComboBox.getModel();
        int size = comboBoxModel.getSize();
        for (int i = 0; i < size; ++i) {
            String element = comboBoxModel.getElementAt(i);
            if (element.equals(spellName)) {
                final int finalI = i;
                ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellNameComboBox) {
                    @Override
                    protected void setValues() {
                        spellNameComboBox.setSelectedItem(spellNameComboBox.getItemAt(finalI));
                    }
                });
                break;
            }
        }
    }

    private void setSelectedSpellLevel(SpellParametersObject spellParametersObject) {
        final Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellParametersObject.spellNameId);
        final JComboBox<String> spellLevelComboBox = getWidget().getSpellLevelComboBox();
        setPossibleSpellLevels(spellLevels, spellLevelComboBox);

        final Object selectedItem = getSelectedSpellLevel(spellParametersObject, (TreeSet) spellLevels, spellLevelComboBox);

        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                spellLevelComboBox.setSelectedItem(selectedItem);
            }
        });
    }

    private Object getSelectedSpellLevel(SpellParametersObject spellParametersObject, TreeSet spellLevels, JComboBox<String> spellLevelComboBox) {
        Integer requirementLevel1 = spellParametersObject.requirementLevel1;
        Integer requirementLevel2 = spellParametersObject.requirementLevel2;
        Integer requirementLevel3 = spellParametersObject.requirementLevel3;

        int spellMinLevel = (int) spellLevels.first();
        final Object selectedItem;
        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
            if (requirementLevel1 != 0) {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel1 - spellMinLevel);
            } else if (requirementLevel2 != 0) {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel2 - spellMinLevel);
            } else {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel3 - spellMinLevel);
            }
        } else {
            selectedItem = spellLevelComboBox.getItemAt(0);
        }
        return selectedItem;
    }

    private void setPossibleSpellLevels(final Set<Integer> spellLevels, final JComboBox<String> spellLevelComboBox) {
        spellLevelComboBox.removeAllItems();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                for (Integer spellLevel : spellLevels) {
                    spellLevelComboBox.addItem(String.valueOf(spellLevel));
                }
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        setWidgetValueToDTOField();
    }

    private Integer getSelectedSpellLevel(SpellParametersObject spellParametersObject) {
        Integer requirementLevel1 = spellParametersObject.requirementLevel1;
        Integer requirementLevel2 = spellParametersObject.requirementLevel2;
        Integer requirementLevel3 = spellParametersObject.requirementLevel3;

        int spellLevel;
        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
            if (requirementLevel1 != 0) {
                spellLevel = requirementLevel1;
            } else if (requirementLevel2 != 0) {
                spellLevel = requirementLevel2;
            } else {
                spellLevel = requirementLevel3;
            }
        } else {
            spellLevel = 0;
        }


        return spellLevel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SpellParametersObject selectedSpellParameterObject = getSelectedSpellParameterObject();
        Integer selectedSpellLevel = getSelectedSpellLevel(selectedSpellParameterObject);
        Integer selectedSpellNameId = selectedSpellParameterObject.spellNameId;

        String selectedSpellName = (String) getWidget().getSpellNameComboBox().getSelectedItem();
        Icon icon = getSpellIcon(selectedSpellName);
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(selectedSpellNameId, selectedSpellLevel, icon);
        SpellParameterModel model = new SpellParameterModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private Icon getSpellIcon(String selectedSpellName) {
        String spellNameKey = ViewTools.getKeyStringByPropertyValue(selectedSpellName, I18NTypes.SPELLS_GUI);
        if (spellNameKey == null) {
            return null;
        }

        spellNameKey = spellNameKey.replaceAll("(.*)\\.name", "$1");

        try {
            URL resource = getClass().getResource("/images/spells_and_scrolls/" + spellNameKey + ".png");
            if (resource == null) {
                return null;
            }

            return new ImageIcon(ImageIO.read(resource));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private SpellParametersObject getSelectedSpellParameterObject() {
        String spellName = (String) getWidget().getSpellNameComboBox().getSelectedItem();
        SpellNameObject spellNameObject = SpellNameTableService.INSTANCE.getSpellName(spellName);

        String spellLevel = (String) getWidget().getSpellLevelComboBox().getSelectedItem();
        return SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(spellNameObject.spellType, Integer.parseInt(spellLevel));
    }
}
