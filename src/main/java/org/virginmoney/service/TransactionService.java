package org.virginmoney.service;

import org.virginmoney.model.Transaction;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionCategoryTotalAmount;
import org.virginmoney.model.TransactionMonthlyAverageAmount;

import java.util.List;

/**
 * Contains common functionality for a transaction
 *
 * @author miroslav
 */
public interface TransactionService {

    /**
     * Get all transactions for a given category.
     *
     * @param category to obtain transactions for
     * @return a list of transactions
     */
    List<Transaction> getAllByCategory(TransactionCategory category);

    /**
     * Get total amount spent for each category.
     *
     * @return a list of total amounts per category
     */
    List<TransactionCategoryTotalAmount> getTotalAmountPerCategory();

    /**
     * Get monthly average amount for a given category.
     *
     * @param category to obtain monthly average amounts
     * @return a list of average monthly amounts
     */
    List<TransactionMonthlyAverageAmount> getMonthlyAmountAverageByCategory(TransactionCategory category);

    /**
     * Get highest or lowest transaction for a given category and a year.
     *
     * @param category to obtain a transaction for
     * @param year to obtain a transaction for
     * @param highest true otherwise for lowest false
     * @return a transaction
     */
    Transaction getSpendByCategoryAndYear(TransactionCategory category, int year, boolean highest);

    /**
     * Create a new transaction
     *
     * @param transaction to be created
     */
    void save(Transaction transaction);
}
