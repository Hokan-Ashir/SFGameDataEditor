package sfgamedataeditor.utils;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.background.ColorBackground;
import ch.swingfx.twinkle.style.background.IBackground;
import ch.swingfx.twinkle.style.closebutton.NullCloseButton;
import ch.swingfx.twinkle.style.overlay.BorderOverlay;
import ch.swingfx.twinkle.style.overlay.OverlayPaintMode;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.window.Positions;

import javax.swing.*;
import java.awt.*;

public class Notification {

    private static final int MINIMUM_NOTIFICATION_SIZE = 200;
    private static final int MAXIMUM_NOTIFICATION_SIZE = 400;
    private static final int DEFAULT_SHOWING_TIME = 3000;

    public Notification(String message) {
        this(message, DEFAULT_SHOWING_TIME, NotificationType.INFO);
    }

    public Notification(String message, NotificationType type) {
        this(message, DEFAULT_SHOWING_TIME, type);
    }

    private Notification(String message, int displayTime, NotificationType type) {
        String title;
        IBackground background;
        Color titleFontColor;
        Color messageFontColor;
        switch (type) {
            case INFO:
                title = I18N.INSTANCE.getMessage("info");
                background = new ColorBackground(new Color(0, 0, 0, 255));
                titleFontColor = new Color(255, 220, 86, 255);
                messageFontColor = new Color(255, 255, 255, 255);
                break;
            case WARNING:
                title = I18N.INSTANCE.getMessage("warning");
                background = new ColorBackground(new Color(255, 181, 178, 255));
                titleFontColor = new Color(250, 130, 118, 255);
                messageFontColor = new Color(250, 130, 118, 255);
                break;
            case ERROR:
                title = I18N.INSTANCE.getMessage("error");
                background = new ColorBackground(new Color(255, 52, 0, 252));
                titleFontColor = new Color(255, 255, 255, 255);
                messageFontColor = new Color(255, 255, 255, 255);
                break;
            default:
                title = "";
                background = new ColorBackground(new Color(0, 0, 0, 255));
                titleFontColor = new Color(255, 220, 86, 255);
                messageFontColor = new Color(255, 255, 255, 255);
                break;
        }

        Dimension size = getMessageDimension(message);

        INotificationStyle style = new DarkDefaultNotification()
                .withWidth((int) (size.getWidth()))
                .withAlpha(0.5f)
                .withCloseButton(new NullCloseButton())
                .withOverlay(new BorderOverlay(3, Color.WHITE, OverlayPaintMode.ALWAYS))
                .withMessageFontColor(messageFontColor)
                .withTitleFontColor(titleFontColor)
                .withBackground(background);

        showNotification(title, message, displayTime, style);
    }

    private Dimension getMessageDimension(String message) {
        Font defaultFont = UIManager.getDefaults().getFont("TextPane.font");
        FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(defaultFont);
        int height = fontMetrics.getHeight();
        int advance = fontMetrics.stringWidth(message);
        Dimension size = new Dimension(advance, height);
        if (size.getWidth() > MAXIMUM_NOTIFICATION_SIZE) {
            size.setSize(MAXIMUM_NOTIFICATION_SIZE, size.getHeight());
        } else if (size.getWidth() < MINIMUM_NOTIFICATION_SIZE) {
            size.setSize(MINIMUM_NOTIFICATION_SIZE, size.getHeight());
        }
        return size;
    }

    private void showNotification(String title, String message, int displayTime, INotificationStyle style) {
        new NotificationBuilder()
                .withFadeOutAnimation(true)
                .withStyle(style)
                .withTitle(title)
                .withMessage(message)
                .withDisplayTime(displayTime)
                .withPosition(Positions.CENTER)
                .showNotification();
    }
}
