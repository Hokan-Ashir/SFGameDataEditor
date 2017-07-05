package sfgamedataeditor.views.main.modules.common.search;

import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentMapping;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.AbstractGhostTextListener;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestLootModelCreator;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersView;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

@SuppressWarnings("unused")
public class SearchView implements PresentableView {
    private static final int CREATURES_TYPE_OBJECT = 100;
    private static final Integer BUILDINGS_TYPE_OBJECT = 101;
    private static final Integer OBJECTS_TYPE_OBJECT = 102;
    private JPanel mainPanel;
    private JTextField searchField;

    private static final String FILTERING_PHRASE = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "filterPanel.text");
    private static final Long MAXIMUM_NUMBER_OF_SUGGESTIONS = 50L;
    private JComboBox<SearchTuple> suggestionsComboBox;
    private final DefaultComboBoxModel<SearchTuple> model = new DefaultComboBoxModel<>();
    private Map<Integer, Pair<ModelCreator, Class<? extends PresentableView>>> modelCreatorsMap = new HashMap<>();

    public SearchView() {
        new GhostText(searchField, FILTERING_PHRASE);
        setupAutoComplete(searchField, Collections.<String>emptyList());
        initializeModelCreatorMap();
    }

    private void initializeModelCreatorMap() {
        modelCreatorsMap.put(CREATURES_TYPE_OBJECT, new Pair<ModelCreator, Class<? extends PresentableView>>(new CreaturesModelCreator(), CreaturesParametersView.class));
        modelCreatorsMap.put(BUILDINGS_TYPE_OBJECT, new Pair<ModelCreator, Class<? extends PresentableView>>(new BuildingModelCreator(), BuildingsParametersView.class));
        modelCreatorsMap.put(OBJECTS_TYPE_OBJECT, new Pair<ModelCreator, Class<? extends PresentableView>>(new ChestLootModelCreator(), ChestParametersView.class));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SearchViewPresenter.class;
    }

    private class GhostText extends AbstractGhostTextListener {

        public GhostText(JTextField textfield, String ghostText) {
            super(textfield, ghostText);
        }

        @Override
        protected void invokeUpdateStateActions() {
            String text = searchField.getText();
            if (text.isEmpty()) {
                if (suggestionsComboBox != null) {
                    suggestionsComboBox.setPopupVisible(false);
                }
                return;
            }

            List<SearchTuple> suggestedElements = getSuggestedElements(text);
            setAdjusting(suggestionsComboBox, true);
            model.removeAllElements();
            for (SearchTuple suggestedElement : suggestedElements) {
                model.addElement(suggestedElement);
            }

            suggestionsComboBox.setPopupVisible(model.getSize() > 0);
            setAdjusting(suggestionsComboBox, false);
        }

        private List<SearchTuple> getSuggestedElements(String text) {
            List<SearchTuple> elements = getItemsSuggestions(text);

            if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                addCreaturesSuggestions(text, elements);
            }

            if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                addBuildingsSuggestions(text, elements);
            }

            if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                addObjectsSuggestions(text, elements);
            }

            Collections.sort(elements);
            return elements;
        }

        private void addBuildingsSuggestions(String text, List<SearchTuple> elements) {
            // TODO rewrite it, like as creatures
//            addI18nElementsSuggestions(text, elements, BUILDINGS_TYPE_OBJECT, I18NTypes.BUILDING_NAMES_MAPPING);
        }

        private void addObjectsSuggestions(String text, List<SearchTuple> elements) {
            addI18nElementsSuggestions(text, elements, OBJECTS_TYPE_OBJECT, I18NTypes.OBJECTS);
        }

        private void addI18nElementsSuggestions(String text, List<SearchTuple> elements, Integer objectType, I18NTypes I18NKey) {
            Set<String> keySet = I18NService.INSTANCE.getBundle(I18NKey).keySet();
            for (String key : keySet) {
                String elementName = I18NService.INSTANCE.getMessage(I18NKey, key);
                if (elementName.contains(text)) {
                    elements.add(new SearchTuple(objectType, elementName, Integer.valueOf(key)));
                }

                if (elements.size() >= MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                    return;
                }
            }
        }

        private void addCreaturesSuggestions(String text, List<SearchTuple> elements) {
            List<ObjectTuple> objects = CreatureCommonParametersTableService.INSTANCE.getCreaturesNameIdPairByItemNamePart(text, MAXIMUM_NUMBER_OF_SUGGESTIONS - elements.size());
            for (ObjectTuple object : objects) {
                elements.add(new SearchTuple(CREATURES_TYPE_OBJECT, object.getName(), object.getObjectId()));
            }
        }

        private List<SearchTuple> getItemsSuggestions(String text) {
            List<SearchTuple> elements = new ArrayList<>();
            List<Integer> typesWithParameters = EquipmentMapping.INSTANCE.getItemTypesWithParameters();
            for (Integer type : typesWithParameters) {
                if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                    List<ObjectTuple> objects = ItemPriceParametersTableService.INSTANCE.getItemNameIdPairByItemNamePart(text, MAXIMUM_NUMBER_OF_SUGGESTIONS - elements.size(), type);
                    for (ObjectTuple object : objects) {
                        elements.add(new SearchTuple(type, object.getName(), object.getObjectId()));
                    }
                } else {
                    break;
                }
            }

            return elements;
        }
    }

    // taken from http://twaver.blogspot.ru/2012/07/add-function-autocompletein-jtextfield.html
    private void setupAutoComplete(final JTextField textField, final List<String> items) {
        suggestionsComboBox = new JComboBox<SearchTuple>(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(suggestionsComboBox, false);

        suggestionsComboBox.setSelectedItem(null);
        suggestionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(suggestionsComboBox)) {
                    if (suggestionsComboBox.getSelectedItem() != null) {
                        textField.setText(suggestionsComboBox.getSelectedItem().toString());
                    }
                }
            }
        });

        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(suggestionsComboBox, true);
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(suggestionsComboBox);
                    suggestionsComboBox.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        SearchTuple selectedItem = (SearchTuple) suggestionsComboBox.getSelectedItem();
                        if (selectedItem != null) {
                            textField.setText(selectedItem.toString());
                            suggestionsComboBox.setPopupVisible(false);

                            openObjectParametersView(selectedItem);
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    suggestionsComboBox.setPopupVisible(false);
                }

                setAdjusting(suggestionsComboBox, false);
            }

            private void openObjectParametersView(SearchTuple selectedItem) {
                Integer elementId = selectedItem.getElementId();
                Class<? extends PresentableView> parametersClass = EquipmentMapping.INSTANCE.getItemParametersViewClassByTypeId(selectedItem.getElementType());
                Model model;
                if (parametersClass != null) {
                    model = EquipmentMapping.INSTANCE.createModel(elementId);
                } else {
                    Pair<ModelCreator, Class<? extends PresentableView>> pair = modelCreatorsMap.get(selectedItem.getElementType());
                    parametersClass = pair.getValue();
                    model = pair.getKey().createModel(elementId, null);
                }

                EventProcessor.INSTANCE.process(new ShowContentViewEvent(parametersClass, model));
            }
        });

        textField.setLayout(new BorderLayout());
        textField.add(suggestionsComboBox, BorderLayout.SOUTH);
    }

    private void setAdjusting(JComboBox suggestComboBox, boolean adjusting) {
        suggestComboBox.putClientProperty("is_adjusting", adjusting);
    }

    private boolean isAdjusting(JComboBox suggestComboBox) {
        if (suggestComboBox.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) suggestComboBox.getClientProperty("is_adjusting");
        }
        return false;
    }
}
