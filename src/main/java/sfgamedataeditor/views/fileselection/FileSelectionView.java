package sfgamedataeditor.views.fileselection;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class FileSelectionView {

    private JPanel mainPanel;
    private JTextField originalFileField;
    private JLabel originalFileLabel;
    private JButton originalFileSelectorButton;
    private JLabel modificationFileLabel;
    private JTextField modificationFileField;
    private JButton modificationFileSelectorButton;
    private JButton okButton;

    public FileSelectionView() {
        originalFileLabel.setText(I18N.INSTANCE.getMessage("fileSelectionWindowOriginalFileTextFieldCaption"));
        modificationFileLabel.setText(I18N.INSTANCE.getMessage("fileSelectionWindowModificationFileTextFieldCaption"));
        okButton.setText(I18N.INSTANCE.getMessage("ok"));
    }

    public static void showFileSelectionView() {
        final JFrame frame = new JFrame(I18N.INSTANCE.getMessage("fileSelectionWindowCaption"));
        final FileSelectionView view = new FileSelectionView();
        frame.setResizable(false);
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);

        view.getOkButton().setEnabled(false);
        view.getOkButton().addActionListener(new UploadDataButtonListener(frame, view));
        view.getOriginalFileSelectorButton().addActionListener(new OriginalFileSelectorListener(view));
        view.getModificationFileSelectorButton().addActionListener(new ModificationFileSelectorListener(view));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    private JButton getOriginalFileSelectorButton() {
        return originalFileSelectorButton;
    }

    private JButton getModificationFileSelectorButton() {
        return modificationFileSelectorButton;
    }

    public JTextField getOriginalFileField() {
        return originalFileField;
    }

    public JTextField getModificationFileField() {
        return modificationFileField;
    }
}
