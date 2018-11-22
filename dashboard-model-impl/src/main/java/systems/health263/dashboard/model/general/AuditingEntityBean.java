package systems.health263.dashboard.model.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import systems.health263.dashboard.imodel.general.IAuditingEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

import static systems.health263.dashboard.utility.strings.StringUtils.maxLength;

/**
 * AuditingEntityBean entity bean.
 *
 * @author Munyaradzi Takayindisa
 */
@MappedSuperclass
@Audited
public abstract class AuditingEntityBean extends PrimaryKeyBean implements IAuditingEntity {

    private static final long serialVersionUID = -423970580746701677L;

    @Size(min = 1, max = 50)
    @Column(name = "zz_created_by", length = 50, nullable = false)
    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @CreationTimestamp
    @Column(name = "zz_created_on", nullable = false)
    @JsonIgnore
    private transient LocalDateTime createdOn = LocalDateTime.now();

    @Size(min = 1, max = 50)
    @Column(name = "zz_updated_by", length = 50)
    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "zz_updated_on")
    @JsonIgnore
    private transient LocalDateTime updatedOn;

    @Override
    public String getCreatedBy() {

        return createdBy;
    }

    @Override
    public void setCreatedBy(final String createdBy) {

        this.createdBy = maxLength(createdBy, 50);
    }

    @Override
    public LocalDateTime getCreatedOn() {

        return createdOn;
    }

    @Override
    public void setCreatedOn(final LocalDateTime createdOn) {

        this.createdOn = createdOn;
    }

    @Override
    public String getUpdatedBy() {

        return updatedBy;
    }

    @Override
    public void setUpdatedBy(final String updatedBy) {

        this.updatedBy = maxLength(updatedBy, 50);
    }

    @Override
    public LocalDateTime getUpdatedOn() {

        return updatedOn;
    }

    @Override
    public void setUpdatedOn(final LocalDateTime lastUpdated) {

        updatedOn = lastUpdated;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getCreatedBy(), getCreatedOn(), getUpdatedBy(), getUpdatedOn());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof IAuditingEntity)) {
            return false;
        }
        final IAuditingEntity other = (IAuditingEntity) obj;
        return Objects.equals(getCreatedBy(), other.getCreatedBy())
                && Objects.equals(getCreatedOn(), other.getCreatedOn())
                && Objects.equals(getUpdatedBy(), other.getUpdatedBy())
                && Objects.equals(getUpdatedOn(), other.getUpdatedOn());

    }
}