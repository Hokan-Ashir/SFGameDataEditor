package sfgamedataeditor.common.widgets.casttype;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;

public class CastTypeWidget extends AbstractWidget<CastTypeFormListener> {
    private static final int FORM_WIDTH = 300;
    private static final int FORM_HEIGHT = 100;

    private JPanel mainPanel;
    private JRadioButton isProjectileToEnemyRadioButton;
    private JRadioButton isProjectileToAlliesRadioButton;
    private JRadioButton isTargetAreaRadioButton;
    private JRadioButton isWorldInstantAreaRadioButton;
    private JRadioButton isInstantAreaRadioButton;
    private JRadioButton isWorldTargetAreaRadioButton;
    private JRadioButton isAlliesAreaRadioButton;
    private JLabel label;
    private JSeparator parametersSeparator;
    private JPanel parametersPanel;

    public CastTypeWidget() {
        add(getMainPanel());
    }

    //    public CastTypeWidget() {
    // need to set render parameters explicitly, if we want to use it as custom object in IDEA GUI designer palette,
    // cause otherwise, this component doesn't know what to render  inside it
    // moreover, to make it work; ANY custom component should be compiled to be shown inside
    // editor, be descendant of JComponent and have public constructor without any parameters, cause
    // otherwise GUI Editor have no idea how to create this component and which binary code it has
//        setVisible(true);
//        Dimension dimension = new Dimension(FORM_WIDTH, FORM_HEIGHT);
//        setPreferredSize(dimension);
//        setMaximumSize(dimension);
//        setMinimumSize(dimension);
//        add(mainPanel);
//    }


    public JRadioButton getIsProjectileToEnemyRadioButton() {
        return isProjectileToEnemyRadioButton;
    }

    public JRadioButton getIsProjectileToAlliesRadioButton() {
        return isProjectileToAlliesRadioButton;
    }

    public JRadioButton getIsTargetAreaRadioButton() {
        return isTargetAreaRadioButton;
    }

    public JRadioButton getIsWorldInstantAreaRadioButton() {
        return isWorldInstantAreaRadioButton;
    }

    public JRadioButton getIsInstantAreaRadioButton() {
        return isInstantAreaRadioButton;
    }

    public JRadioButton getIsWorldTargetAreaRadioButton() {
        return isWorldTargetAreaRadioButton;
    }

    public JRadioButton getIsAlliesAreaRadioButton() {
        return isAlliesAreaRadioButton;
    }

    @Override
    public void insertListener(CastTypeFormListener listener) {

    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
