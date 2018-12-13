package info.mushonga.search.model.search;

import info.mushonga.search.imodel.search.ISearch;
import info.mushonga.search.model.general.Active;
import info.mushonga.search.model.product.ProductConsumption;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

/**
 * Implementation of the ISearch interface
 *
 * @author percym
 */
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "search")
@AttributeOverrides({
        @AttributeOverride(name = "id" , column = @Column(name = "search_serial")),
        @AttributeOverride(name = "createdBy" , column = @Column(name = "search_createdBy")),
        @AttributeOverride(name = "createdOn" , column = @Column(name = "search_createdOn")),
        @AttributeOverride(name = "updatedBy" , column = @Column(name = "search_updatedBy")),
        @AttributeOverride(name = "updatedOn" , column = @Column(name = "search_updatedOn")),
        @AttributeOverride(name = "startDate" , column = @Column(name = "search_startDate")),
        @AttributeOverride(name = "endDate" , column = @Column(name = "search_endDate")),
        @AttributeOverride(name = "active" , column = @Column(name = "search_active"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "search_serial_seq", allocationSize = 1)
public class Search extends Active implements ISearch<ProductConsumption> {

    @NotNull
    @Column(name = "search_search_term")
    String searchTerm;

    @Valid
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL})
    private Collection<ProductConsumption> products ;

    @NotNull
    @Column(name = "search_user_id",nullable = false)
    Long userId;


}
