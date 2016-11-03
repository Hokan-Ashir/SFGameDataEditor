package sfgamedataeditor.mvc;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.*;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.ControllableView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModel;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModelParameter;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.*;

public enum  ShowViewDispatcher {
    INSTANCE;

    private List<Class<? extends ControllableView>> viewsOnTheScreen = new ArrayList<>();
    private Map<ClassTuple<? extends ControllableView, ? extends ControllableView>, ModelCreator> modelCreatorsMap = new HashMap<>();

    ShowViewDispatcher() {
        modelCreatorsMap.put(new ClassTuple<>(SpellParameterView.class, SpellsView.class), new StubModelCreator());
        modelCreatorsMap.put(new ClassTuple<>(SpellsView.class, SpellSchoolsView.class), new StubModelCreator());
        modelCreatorsMap.put(new ClassTuple<>(SpellSchoolsView.class, ModulesView.class), new StubModelCreator());
    }

    public void showView(Class<? extends ControllableView> viewClassToShow, Model model) {
        List<ViewEvent> eventsToProcess = createEventListToProcess(viewClassToShow, model);

        for (ViewEvent eventsToProces : eventsToProcess) {
            if (eventsToProces.getViewClass().equals(viewClassToShow) && ShowViewEvent.class.isAssignableFrom(eventsToProces.getClass())) {
                EventHistory.INSTANCE.addEventToHistory((ShowViewEvent) eventsToProces);
                break;
            }
        }

        for (Event event : eventsToProcess) {
            EventProcessor.INSTANCE.process(event);
        }

        updateEventHistoryButtonsState();
    }

    public void showViewSilently(Class<? extends ControllableView> viewClassToShow, Model model) {
        List<ViewEvent> eventsToProcess = createEventListToProcess(viewClassToShow, model);

        for (Event event : eventsToProcess) {
            EventProcessor.INSTANCE.process(event);
        }
    }

    private List<ViewEvent> createEventListToProcess(Class<? extends ControllableView> viewClassToShow, Model model) {
        List<ViewEvent> eventsToProcess = new ArrayList<>();
        List<Class<? extends ControllableView>> viewsToShow = ViewHierarchy.INSTANCE.getViewsToShow(viewClassToShow);
        List<ViewEvent> toProcess = createEventsToProcess(viewsToShow, model);
        eventsToProcess.addAll(toProcess);
        List<ViewEvent> unrenderEvents = createUnRenderViewEventList(viewsToShow);
        eventsToProcess.addAll(unrenderEvents);

        return eventsToProcess;
    }

    private List<ViewEvent> createEventsToProcess(List<Class<? extends ControllableView>> viewBranch, Model model) {
        List<ViewEvent> events = new ArrayList<>();
        Model parentModel = model;
        for (int i = 0; i < viewBranch.size(); ++i) {
            if (i + 1 == viewBranch.size()) {
                break;
            }

            Class<? extends ControllableView> viewI = viewBranch.get(i);
            Class<? extends ControllableView> viewI1 = viewBranch.get(i + 1);
            boolean existsOnScreen = isViewExistsOnScreen(viewI);
            if (existsOnScreen) {
                ModelCreator modelCreator = modelCreatorsMap.get(new ClassTuple<>(viewI, viewI1));
                if (modelCreator != null) {
                    parentModel = modelCreator.createModel(parentModel);
                    UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(viewI, parentModel);
                    events.add(updateViewModelEvent);
                }
            } else {
                ShowViewEvent showViewEvent = new ShowViewEvent(viewI, parentModel);
                events.add(showViewEvent);
            }
        }

        if (events.size() == 0 && viewBranch.size() != 0) {
            for (Class<? extends ControllableView> aClass : viewBranch) {
                ShowViewEvent event = new ShowViewEvent(aClass, null);
                events.add(event);
            }
        }

        return events;
    }

