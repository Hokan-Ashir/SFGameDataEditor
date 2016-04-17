package sfgamedataeditor.events;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import sfgamedataeditor.views.common.AbstractView;

public class ClassTuple<T extends AbstractView<V>, V extends AbstractView> {

    private Class<T> viewClass;
    private V parentViewInstance;

    public ClassTuple(Class<T> viewClass, V parentViewInstance) {
        this.viewClass = viewClass;
        this.parentViewInstance = parentViewInstance;
    }

    public Class<T> getViewClass() {
        return viewClass;
    }

    public V getParentViewInstance() {
        return parentViewInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(viewClass).append(parentViewInstance).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        ClassTuple other = (ClassTuple) obj;
        return new EqualsBuilder().append(viewClass, other.getViewClass()).
                append(parentViewInstance, other.getParentViewInstance()).build();
    }
}
