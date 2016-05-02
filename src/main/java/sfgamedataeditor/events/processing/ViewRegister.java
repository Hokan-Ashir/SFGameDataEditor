package sfgamedataeditor.events.processing;

import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.MainView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<ClassTuple, AbstractView> views = new HashMap<ClassTuple, AbstractView>() {{
        put(new ClassTuple<>(NullView.class, AbstractView.class), new NullView(null));
    }};

    public Map<ClassTuple, AbstractView> getViews() {
        return views;
    }

    // TODO find more appropriate method-parameter signature
    public AbstractView getView(ClassTuple tuple) {
        if (views.containsKey(tuple)) {
            return views.get(tuple);
        }

        return null;
    }

    public void updateAllCurrentViews() {
        OffsetProvider.INSTANCE.recreateAllMaps();
        // TODO make this use-case work:
        // user selected Fire/Fireball-1 and change its spell requirements to
        // Elemental magic/Ice-1, made sfmod-file, then load it,
        // cause all maps in Mappings class stays the same
        // Fireball-1's requrements still considered as Elemental magic/Fire-1
        MainView view = (MainView) getView(new ClassTuple<>(MainView.class, NullView.class));
        updateDataRecursively(view);
    }

    private <T extends AbstractView> void updateDataRecursively(AbstractView<T> parent) {
        parent.updateData(null);
        for (AbstractView<T> tAbstractView : parent.getChildren()) {
            updateDataRecursively(tAbstractView);
        }
    }
}
