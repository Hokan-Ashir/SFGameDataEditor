package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class SpellScrollsParametersPresenter extends AbstractPresenter<SpellScrollsParametersModelParameter, SpellScrollsParametersView> {

    private static final Logger LOGGER = Logger.getLogger(SpellScrollsParametersPresenter.class);

    public SpellScrollsParametersPresenter(SpellScrollsParametersView view) {
        super(view);
    }

    @Override
    public void updateView() {
        SpellScrollsParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        List<ItemSpellEffectsObject> itemSpellEffectsObjects = parameter.getItemSpellEffectsObjects();

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

                Class<?> dtoClass = annotation.DTOClass();
                if (dtoClass.equals(ItemPriceParametersObject.class)) {
                    widget.getListener().updateWidgetValue(priceParametersObject);
                } else if (dtoClass.equals(ItemSpellEffectsObject.class)) {
                    if (itemSpellEffectsObjects == null || itemSpellEffectsObjects.isEmpty()) {
                        panel.setVisible(false);
                    } else {
                        panel.setVisible(true);
                        // it's guaranteed that scroll has only one spell effect on it
                        widget.getListener().updateWidgetValue(itemSpellEffectsObjects.get(0));
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
        mainView.renderViewInsideContentPanel(getView().getMainPanel());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unRenderViewInsideContentPanel(getView().getMainPanel());
    }
}
