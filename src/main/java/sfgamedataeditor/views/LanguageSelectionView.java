package sfgamedataeditor.views;

import sfgamedataeditor.I18N;
import sfgamedataeditor.dataextraction.XMLExtractor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LanguageSelectionView {
    private static final String CONFIGURATION_XML_FILE_PREFIX = "configuration_";
    private static final String XML_FILE_EXTENSION = ".xml";

    private static final Map<String, String> LANGUAGE_FILE_MAP = new HashMap<String, String>() {{
        put("en", "English");
        put("ru", "Russian");
        put("fr", "French");
        put("de", "German");
    }};

    private Map<String, String> languageToFileMap = new HashMap<>();

    private JButton okButton;
    private JComboBox languageSelectionComboBox;
    private JLabel languageSelectionLabel;
    private JPanel mainPanel;

    public LanguageSelectionView() {
        generateUI();
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("SpellForce GameData.cff Editor : Language Selection Dialog");
        final LanguageSelectionView view = new LanguageSelectionView();
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        if (view.getLanguageSelectionComboBox().getItemCount() == 0) {
            ViewTools.repaintButtonTextContent(view.getOkButton(), frame, view.getMainPanel(), "No configuration files");
            view.getOkButton().setEnabled(false);
            view.getLanguageSelectionComboBox().setEnabled(false);
        }

        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = view.getLanguageSelectionComboBox().getSelectedItem();
                String countryLanguage = null;
                for (Map.Entry<String, String> stringStringEntry : LANGUAGE_FILE_MAP.entrySet()) {
                    if (stringStringEntry.getValue().equals(selectedItem)) {
                        countryLanguage = stringStringEntry.getKey();
                    }
                }

                Locale locale;
                if (countryLanguage == null) {
                    locale = Locale.getDefault();
                } else {
                    locale = new Locale(countryLanguage);
                }
                I18N.loadBundleMessages("messages", locale);
                XMLExtractor.setConfigurationXml(view.getLanguageToFileMap().get(selectedItem));

                FileSelectionView.showFileSelectionView();
                frame.dispose();
            }
        });
    }

    private void generateUI() {
        File currentDirectory = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        File[] directoryListing = currentDirectory.listFiles();
        if (directoryListing == null) {
            return;
        }

        for (File child : directoryListing) {
            String name = child.getName().toLowerCase();
            if (!(name.startsWith(CONFIGURATION_XML_FILE_PREFIX)
                    && name.endsWith(XML_FILE_EXTENSION))) {
                continue;
            }

            int prefixNameIndex = name.lastIndexOf(CONFIGURATION_XML_FILE_PREFIX) + CONFIGURATION_XML_FILE_PREFIX.length();
            int extensionNameIndex = name.indexOf(XML_FILE_EXTENSION);
            String fileLanguageExtension = name.substring(prefixNameIndex, extensionNameIndex);
            String languageName = LANGUAGE_FILE_MAP.get(fileLanguageExtension);
            boolean notExistsInComboBox = ((DefaultComboBoxModel) languageSelectionComboBox.getModel()).getIndexOf(languageName) == -1;
            if (LANGUAGE_FILE_MAP.containsKey(fileLanguageExtension) && notExistsInComboBox) {
                languageSelectionComboBox.addItem(languageName);
                languageToFileMap.put(languageName, child.getName());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JComboBox getLanguageSelectionComboBox() {
        return languageSelectionComboBox;
    }

    public Map<String, String> getLanguageToFileMap() {
        return languageToFileMap;
    }
}
