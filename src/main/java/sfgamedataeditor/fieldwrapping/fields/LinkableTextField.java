package sfgamedataeditor.fieldwrapping.fields;

import org.apache.log4j.Logger;
import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class LinkableTextField extends AbstractDataField<JTextField> {

    private static final Logger LOGGER = Logger.getLogger(LinkableTextField.class);

    private final JButton linkButton;

    public LinkableTextField(EntityTuple<JTextField> tuple, Class<AbstractMetaEvent> eventClass) {
        super(tuple);
        AbstractMetaEvent event = null;
        try {
            event = eventClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
        }

        linkButton = createLinkButton(event);
        getComponent().add(linkButton);
    }

    private JButton createLinkButton(AbstractMetaEvent event) {
        JButton button = new JButton(">>");
        button.setToolTipText(event.getEventDescription());
        // TODO set text or symbol
        // TODO set tooltip text based on metaevent description
        attachLinkEventListener(event);

        return button;
    }

    private void attachLinkEventListener(final AbstractMetaEvent event) {
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventHandlerRegister.INSTANCE.fireEvent(event);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFieldValue(int value) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFieldValue() {
        return 0;
    }
}
