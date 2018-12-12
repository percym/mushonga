package info.mushonga.search.imodel.search;

import info.mushonga.search.imodel.general.IAuditingEntity;
import info.mushonga.search.imodel.general.ITimeActiveRecordEntity;
import info.mushonga.search.imodel.pharmacy.IPharmacy;
import info.mushonga.search.imodel.product.IProduct;


/**
 * The interface for all search results
 *
 * @author percym
 */
public interface ISearchResult  extends IAuditingEntity,ITimeActiveRecordEntity{

    /**
     * Returns the product found in search.
     *
     * @return the product found in search.
     */
    IProduct product();

    /**
     * Sets the product found in search..
     *
     * @param product the product found in search..
     */

    void setProduct(IProduct product);

    /**
     * Returns the pharmacy found in search.
     *
     * @return the pharmacy found in search.
     */
    IPharmacy getPharmacy();

    /**
     * Returns the pharmacy found in search.
     *
     * @param   pharmacy found in search.
     */

    void setPharmacy(IPharmacy pharmacy);


}
