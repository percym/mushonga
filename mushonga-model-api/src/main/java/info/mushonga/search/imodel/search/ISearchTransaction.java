package info.mushonga.search.imodel.search;


import info.mushonga.search.imodel.general.ICosting;

import java.math.BigDecimal;

/**
 * ISearchTransaction interface search summary per user logs searches with results
 *
 * @author percym
 */
public interface ISearchTransaction extends ICosting {

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
     * Gets the count totalQueryResults for this search.
     *
     * @return  the count  totalQueryResults  for this search.
     */
    BigDecimal getTotalQueryResults();
    /**
     * Gets the count totalQueryResults for this search.
     *
     * @param   totalQueryResults count  for this search.
     */
    void setTotalQueryResults(BigDecimal totalQueryResults);

    /**
     * Returns the transactionCost for this search.
     *
     * @return the transactionCost  for this search.
     */
    BigDecimal getTransactionCost();

    /**
     * Returns the transactionCost for this search.
     *
     * @param transactionCost for this search.
     */
    void setTransactionCost(BigDecimal transactionCost);

    /**
     * Returns the balanceAfterSearch for this search.
     *
     * @return the balanceAfterSearch  for this search.
     */
    BigDecimal getBalanceAfterSearch();

    /**
     * Returns the balanceAfterSearch for this search.
     *
     * @param balanceAfterSearch for this search.
     */
    void setBalanceAfterSearch(BigDecimal balanceAfterSearch);

}
