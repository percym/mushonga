package info.mushonga.search.model.general;

import info.mushonga.search.imodel.general.IProductDescription;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Active entity bean for getting active records.
 *
 * @author percym
 */
@MappedSuperclass
@Audited
public class ProductDescription extends GeneralNaming implements IProductDescription {


    private static final long serialVersionUID = 6911937956855157542L;

    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name = "zz_product_description")
    private String productDescription;

    public String getProductDescription(){
        return productDescription;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getProductDescription());
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof IProductDescription)) {
            return false;
        }
        final IProductDescription other = (IProductDescription) obj;
        return Objects.equals(getProductDescription(), other.getProductDescription());

    }
}
