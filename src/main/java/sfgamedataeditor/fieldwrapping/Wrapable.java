package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.utils.EntityTuple;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

interface Wrapable {
    Map<Field, EntityTuple> getFieldDataRestrictions();
}
