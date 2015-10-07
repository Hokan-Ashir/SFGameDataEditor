package views;

import views.skills.SkillView;
import views.spells.blackmagic.DeathMagicView;
import views.spells.blackmagic.NecromancyView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpellForce GameData.cff editor");
        final MainView mainView = new MainView();
        frame.setContentPane(mainView.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        mainView.modulesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();

                    mainView.modulesPanel.removeAll();
                    if (item.equals("Skills")) {
                        SkillView skillView = new SkillView();
                        mainView.modulesPanel.add(skillView.getMainPanel());
                    } else if (item.equals("Black magic : Death")) {
                        DeathMagicView deathMagicView = new DeathMagicView();
                        mainView.modulesPanel.add(deathMagicView.getMainPanel());
                    } else if (item.equals("Black magic : Necromancy")) {
                        NecromancyView necromancyView = new NecromancyView();
                        mainView.modulesPanel.add(necromancyView.getMainPanel());
                    }

                    mainView.modulesPanel.revalidate();
                    mainView.modulesPanel.repaint();
                }
            }
        });

        mainView.modulesComboBox.setSelectedItem(null);
        mainView.modulesComboBox.setSelectedIndex(0);
    }

}
