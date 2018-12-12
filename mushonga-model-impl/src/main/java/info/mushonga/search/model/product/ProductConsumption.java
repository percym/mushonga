package info.mushonga.search.model.product;

import info.mushonga.search.imodel.product.IProduct;
import info.mushonga.search.model.general.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *The product class for all products to be added
 *
 * @author percym
 */
@Entity
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "product_consumption")
@AttributeOverrides({
        @AttributeOverride(name = "id" , column = @Column(name = "product_consumption_serial")),
        @AttributeOverride(name = "createdBy" , column = @Column(name = "product_consumption_createdBy")),
        @AttributeOverride(name = "createdOn" , column = @Column(name = "product_consumption_createdOn")),
        @AttributeOverride(name = "updatedBy" , column = @Column(name = "product_consumption_updatedBy")),
        @AttributeOverride(name = "updatedOn" , column = @Column(name = "product_consumption_updatedOn")),
        @AttributeOverride(name = "startDate" , column = @Column(name = "product_consumption_startDate")),
        @AttributeOverride(name = "endDate" , column = @Column(name = "product_consumption_endDate")),
        @AttributeOverride(name = "active" , column = @Column(name = "active")),
        @AttributeOverride(name = "genericCode" , column = @Column(name = "product_consumption_generic_code")),
        @AttributeOverride(name = "genericName" , column = @Column(name = "product_consumption_generic_name")),
        @AttributeOverride(name = "productDescription" , column = @Column(name = "product_consumption_description")),
        @AttributeOverride(name = "lastSearchedDate" , column = @Column(name = "product_consumption_last_searched_date")),
        @AttributeOverride(name = "totalSearchedTimes" , column = @Column(name = "product_consumption_total_searched_times"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "product_consumption_serial_seq", allocationSize = 1)
public class ProductConsumption extends Active implements IProduct {


    @NotNull
    @Size(max = 10)
    @Column(name = "product_generic_code", length = 10)
    private String genericCode;


    @NotNull
    @Column(name = "product_generic_name", unique = true)
    private String genericName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_last_searched_date")
    private LocalDateTime lastSearchedDate;

    @DecimalMin("0.00")
    @Column(name = "product_total_searched_times", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal totalSearchedTimes;


    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_balance",columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal itemBalance;

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_price",columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal itemPrice;
}
