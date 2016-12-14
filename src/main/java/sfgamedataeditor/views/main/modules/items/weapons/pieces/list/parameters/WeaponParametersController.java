package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;

public class WeaponParametersController extends AbstractController<WeaponParametersModelParameter, WeaponParametersView> {

    private static final Logger LOGGER = Logger.getLogger(WeaponParametersController.class);

    public WeaponParametersController(WeaponParametersView view) {
        super(view);
    }

    @Override
    public void updateView() {
        WeaponParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        ItemSpellEffectsObject itemSpellEffectsObject = parameter.getItemSpellEffectsObject();
        WeaponParametersObject weaponParametersObject = parameter.getWeaponParametersObject();
        ItemRequirementsObject requirementsObject = parameter.getRequirementsObject();

        Field[] declaredFields = getView().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            GUIElement annotation = declaredField.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            try {
                declaredField.setAccessible(true);
                JPanel panel = (JPanel) declaredField.get(getView());
                AbstractWidget widget = (AbstractWidget) panel.getComponent(0);

                // TODO get rid of this equals-switch & generalize same solutions
                Class<?> dtoClass = annotation.DTOClass();
                if (dtoClass.equals(ItemPriceParametersObject.class)) {
                    widget.getListener().updateWidgetValue(priceParametersObject);
                } else if (dtoClass.equals(ItemSpellEffectsObject.class)) {
                    if (itemSpellEffectsObject == null) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        widget.getListener().updateWidgetValue(itemSpellEffectsObject);
                    }
                } else if (dtoClass.equals(WeaponParametersObject.class)) {
                    if (weaponParametersObject == null) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        widget.getListener().updateWidgetValue(weaponParametersObject);
                    }
                } else if (dtoClass.equals(ItemRequirementsObject.class)) {
                    if (requirementsObject == null) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        widget.getListener().updateWidgetValue(requirementsObject);
                    }
                } else {

                }

//                widget.updateI18N(strings);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unRenderViewInsideContentPanel(getView());
    }
}
