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
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
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
    private static final int CREATURES_TYPE_OBJECT = 0;
    private JPanel mainPanel;
    private JTextField searchField;

    private static final String FILTERING_PHRASE = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "filterPanel.text");
    private static final Long MAXIMUM_NUMBER_OF_SUGGESTIONS = 10L;
    private JComboBox<SearchTuple> suggestionsComboBox;
    private final DefaultComboBoxModel<SearchTuple> model = new DefaultComboBoxModel<>();
    private Map<I18NTypes, Pair<ModelCreator, PresentableView>> map = new HashMap<>();

    public SearchView() {
        new GhostText(searchField, FILTERING_PHRASE);
        setupAutoComplete(searchField, Collections.<String>emptyList());
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

            suggestionsComboBox.setPopupVisible(model.getSize() > CREATURES_TYPE_OBJECT);
            setAdjusting(suggestionsComboBox, false);
        }

        private List<SearchTuple> getSuggestedElements(String text) {
            List<SearchTuple> elements = new ArrayList<>();
            if (getItemsSuggestions(text, elements)) {
                return elements;
            }

            if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                List<Pair<String, Integer>> objects = CreatureCommonParametersTableService.INSTANCE.getCreaturesNameIdPairByItemNamePart(text, MAXIMUM_NUMBER_OF_SUGGESTIONS - elements.size());
                for (Pair<String, Integer> object : objects) {
                    elements.add(new SearchTuple(CREATURES_TYPE_OBJECT, object.getKey(), object.getValue()));
                }
                return elements;
            } else {
                return elements;
            }
        }

        private boolean getItemsSuggestions(String text, List<SearchTuple> elements) {
            List<Integer> typesWithParameters = EquipmentMapping.INSTANCE.getItemTypesWithParameters();
            for (Integer type : typesWithParameters) {
                if (elements.size() < MAXIMUM_NUMBER_OF_SUGGESTIONS) {
                    List<Pair<String, Integer>> objects = ItemPriceParametersTableService.INSTANCE.getItemNameIdPairByItemNamePart(text, MAXIMUM_NUMBER_OF_SUGGESTIONS - elements.size(), type);
                    for (Pair<String, Integer> object : objects) {
                        elements.add(new SearchTuple(type, object.getKey(), object.getValue()));
                    }
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    // taken from http://twaver.blogspot.ru/2012/07/add-function-autocompletein-jtextfield.html
    private void setupAutoComplete(final JTextField textField, final List<String> items) {
        suggestionsComboBox = new JComboBox<SearchTuple>(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, CREATURES_TYPE_OBJECT);
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (suggestionsComboBox.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(suggestionsComboBox);
                    suggestionsComboBox.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        SearchTuple selectedItem = (SearchTuple) suggestionsComboBox.getSelectedItem();
                        textField.setText(selectedItem.toString());
                        suggestionsComboBox.setPopupVisible(false);

                        openObjectParametersView(selectedItem);
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
                Model model = null;
                if (parametersClass != null) {
                     model = EquipmentMapping.INSTANCE.createModel(elementId);
                } else if (selectedItem.getElementType().equals(CREATURES_TYPE_OBJECT)) {
                    parametersClass = CreaturesParametersView.class;
                    CreaturesModelCreator creator = new CreaturesModelCreator();
                    model = creator.createModel(elementId, null);
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
