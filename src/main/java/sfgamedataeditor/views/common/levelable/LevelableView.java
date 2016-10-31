package sfgamedataeditor.views.common.levelable;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;

public class LevelableView<T extends RenderableView> implements RenderableView {
    private JPanel mainPanel;
    private JComboBox<String> levelComboBox;
    private JLabel levelLabel;

    public LevelableView(T parentView) {
        levelLabel.setText(I18N.INSTANCE.getMessage("levelLabel"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Integer getSelectedLevel() {
        return Integer.valueOf((String) levelComboBox.getSelectedItem());
    }

    public JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }

    @Override
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }
}