    private List<ViewEvent> createUnRenderViewEventList(List<Class<? extends ControllableView>> viewsToShow) {
//        if (!viewsToShow.contains(ModulesView.class)) {
//            return Collections.emptyList();
//        }

        List<Class<? extends ControllableView>> modulesViewsOnScreenToUnrender = getAbstractModulesViewsOnScreen();

        if (modulesViewsOnScreenToUnrender.isEmpty()) {
            return Collections.emptyList();
        }

        filterEventsOnScreenThatShouldBeRendered(viewsToShow, modulesViewsOnScreenToUnrender);

        if (modulesViewsOnScreenToUnrender.isEmpty()) {
            return Collections.emptyList();
        }

        int rootModuleViewIndexToUnrender = getRootIndexViewsOnScreenToUnrender(modulesViewsOnScreenToUnrender);
        return createUnrenderEventList(rootModuleViewIndexToUnrender);
    }

    private void filterEventsOnScreenThatShouldBeRendered(List<Class<? extends ControllableView>> viewsToShow, List<Class<? extends ControllableView>> modulesViewsOnScreenToUnrender) {
        for (Class<? extends ControllableView> aClass : viewsToShow) {
            boolean moduleView = AbstractModulesView.class.isAssignableFrom(aClass);// && !ModulesView.class.equals(aClass);
            if (moduleView) {
                if (modulesViewsOnScreenToUnrender.contains(aClass)) {
                    modulesViewsOnScreenToUnrender.remove(aClass);
                }
            }
        }
    }

    private List<Class<? extends ControllableView>> getAbstractModulesViewsOnScreen() {
        List<Class<? extends ControllableView>> modulesViewsOnScreenToUnrender = new ArrayList<>();
        for (Class<? extends ControllableView> aClass : viewsOnTheScreen) {
            boolean moduleView = AbstractModulesView.class.isAssignableFrom(aClass) && !ModulesView.class.equals(aClass);
            if (moduleView) {
                modulesViewsOnScreenToUnrender.add(aClass);
            }
        }
        return modulesViewsOnScreenToUnrender;
    }

    private List<ViewEvent> createUnrenderEventList(int rootModuleViewIndexToUnrender) {
        List<ViewEvent> unrenderEvents = new ArrayList<>();
        for (int i = rootModuleViewIndexToUnrender; i < viewsOnTheScreen.size(); i++) {
            UnShowViewEvent event = new UnShowViewEvent(viewsOnTheScreen.get(i));
            unrenderEvents.add(event);
        }
        return unrenderEvents;
    }

    private int getRootIndexViewsOnScreenToUnrender(List<Class<? extends ControllableView>> modulesViewsOnScreenToUnrender) {
        int rootModuleViewIndexToUnrender = Integer.MAX_VALUE;
        for (Class<? extends ControllableView> aClass : modulesViewsOnScreenToUnrender) {
            int i = viewsOnTheScreen.indexOf(aClass);
            if (i < rootModuleViewIndexToUnrender) {
                rootModuleViewIndexToUnrender = i;
            }
        }
        return rootModuleViewIndexToUnrender;
    }

    private boolean isViewExistsOnScreen(Class<? extends ControllableView> view) {
        return viewsOnTheScreen.contains(view);
    }

    private void updateEventHistoryButtonsState() {
        boolean isRedoPossible = EventHistory.INSTANCE.isRedoPossible();
        boolean isUndoPossible = EventHistory.INSTANCE.isUndoPossible();
        EventHistoryModelParameter parameter = new EventHistoryModelParameter(isRedoPossible, isUndoPossible);
        EventHistoryModel model = new EventHistoryModel(parameter);
        UpdateViewModelEvent event = new UpdateViewModelEvent(EventHistoryView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    public void registerRenderedView(Class<? extends ControllableView> classViewToRegister) {
        viewsOnTheScreen.add(classViewToRegister);
    }

    public void unregisterUnRenderedView(Class<? extends ControllableView> classViewToUnRegister) {
        viewsOnTheScreen.remove(classViewToUnRegister);
    }
}
