package sfgamedataeditor.database.common;

public interface DTODecorator<T> {
    T decorateObject(T object);
}
