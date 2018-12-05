package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.IGenericNaming;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Active entity bean for getting active records.
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class GeneralNaming extends Active implements IGenericNaming {

    private static final long serialVersionUID = -7331270591406353590L;

    @NotNull
    @Size(max = 10)
    @Column(name = "zz_generic_code", length = 10)
    private String genericCode;

    @NotNull
    @Column(name = "zz_generic_description", unique = true)
    private String genericName;

    @Override
    public String getGenericCode() {
        return genericCode;
    }

    @Override
    public void setGenericCode(String genericCode) {
        this.genericCode = genericCode;
    }

    @Override
    public String getGenericName() {
        return genericName;
    }

    @Override
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getGenericCode(), getGenericName());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof IGenericNaming)) {
            return false;
        }
        final IGenericNaming other = (IGenericNaming) obj;
        return Objects.equals(getGenericCode(), other.getGenericCode())
                && Objects.equals(getGenericName(), other.getGenericName());

    }
}
