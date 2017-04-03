package sfgamedataeditor.common.cache.icons;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.icons.aliases.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ImageIconsCache {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ImageIconsCache.class);
    private final Map<String, ImageIcon> iconsMap = new HashMap<>();
    private final List<AbstractIconPathAlias> aliasList = new ArrayList<>();

    ImageIconsCache() {
        aliasList.add(new SpellAliases());
        aliasList.add(new UnitAliases());
        aliasList.add(new CreaturesAliases());
        aliasList.add(new HeroesAliases());
    }

    public ImageIcon getImageIcon(String iconPath) {
        ImageIcon imageIcon = iconsMap.get(iconPath);
        if (imageIcon != null) {
            return imageIcon;
        } else {
            URL resource = getClass().getResource(iconPath);
            if (resource == null) {
                String aliasPath = getAliasIconPath(iconPath);
                if (aliasPath == null) {
                    return null;
                }

                resource = getClass().getResource(aliasPath);
            }

            try {
                BufferedImage image = ImageIO.read(resource);
                ImageIcon icon = new ImageIcon(image);
                iconsMap.put(iconPath, icon);
                return icon;
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                return null;
            }
        }
    }

    private String getAliasIconPath(String iconPath) {
        for (AbstractIconPathAlias pathAlias : aliasList) {
            String alias = pathAlias.getAlias(iconPath);
            if (alias != null) {
                return alias;
            }
        }

        return null;
    }
}
