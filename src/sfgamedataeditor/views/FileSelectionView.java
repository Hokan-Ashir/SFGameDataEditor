package sfgamedataeditor.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileSelectionView {
    private JPanel mainPanel;
    private JTextField originalFileField;
    private JLabel originalFileLabel;
    private JButton originalFileSelectorButton;
    private JLabel modificationFileLabel;
    private JTextField modificationFileField;
    private JButton modificationFileSelectorButton;
    private JButton okButton;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("SpellForce GameData.cff Editor : File Selection Dialog");
        final FileSelectionView view = new FileSelectionView();
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        addOpenParametersEditorListener(frame, view);
        addOriginalFileSelectionListener(view);
        addModificationFileSelectionListener(view);
    }

    private static void addOriginalFileSelectionListener(final FileSelectionView view) {
        view.getOriginalFileSelectorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(view.getMainPanel());
            }
        });
    }

    private static void addOpenParametersEditorListener(final JFrame frame,
            FileSelectionView view) {
        view.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainView.showMainView();
            }
        });
    }

    private static void addModificationFileSelectionListener(final FileSelectionView view) {
        view.getModificationFileSelectorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(view.getMainPanel());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getOriginalFileSelectorButton() {
        return originalFileSelectorButton;
    }

    public JButton getModificationFileSelectorButton() {
        return modificationFileSelectorButton;
    }
}
