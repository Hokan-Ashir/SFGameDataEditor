package sfgamedataeditor.common.cache.icons.aliases;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class AbstractIconPathAlias {

    private final Map<String, String> aliasMap = new HashMap<>();
    private static final String ALIASES_FOLDER = "aliases/";

    AbstractIconPathAlias() {
        addAliasesFromFile();
    }

    private void addAliasesFromFile() {
        String aliasFilePath = ALIASES_FOLDER + getAliasFileName();
        ResourceBundle bundle = ResourceBundle.getBundle(aliasFilePath);
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (value.startsWith("[")) {
                String[] aliases = value.replaceAll("\\[(.*)\\]", "$1").split(", ");
                for (String alias : aliases) {
                    addAlias(alias + ".png", key + ".png");
                }
            } else {
                addAlias(value + ".png", key + ".png");
            }
        }
    }

    protected abstract String getAliasFileName();
    protected abstract String getImagePathPrefix();

    private void addAlias(String alias, String original) {
        aliasMap.put(getImagePathPrefix() + alias, getImagePathPrefix() + original);
    }

    public String getAlias(String iconPath) {
        return aliasMap.get(iconPath);
    }
}
