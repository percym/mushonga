package info.mushonga.search.imodel.account;


import info.mushonga.search.imodel.general.ICosting;

import java.util.Collection;

/**
 * IAccount interface for accounts
 * @author percym
 * @param <T> any class that extends IPayment
 * @param <T> any class that extends ITransaction
 */
public interface IAccount <T extends IPayment, U extends  ISearchTransaction> extends ICosting {

    /**
     * Returns the accountNumber for this account.
     *
     * @return the accountNumber  for this account.
     */
    String getAccountNumber();
    /**
     * Sets the accountNumber for this account.
     *
     * @param  accountNumber  for this account.
     */
    void setAccountNumber(String accountNumber);

    /**
     * Returns the list of {@link IPayment payment} for this account.
     *
     * @return the list of {@link IPayment payment}  for this account.
     */
    Collection<T> getPayments();

    /**
     * Sets thelist of {@link IPayment payment} for this account.
     *
     * @param payments the list of {@link IPayment payment} for this account.
     */
    void setPayments(Collection<T> payments);

    /**
     * Returns the list of {@link ISearchTransaction searchTransaction}  for this account.
     *
     * @return the list of {@link ISearchTransaction searchTransaction}  for this account.
     */
    Collection<U> getSearchTransactions();

    /**
     * Sets thelist of {@link ISearchTransaction searchTransactions} for this account.
     *
     * @param searchTransactions the list of {@link ISearchTransaction searchTransaction} for this account.
     */
    void setSearchTransactions(Collection<U> searchTransactions);
}
