package info.mushonga.search.imodel.product;


import info.mushonga.search.imodel.general.IProductDescription;
import info.mushonga.search.imodel.general.IStatistics;

import java.math.BigDecimal;

/**
 * IProduct the interface for the product class
 * »»————> add relationship with pharmacy
 * @author percym
 */
public interface IProduct extends IProductDescription, IStatistics {

    /**
     * Returns the quantity of the item in the system
     *
     * @return the quantity of the  item in the system
     */
    BigDecimal getItemBalance();

    /**
     * Sets the balance of the  item in the system.
     *
     * @param itemBalance the balance system.
     */
    void setItemBalance(BigDecimal itemBalance);

    /**
     * Returns the cost of the item in the system
     *
     * @return the cost of the  item in the system
     */
    BigDecimal getItemPrice();

    /**
     * Sets the price of the  item in the system.
     *
     * @param itemPrice the price system.
     */
    void setItemPrice(BigDecimal itemPrice);
}
