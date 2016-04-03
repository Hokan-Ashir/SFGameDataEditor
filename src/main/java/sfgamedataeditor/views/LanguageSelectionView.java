package sfgamedataeditor.views;

import sfgamedataeditor.dataextraction.XMLExtractor;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

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
        ViewTools.centerFrame(frame);

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
                    // TODO show "no such locale detected, english locale will be used by default"
                    locale = Locale.getDefault();
                } else {
                    locale = new Locale(countryLanguage);
                }
                I18N.loadBundleMessages("messages", locale);
                initializeDefaultSwingComponentsI18N();
                XMLExtractor.setConfigurationXml(view.getLanguageToFileMap().get(selectedItem));

                FileSelectionView.showFileSelectionView();
                frame.dispose();
            }
        });
    }

    private static void initializeDefaultSwingComponentsI18N() {
        List<String> componentPropertyList = new ArrayList<String>() {{
            add("FileChooser.openDialogTitleText");
            add("FileChooser.saveDialogTitleText");
            add("FileChooser.lookInLabelText");
            add("FileChooser.saveInLabelText");
            add("FileChooser.upFolderToolTipText");
            add("FileChooser.homeFolderToolTipText");
            add("FileChooser.newFolderToolTipText");
            add("FileChooser.listViewButtonToolTipText");
            add("FileChooser.detailsViewButtonToolTipText");
            add("FileChooser.fileNameHeaderText");
            add("FileChooser.fileSizeHeaderText");
            add("FileChooser.fileTypeHeaderText");
            add("FileChooser.fileDateHeaderText");
            add("FileChooser.fileAttrHeaderText");
            add("FileChooser.fileNameLabelText");
            add("FileChooser.filesOfTypeLabelText");
            add("FileChooser.openButtonText");
            add("FileChooser.openButtonToolTipText");
            add("FileChooser.saveButtonText");
            add("FileChooser.saveButtonToolTipText");
            add("FileChooser.directoryOpenButtonText");
            add("FileChooser.directoryOpenButtonToolTipText");
            add("FileChooser.cancelButtonText");
            add("FileChooser.cancelButtonToolTipText");
            add("FileChooser.newFolderErrorText");
            add("FileChooser.acceptAllFileFilterText");
            add("OptionPane.yesButtonText");
            add("OptionPane.noButtonText");
            add("OptionPane.cancelButtonText");
            add("ProgressMonitor.progressText");
        }};

        for (String s : componentPropertyList) {
            UIManager.put(s, I18N.getMessage(s));
        }
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
