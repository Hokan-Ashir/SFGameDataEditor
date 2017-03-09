package sfgamedataeditor.common.cache.icons.aliases;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractIconPathAlias {

    private final Map<String, String> aliasMap = new HashMap<>();

    public AbstractIconPathAlias() {
        fillAliasMap();
    }

    protected abstract void fillAliasMap();
    protected abstract String getImagePathPrefix();

    protected void addAlias(String alias, String original) {
        aliasMap.put(getImagePathPrefix() + alias, getImagePathPrefix() + original);
    }

    public String getAlias(String iconPath) {
        return aliasMap.get(iconPath);
    }
}
