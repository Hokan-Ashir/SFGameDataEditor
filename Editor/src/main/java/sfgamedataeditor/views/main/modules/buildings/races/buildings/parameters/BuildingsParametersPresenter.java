package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.buildings.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingsParametersPresenter extends AbstractPresenter<BuildingsParametersModelParameter, BuildingsParametersView> {

    private static final Logger LOGGER = Logger.getLogger(BuildingsParametersPresenter.class);
    private static final Map<Integer, Integer> RESOURCES_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> ARMY_REQUIREMENTS_NUMBER_MAPPING = new HashMap<>();

    public BuildingsParametersPresenter(BuildingsParametersView view) {
        super(view);
        initializeResourcesNumberMapping();
        initializeArmyRequirementsNumberMapping();
    }

    private void initializeResourcesNumberMapping() {
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE_1, 0);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE_2, 1);
        RESOURCES_NUMBER_MAPPING.put(GUIElements.RESOURCE_3, 2);
    }

    private void initializeArmyRequirementsNumberMapping() {
        ARMY_REQUIREMENTS_NUMBER_MAPPING.put(GUIElements.ARMY_UNIT_1, 0);
        ARMY_REQUIREMENTS_NUMBER_MAPPING.put(GUIElements.ARMY_UNIT_2, 1);
        ARMY_REQUIREMENTS_NUMBER_MAPPING.put(GUIElements.ARMY_UNIT_3, 2);
        ARMY_REQUIREMENTS_NUMBER_MAPPING.put(GUIElements.ARMY_UNIT_4, 3);
    }

    @Override
    public void updateView() {
        BuildingsParametersModelParameter parameter = getModel().getParameter();
        BuildingsObject buildingsObject = parameter.getBuildingsObject();
        List<BuildingsRequirementsObject> requirementsObjects = parameter.getRequirementsObjects();
        List<BuildingsArmyRequirementsObject> buildingsArmyRequirementsObjects = parameter.getBuildingsArmyRequirementsObjects();
        Icon icon = parameter.getIcon();

        Field[] declaredFields = getView().getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                IconElement iconElement = declaredField.getAnnotation(IconElement.class);
                if (iconElement != null) {
                    declaredField.setAccessible(true);
                    JLabel panel = (JLabel) declaredField.get(getView());
                    panel.setIcon(icon);
                    continue;
                }

                GUIElement annotation = declaredField.getAnnotation(GUIElement.class);
                if (annotation == null) {
                    continue;
                }


                declaredField.setAccessible(true);
                JPanel panel = (JPanel) declaredField.get(getView());
                AbstractWidget widget = (AbstractWidget) panel.getComponent(0);

                Class<?> dtoClass = annotation.DTOClass();
                if (dtoClass.equals(BuildingsObject.class)) {
                    widget.getListener().updateWidgetValue(buildingsObject);
                } else if (dtoClass.equals(BuildingsRequirementsObject.class)) {
                    if (requirementsObjects == null || requirementsObjects.isEmpty()) {
                        widget.setVisible(false);
                    } else {
                        updateWidgetWithParametersList(annotation.GUIElementId(), widget, requirementsObjects, RESOURCES_NUMBER_MAPPING);
                    }
                } else if (dtoClass.equals(BuildingsArmyRequirementsObject.class)) {
                    if (buildingsArmyRequirementsObjects == null || buildingsArmyRequirementsObjects.isEmpty()) {
                        widget.setVisible(false);
                    } else {
                        updateWidgetWithParametersList(annotation.GUIElementId(), widget, buildingsArmyRequirementsObjects, ARMY_REQUIREMENTS_NUMBER_MAPPING);
                    }
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    // TODO duplication with UnitParametersPresenter
    private void updateWidgetWithParametersList(int guiElementId, AbstractWidget widget,
                                                List<? extends OffsetableObject> objects,
                                                Map<Integer, Integer> guiElementsMapping) {
        Integer objectIndex = guiElementsMapping.get(guiElementId);
        if (objectIndex >= objects.size()) {
            widget.setVisible(false);
        } else {
            widget.setVisible(true);
            widget.getListener().updateWidgetValue(objects.get(objectIndex));
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
