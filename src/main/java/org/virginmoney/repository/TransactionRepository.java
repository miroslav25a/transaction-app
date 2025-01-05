package org.virginmoney.repository;

import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.entity.TransactionEntity;

import java.util.List;

/**
 * Generic repository operations for a transaction.
 *
 * @author miroslav
 */
public interface TransactionRepository {

    /**
     * Get all transactions
     *
     * @return a list of transaction entities
     */
    List<TransactionEntity> findAll();

    /**
     * Get all transactions for a given category.
     *
     * @param category to obtain transactions
     * @return a list of transaction entities
     */
    List<TransactionEntity> findAllByCategory(TransactionCategory category);

    /**
     * Save a singe transaction.
     *
     * @param entity to save
     */
    void save(TransactionEntity entity);

    /**
     * Get all transactions for a given category and year.
     *
     * @param category to obtain transactions
     * @param year to obtain transactions
     * @return a list of transaction entities
     */
    List<TransactionEntity> findAllByCategoryAndYear(TransactionCategory category, int year);

}
