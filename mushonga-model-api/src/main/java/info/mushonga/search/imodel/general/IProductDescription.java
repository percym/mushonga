package info.mushonga.search.imodel.general;

/**
 * @author percym
 */
public interface IProductDescription extends IGenericNaming {
    /**
     * Returns the productDescription for this item.
     *
     * @return the productDescription for this item.
     */
    String getProductDescription();

    /**
     * Sets the productDescription for this item.
     *
     * @param productDescription the genericName for this item.
     */
    void setProductDescription(String productDescription);
}
