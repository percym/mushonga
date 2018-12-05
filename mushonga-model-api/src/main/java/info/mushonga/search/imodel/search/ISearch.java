package info.mushonga.search.imodel.search;


import info.mushonga.search.imodel.general.IAuditingEntity;
import info.mushonga.search.imodel.general.ITimeActiveRecordEntity;
import info.mushonga.search.imodel.product.IProduct;

import java.util.Collection;

/**
 *ISearch interface for all searches
 *
 * @author percym
 */
public interface ISearch <T extends IProduct>extends IAuditingEntity, ITimeActiveRecordEntity {
    /**
     * Returns the searchTerm for this search.
     *
     * @return the searchTerm  for this search.
     */
    String getSearchTerm();

    /**
     * Sets the searchTerm for this search.
     *
     * @param  searchTerm  for this search.
     */
    void setSearchTerm(String searchTerm);

    /**
     * Returns the searched products for this search.
     *
     * @return the searched products  for this search.
     */
    Collection<T> getProducts();

    /**
     * Sets the searched product for this search.
     *
     * @param  products  searched for this search.
     */
    void setProducts(Collection<T> products);
}
