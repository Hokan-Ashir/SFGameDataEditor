package sfgamedataeditor.database.spells.school.names;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "spell_school_name")
public class SpellSchoolNameObject {

    @DatabaseField(id = true)
    public String name;

    @DatabaseField
    public Integer spellSchoolId;

    public SpellSchoolNameObject() {
    }

    public SpellSchoolNameObject(String name, Integer spellSchoolId) {
        this.name = name;
        this.spellSchoolId = spellSchoolId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(name).append(spellSchoolId).build();
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

        SpellSchoolNameObject other = (SpellSchoolNameObject) obj;
        return new EqualsBuilder().append(this.name, other.name).append(this.spellSchoolId, other.spellSchoolId).build();
    }
}
