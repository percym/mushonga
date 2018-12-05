package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.IPrimaryKey;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Abstract class for the primary key to be inherited by all tables
 *
 * @author Percy Mugadza
 */
@EqualsAndHashCode
@MappedSuperclass
@Audited
public abstract class PrimaryKeyBean implements IPrimaryKey {

    private static final long serialVersionUID = -3186729721754556621L;

    @Id
    @GeneratedValue(generator = "default_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "zz_serial", insertable = false, nullable = false, updatable = false)
    private Long id;

    @Override
    public Long getId() {

        return id;
    }

    @Override
    public void setId(final Long id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

