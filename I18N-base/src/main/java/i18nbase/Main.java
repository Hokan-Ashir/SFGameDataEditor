package i18nbase;

import i18nbase.objects.Service;
import i18nbase.objects.common_gui.CommonGUI;

public class Main {
    public static void main(String[] args) {
        Service.INSTANCE.createTableFromResourceFile("common_gui.properties", CommonGUI.class);
    }
}
