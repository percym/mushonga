package systems.health263.dashboard.model.clinical;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import systems.health263.dashboard.imodel.clinical.IDataICD10;
import systems.health263.dashboard.model.general.PrimaryKeyBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Munyaradzi Takayindisa
 * <p>
 * Implementation for the IDataICD10 class
 */
@Entity
@Immutable
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "static", name = "icd10")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "icd10_serial"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "icd10_serial_seq", allocationSize = 1000)
public class DataICD10 extends PrimaryKeyBean implements IDataICD10 {

    private static final long serialVersionUID = -2703995016727742013L;

    @NotNull
    @Column(name = "icd10_code_field", length = 6)
    private String codeField;

    @NotNull
    @Column(name = "icd10_description_field", length = 160)
    private String descriptionField;

}
