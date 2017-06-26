package sfgamedataeditor.database.common;

public interface DTOFilter {
    boolean isAcceptable(byte[] buffer);
}
