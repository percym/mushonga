package info.mushonga.search.model.search;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.mushonga.search.imodel.search.ISearch;
import info.mushonga.search.model.general.AuditingEntityBean;
import info.mushonga.search.model.product.Product;
import info.mushonga.search.model.user.SystemUser;
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
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
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
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "product_serial_seq", allocationSize = 1)
public class Search extends AuditingEntityBean implements ISearch<Product> {

    @NotNull
    @Column(name = "search_search_term")
    String searchTerm;

    @Valid
    @OneToMany(fetch = FetchType.LAZY,   cascade = {CascadeType.ALL})
    private Collection<Product> products = new ArrayList<>();


}
