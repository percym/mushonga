package info.mushonga.search.model.product;

import info.mushonga.search.imodel.product.IProduct;
import info.mushonga.search.model.general.Statistics;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

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
@Table(schema = "data", name = "product")
//@AttributeOverrides({
//        @AttributeOverride(name = "id" , column = @Column(name = "product_serial")),
//        @AttributeOverride(name = "createdBy" , column = @Column(name = "product_createdBy")),
//        @AttributeOverride(name = "createdOn" , column = @Column(name = "product_createdOn")),
//        @AttributeOverride(name = "updatedBy" , column = @Column(name = "product_createdBy")),
//        @AttributeOverride(name = "updatedOn" , column = @Column(name = "product_createdOn")),
//        @AttributeOverride(name = "startDate" , column = @Column(name = "product_startDate")),
//        @AttributeOverride(name = "endDate" , column = @Column(name = "product_endDate")),
//        @AttributeOverride(name = "endDate" , column = @Column(name = "product_endDate")),
//        @AttributeOverride(name = "active" , column = @Column(name = "active")),
//        @AttributeOverride(name = "genericCode" , column = @Column(name = "product_generic_code")),
//        @AttributeOverride(name = "genericName" , column = @Column(name = "product_generic_name")),
//        @AttributeOverride(name = "lastSearchedDate" , column = @Column(name = "product_last_searched_date")),
//        @AttributeOverride(name = "totalSearchedTimes" , column = @Column(name = "product_total_searched_times"))
//})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "product_serial_seq", allocationSize = 1)
public class Product extends Statistics implements IProduct {

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_balance")
    private BigDecimal itemBalance;

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_price")
    private BigDecimal itemPrice;

}
