package systems.health263.dashboard.model.permissions;

import systems.health263.dashboard.imodel.permissions.IPermissions;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(schema = "static", name = "admin_permissions")
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "admin_permissions_serial_seq", allocationSize = 1)
public class AdminPermissions implements IPermissions,Serializable{


    private static final long serialVersionUID = 3944568458008474402L;

    @Id
    @GeneratedValue(generator = "default_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "permissions_serial", insertable = false, nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Size(max = 80)
    @Column(name = "permission_title")
    private String title;

    @NotNull
    @Size(max = 80)
    @Column(name = "permission_value")
    private String value;
    
    @NotNull
    @Column(name = "permission_active")
    private Boolean active;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active=active;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }
}
