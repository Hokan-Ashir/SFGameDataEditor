package sfgamedataeditor.views.main.modules.player.level.stats;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.player.level.stats.GUIElements;
import sfgamedataeditor.database.player.level.stats.PlayerLevelStatsObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

public class PlayerLevelStatsView implements PresentableView {
    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.HP_FACTOR, DTOColumnNames = "hpFactor", DTOClass = PlayerLevelStatsObject.class)
    private JPanel hpFactorPanel;

    @GUIElement(GUIElementId = GUIElements.MP_FACTOR, DTOColumnNames = "mpFactor", DTOClass = PlayerLevelStatsObject.class)
    private JPanel mpFactorPanel;

    @GUIElement(GUIElementId = GUIElements.EXPERIENCE, DTOColumnNames = "experience", DTOClass = PlayerLevelStatsObject.class)
    private JPanel experiencePanel;

    @GUIElement(GUIElementId = GUIElements.MAX_ATTRIBUTES_POINTS, DTOColumnNames = "maxAttributesPoints", DTOClass = PlayerLevelStatsObject.class)
    private JPanel maxAttributePointsPanel;

    @GUIElement(GUIElementId = GUIElements.MAX_SKILL_LEVEL, DTOColumnNames = "maxSkillLevel", DTOClass = PlayerLevelStatsObject.class)
    private JPanel maxSkillLevelPanel;

    @GUIElement(GUIElementId = GUIElements.WEAPON_FACTOR, DTOColumnNames = "weaponFactor", DTOClass = PlayerLevelStatsObject.class)
    private JPanel weaponFactorPanel;

    @GUIElement(GUIElementId = GUIElements.ARMOR_FACTOR, DTOColumnNames = "armorFactor", DTOClass = PlayerLevelStatsObject.class)
    private JPanel armorFactorPanel;

    @GUIElement(GUIElementId = GUIElements.LEVEL, DTOColumnNames = "level", DTOClass = PlayerLevelStatsObject.class)
    private JPanel levelPanel;

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return PlayerLevelStatsPresenter.class;
    }

    @Override
    public void localize() {

    }
}
