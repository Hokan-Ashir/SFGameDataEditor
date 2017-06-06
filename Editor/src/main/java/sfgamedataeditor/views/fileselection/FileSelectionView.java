package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

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

    private FileSelectionView() {
        originalFileLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fileSelectionWindowOriginalFileTextFieldCaption"));
        modificationFileLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "fileSelectionWindowModificationFileTextFieldCaption"));
        okButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "ok"));
    }

    public static void showFileSelectionView() {
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
