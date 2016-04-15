package sfgamedataeditor.views;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class LanguageSelectionView {
    private static final Map<String, String> LANGUAGE_FILE_MAP = new TreeMap<String, String>() {{
        put("en", "English");
        put("ru", "Russian");
        put("fr", "French");
        put("de", "German");
    }};

    private JButton okButton;
    private JComboBox languageSelectionComboBox;
    private JLabel languageSelectionLabel;
    private JPanel mainPanel;

    public LanguageSelectionView() {
        for (String s : LANGUAGE_FILE_MAP.values()) {
            languageSelectionComboBox.addItem(s);
        }
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
                I18N.INSTANCE.loadBundleMessages("messages", locale);
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
            UIManager.put(s, I18N.INSTANCE.getMessage(s));
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
}
