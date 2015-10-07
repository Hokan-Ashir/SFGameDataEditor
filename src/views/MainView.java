package views;

import javax.swing.*;

public class MainView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpellForce GameData.cff editor");
        MainView mainView = new MainView();
        frame.setContentPane(mainView.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        SkillView skillView = new SkillView();
        mainView.modulesPanel.add(skillView.getMainPanel());
        mainView.modulesPanel.revalidate();
    }

}
