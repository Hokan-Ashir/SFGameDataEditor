package sfgamedataeditor.database.common;

public interface DTOFilter {
    boolean isApplicable(byte[] value);
}
