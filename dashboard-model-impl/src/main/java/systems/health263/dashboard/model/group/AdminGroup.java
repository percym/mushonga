package systems.health263.dashboard.model.group;

import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.group.IGroup;
import systems.health263.dashboard.model.permissions.AdminPermissionsConsumption;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
@Table(schema = "data", name = "groups" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"group_title", }, name = "un_group_title")
})
//@AttributeOverrides({
//        @AttributeOverride(name = "id", column = @Column(name = "groups_serial"))
//
//})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "groups_serial_seq", allocationSize = 1)
public class AdminGroup implements IGroup<AdminPermissionsConsumption>,Serializable {


    private static final long serialVersionUID = 4608732201469991960L;

    @Id
    @GeneratedValue(generator = "default_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "groups_serial", insertable = false, nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "group_title" ,length = 80 , unique = true)
    private String title;

    @Column(name = "group_active")
    private boolean active;

    @Valid
    @ManyToMany(cascade = CascadeType.ALL,targetEntity = AdminPermissionsConsumption.class,fetch = FetchType.EAGER)
    private Collection<AdminPermissionsConsumption> permissions;

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Collection<AdminPermissionsConsumption> getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(Collection<AdminPermissionsConsumption> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;

    }
}
