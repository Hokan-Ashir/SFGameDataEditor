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

    private static final class F {
        private final Integer a;
        private final Integer b;

        public F(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public Integer getB() {
            return b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            F f = (F) o;

            if (a != null ? !a.equals(f.a) : f.a != null) return false;
            return b != null ? b.equals(f.b) : f.b == null;
        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "F{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    private LanguageSelectionView() {
        setUIFont(new FontUIResource("Arial", Font.PLAIN, 12));
        for (String s : LANGUAGE_FILE_MAP.values()) {
            languageSelectionComboBox.addItem(s);
        }

        F f = new F(5, 10);
        F f1 = new F(5, 10);
        F f2 = new F(5, 10);
        HashMap<F, F> map = new HashMap<>();
        map.put(f, f1);
        map.put(f1, f);
        map.put(f2, f);

        F f3 = new F(5, 10);
        System.out.printf(String.valueOf(map.get(f3)));
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
        ViewTools.centerFrame(frame);

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
