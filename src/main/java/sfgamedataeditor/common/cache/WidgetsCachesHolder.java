package sfgamedataeditor.common.cache;

import sfgamedataeditor.mvc.objects.View;

import java.util.HashMap;
import java.util.Map;

public enum WidgetsCachesHolder {
    INSTANCE;

    private final Map<Class<? extends View>, WidgetsCache> cacheMap = new HashMap<>();

    public WidgetsCache getWidgetsCache(Class<? extends View> viewClass) {
        boolean isCacheExists = cacheMap.containsKey(viewClass);
        if (!isCacheExists) {
            WidgetsCache cache = new WidgetsCache();
            cacheMap.put(viewClass, cache);
        }

        return cacheMap.get(viewClass);
    }
}
