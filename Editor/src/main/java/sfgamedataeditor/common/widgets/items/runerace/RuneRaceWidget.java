package sfgamedataeditor.common.widgets.items.runerace;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.*;

public class RuneRaceWidget extends AbstractWidget<RuneRaceWidgetListener> {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> raceNameComboBox;

    private static final Map<Integer, String> runeRaceMap = new TreeMap<Integer, String>() {{
        put(0, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.player"));
        put(1, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"));
        put(2, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"));
        put(3, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"));
        put(4, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"));
        put(5, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"));
        put(6, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"));
    }};

    public RuneRaceWidget() {
        List<String> names = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : runeRaceMap.entrySet()) {
            names.add(entry.getValue());
        }

        Collections.sort(names);

        for (String name : names) {
            raceNameComboBox.addItem(name);
        }

        add(getMainPanel());
    }

    @Override
    protected void insertListener(RuneRaceWidgetListener listener) {
        raceNameComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Integer getRaceIdByByName(String raceName) {
        for (Map.Entry<Integer, String> integerStringEntry : runeRaceMap.entrySet()) {
            if (integerStringEntry.getValue().equals(raceName)) {
                return integerStringEntry.getKey();
            }
        }

        return 0;
    }

    public String getRaceNameById(Integer raceId) {
        return runeRaceMap.get(raceId);
    }

    public JComboBox<String> getRaceNameComboBox() {
        return raceNameComboBox;
    }
}
