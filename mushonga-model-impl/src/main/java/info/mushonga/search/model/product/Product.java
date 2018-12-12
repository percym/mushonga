package info.mushonga.search.model.product;

import info.mushonga.search.imodel.product.IProduct;
import info.mushonga.search.model.general.Active;
import info.mushonga.search.model.general.Statistics;
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
import org.springframework.context.annotation.Primary;


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
@Indexed
@AnalyzerDef(name = "product_analyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                })
        })
@Data
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(schema = "data", name = "product")
@AttributeOverrides({
        @AttributeOverride(name = "id" , column = @Column(name = "product_serial")),
        @AttributeOverride(name = "createdBy" , column = @Column(name = "product_createdBy")),
        @AttributeOverride(name = "createdOn" , column = @Column(name = "product_createdOn")),
        @AttributeOverride(name = "updatedBy" , column = @Column(name = "product_updatedBy")),
        @AttributeOverride(name = "updatedOn" , column = @Column(name = "product_updatedOn")),
        @AttributeOverride(name = "startDate" , column = @Column(name = "product_startDate")),
        @AttributeOverride(name = "endDate" , column = @Column(name = "product_endDate")),
        @AttributeOverride(name = "active" , column = @Column(name = "active")),
        @AttributeOverride(name = "genericCode" , column = @Column(name = "product_generic_code")),
        @AttributeOverride(name = "genericName" , column = @Column(name = "product_generic_name")),
        @AttributeOverride(name = "productDescription" , column = @Column(name = "product_description")),
        @AttributeOverride(name = "lastSearchedDate" , column = @Column(name = "product_last_searched_date")),
        @AttributeOverride(name = "totalSearchedTimes" , column = @Column(name = "product_total_searched_times"))
})
@SequenceGenerator(name = "default_seq", schema = "data", sequenceName = "product_serial_seq", allocationSize = 1)
public class Product extends Active implements IProduct {


    @NotNull
    @Size(max = 10)
    @Column(name = "product_generic_code", length = 10)
    private String genericCode;

    @Analyzer(definition = "product_analyzer")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull
    @Column(name = "product_generic_name", unique = true)
    private String genericName;

    @Analyzer(definition = "product_analyzer")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_last_searched_date")
    private LocalDateTime lastSearchedDate;

    @DecimalMin("0.00")
    @Column(name = "product_total_searched_times", columnDefinition = "numeric(12,2) default '0.00'")
    private BigDecimal totalSearchedTimes;


    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_balance")
    private BigDecimal itemBalance;

    @DecimalMin("0.00")
    @DecimalMax("999999999999.00")
    @Column(name = "product_item_price")
    private BigDecimal itemPrice;
}
