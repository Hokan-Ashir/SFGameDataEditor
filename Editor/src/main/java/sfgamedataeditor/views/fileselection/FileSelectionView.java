package sfgamedataeditor.views.fileselection;

import org.apache.log4j.Logger;
import sfgamedataeditor.views.main.modules.common.localization.LocalizationService;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

@SuppressWarnings("unused")
public class FileSelectionView {

    private JPanel mainPanel;
    private JTextField originalFileField;
    private JLabel originalFileLabel;
    private JButton originalFileSelectorButton;
    private JLabel modificationFileLabel;
    private JTextField modificationFileField;
    private JButton modificationFileSelectorButton;
    private JButton okButton;

    private static final Logger LOGGER = Logger.getLogger(FileSelectionView.class);

    private FileSelectionView() {
        originalFileLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fileSelectionWindowOriginalFileTextFieldCaption"));
        modificationFileLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fileSelectionWindowModificationFileTextFieldCaption"));
        okButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "ok"));
    }

    public static void main(String[] args) {
        setUIFont(new FontUIResource("Arial", Font.PLAIN, 12));

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LocalizationService.INSTANCE.initializeLocalization();

        final JFrame frame = new JFrame(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fileSelectionWindowCaption"));
        final FileSelectionView view = new FileSelectionView();
        frame.setResizable(false);
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        view.getOkButton().setEnabled(false);
        view.getOkButton().addActionListener(new UploadDataButtonListener(frame, view));
        view.getOriginalFileSelectorButton().addActionListener(new OriginalFileSelectorListener(view));
        view.getModificationFileSelectorButton().addActionListener(new ModificationFileSelectorListener(view));
    }

    private static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    JButton getOkButton() {
        return okButton;
    }

    private JButton getOriginalFileSelectorButton() {
        return originalFileSelectorButton;
    }

    private JButton getModificationFileSelectorButton() {
        return modificationFileSelectorButton;
    }

    JTextField getOriginalFileField() {
        return originalFileField;
    }

    JTextField getModificationFileField() {
        return modificationFileField;
    }
}
