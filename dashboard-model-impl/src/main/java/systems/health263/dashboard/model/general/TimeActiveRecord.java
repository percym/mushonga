package systems.health263.dashboard.model.general;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.general.ITimeActiveRecordEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity bean for finding all active records
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class TimeActiveRecord extends AuditingEntityBean implements ITimeActiveRecordEntity {

    private static final long serialVersionUID = -5350906279246611703L;

    @CreationTimestamp
    @Column(name = "zz_start_date", updatable = false, nullable = false)
    private LocalDateTime startDate;


    @UpdateTimestamp
    @Column(name = "zz_end_date", updatable = false)
    private LocalDateTime endDate;

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getStartDate(), getEndDate());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof TimeActiveRecord)) {
            return false;
        }
        final TimeActiveRecord other = (TimeActiveRecord) obj;
        return Objects.equals(getStartDate(), other.getStartDate())
                && Objects.equals(getEndDate(), other.getEndDate());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public LocalDateTime getStartDate() {

        return startDate;
    }

    @Override
    public void setStartDate(final LocalDateTime startDate) {

        this.startDate = startDate;
    }

    @Override
    public LocalDateTime getEndDate() {

        return endDate;
    }

    @Override
    public void setEndDate(final LocalDateTime endDate) {

        this.endDate = endDate;
    }

}