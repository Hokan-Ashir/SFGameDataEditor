package sfgamedataeditor.common.cache.icons.aliases;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractIconPathAlias {

    private final Map<String, String> aliasMap = new HashMap<>();

    public AbstractIconPathAlias() {
        fillAliasMap();
    }

    protected abstract void fillAliasMap();

    protected void addAlias(String alias, String original) {
        aliasMap.put(alias, original);
    }

    public String getAlias(String iconPath) {
        return aliasMap.get(iconPath);
    }
}
