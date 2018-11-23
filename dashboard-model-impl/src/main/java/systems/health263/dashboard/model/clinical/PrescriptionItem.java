package systems.health263.dashboard.model.clinical;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import systems.health263.dashboard.imodel.clinical.IPrescriptionItem;
import systems.health263.dashboard.model.general.GeneralNaming;

import javax.persistence.*;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IBill class
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "static", name = "prescription_item", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pres_item_generic_code", "pres_item_generic_name"}, name = "un_static_pres_item")})
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "pres_item_serial")),
        @AttributeOverride(name = "startDate", column = @Column(name = "pres_item_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "pres_item_end_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "pres_item_created_by")),
        @AttributeOverride(name = "createdOn", column = @Column(name = "pres_item_created_on")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "pres_item_updated_by")),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "pres_item_updated_on")),
        @AttributeOverride(name = "active", column = @Column(name = "pres_item_is_active")),
        @AttributeOverride(name = "genericCode", column = @Column(name = "pres_item_generic_code")),
        @AttributeOverride(name = "genericName", column = @Column(name = "pres_item_generic_name"))
})
@SequenceGenerator(name = "default_seq", schema = "static", sequenceName = "pres_item_serial_seq", allocationSize = 1)
public class PrescriptionItem extends GeneralNaming implements IPrescriptionItem {

    private static final long serialVersionUID = -6279563761126430181L;

}
