package sfgamedataeditor.common.cache;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public enum ImageIconsCache {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ImageIconsCache.class);
    private Map<String, ImageIcon> iconsMap = new HashMap<>();

    public ImageIcon getImageIcon(String iconPath) {
        ImageIcon imageIcon = iconsMap.get(iconPath);
        if (imageIcon != null) {
            return imageIcon;
        } else {
            URL resource = getClass().getResource(iconPath);
            if (resource == null) {
                return null;
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
}
