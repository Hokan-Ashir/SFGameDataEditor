package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;

public class ArmorParametersController extends AbstractController<ArmorParametersModelParameter, ArmorParametersView> {

    private static final Logger LOGGER = Logger.getLogger(ArmorParametersController.class);

    public ArmorParametersController(ArmorParametersView view) {
        super(view);
    }

    @Override
    public void updateView() {
        ArmorParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        ItemEffectsObject itemEffectsObject = parameter.getItemEffectsObject();
        ArmorParametersObject armorParametersObject = parameter.getArmorParametersObject();
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
                } else if (dtoClass.equals(ItemEffectsObject.class)) {
                    if (itemEffectsObject == null) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        widget.getListener().updateWidgetValue(itemEffectsObject);
                    }
                } else if (dtoClass.equals(ArmorParametersObject.class)) {
                    widget.getListener().updateWidgetValue(armorParametersObject);
                } else if (dtoClass.equals(ItemRequirementsObject.class)) {
                    widget.getListener().updateWidgetValue(requirementsObject);
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
