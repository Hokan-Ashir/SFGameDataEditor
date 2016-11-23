package sfgamedataeditor.common.widgets.casttype;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;
import java.lang.reflect.Field;

public class CastTypeForm extends AbstractWidget {
    private static final int FORM_WIDTH = 300;
    private static final int FORM_HEIGHT = 100;

    private JPanel mainPanel;
    private JCheckBox isProjectileToEnemyCheckBox;
    private JCheckBox isProjectileToAlliesCheckBox;
    private JCheckBox isTargetAreaCheckBox;
    private JCheckBox isWorldInstantAreaCheckBox;
    private JCheckBox isInstantAreaCheckBox;
    private JCheckBox isWorldTargetAreaCheckBox;
    private JCheckBox isAlliesAreaCheckBox;
    private JLabel label;
    private JSeparator parametersSeparator;
    private JPanel parametersPanel;

    public CastTypeForm(Field DTOField) {
        super(DTOField);
    }

//    public CastTypeForm() {
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

    @Override
    public void update(Model model) {

    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
