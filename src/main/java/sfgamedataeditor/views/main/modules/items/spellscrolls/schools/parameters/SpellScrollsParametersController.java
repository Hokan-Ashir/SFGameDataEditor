package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;

public class SpellScrollsParametersController extends AbstractController<SpellScrollsParametersModelParameter, SpellScrollsParametersView> {

    private static final Logger LOGGER = Logger.getLogger(SpellScrollsParametersController.class);

    public SpellScrollsParametersController(SpellScrollsParametersView view) {
        super(view);
    }

    @Override
    public void updateView() {
        SpellScrollsParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        ItemSpellEffectsObject itemSpellEffectsObject = parameter.getItemSpellEffectsObject();

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
                } else {

                }
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
