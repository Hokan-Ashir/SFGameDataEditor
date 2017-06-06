package sfgamedataeditor.views;

import org.apache.log4j.Logger;
import sfgamedataeditor.views.fileselection.FileSelectionView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

@SuppressWarnings("unused")
public class LanguageSelectionView {

    private static final Logger LOGGER = Logger.getLogger(LanguageSelectionView.class);

    private static final Map<String, String> LANGUAGE_FILE_MAP = new TreeMap<String, String>() {{
        put("en", "English");
        put("ru", "Russian");
        put("fr", "French");
        put("de", "German");
    }};

    private JButton okButton;
    private JComboBox<String> languageSelectionComboBox;
    private JLabel languageSelectionLabel;
    private JPanel mainPanel;

    private LanguageSelectionView() {
        setUIFont(new FontUIResource("Arial", Font.PLAIN, 12));
        for (String s : LANGUAGE_FILE_MAP.values()) {
            languageSelectionComboBox.addItem(s);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            LOGGER.error(e.getMessage(), e);
        }

        final JFrame frame = new JFrame("SpellForce GameData.cff Editor : Language Selection Dialog");
        final LanguageSelectionView view = new LanguageSelectionView();
        frame.setResizable(false);
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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

                I18NService.INSTANCE.loadBundleMessages(locale);
                initializeDefaultSwingComponentsI18N();

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
            UIManager.put(s, I18NService.INSTANCE.getMessage(I18NTypes.COMMON, s));
        }
    }

    private void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    private JPanel getMainPanel() {
        return mainPanel;
    }

    private JButton getOkButton() {
        return okButton;
    }

    private JComboBox getLanguageSelectionComboBox() {
        return languageSelectionComboBox;
    }
}
