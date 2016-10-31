package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;

public class SpellsView extends AbstractModulesView {

    public SpellsView() {
        super(I18N.INSTANCE.getMessage("spells"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideNavigationPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellController.class;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void setEventParameter(AbstractMetaEvent metaEvent) {
//        super.setEventParameter(metaEvent);
//        String selectedSpellName = getSelectedModuleValue();
//        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(selectedSpellName);
//        spellParameterEventParameter.setSpellId(spellId);
//
//        LevelableView<SpellsView> levelableView = ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SpellsView.class));
//        if (levelableView != null) {
//            spellParameterEventParameter.setSpellLevel(levelableView.getSelectedLevel());
//        } else {
//            spellParameterEventParameter.setSpellLevel(1);
//        }
//
//        metaEvent.setEventParameter(ShowSpellParameterViewEvent.class, spellParameterEventParameter);
//    }
}
