package sfgamedataeditor.views.main.modules.common.localization;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;

import javax.swing.*;
import java.util.List;

public class LocalizationView implements PresentableView {
    private JPanel mainPanel;
    private JComboBox<ObjectTuple> languageComboBox;

    public LocalizationView() {
        Integer languageId = LocalizationService.INSTANCE.getLanguageId();
        List<ObjectTuple> availableLanguages = LocalizationService.INSTANCE.getAvailableLanguages();
        for (ObjectTuple availableLanguage : availableLanguages) {
            languageComboBox.addItem(availableLanguage);

            if (availableLanguage.getObjectId().equals(languageId)) {
                languageComboBox.setSelectedItem(availableLanguage);
            }
        }

        languageComboBox.addItemListener(new LanguageSelectionListener(languageComboBox));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return LocalizationPresenter.class;
    }
}
