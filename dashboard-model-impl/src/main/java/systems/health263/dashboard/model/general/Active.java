package systems.health263.dashboard.model.general;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.general.IActive;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Active entity bean for getting active records.
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class Active extends TimeActiveRecord implements IActive {

    private static final long serialVersionUID = 8662079809319279877L;


    @NotNull
    @Column(name = "zz_active", length = 1, columnDefinition = "BOOLEAN DEFAULT '1'")
    private Boolean active;

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getActive());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof IActive)) {
            return false;
        }
        final IActive other = (IActive) obj;
        return Objects.equals(getActive(), other.getActive());

    }
}