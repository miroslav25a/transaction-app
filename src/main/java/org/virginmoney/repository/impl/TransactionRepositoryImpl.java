package org.virginmoney.repository.impl;

import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.entity.TransactionEntity;
import org.virginmoney.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final List<TransactionEntity> transactions;

    public TransactionRepositoryImpl(final List<TransactionEntity> transactions) {
        this.transactions = new ArrayList<>();
        this.transactions.addAll(transactions);
    }

    @Override
    public List<TransactionEntity> findAll() {
        return transactions;
    }

    @Override
    public List<TransactionEntity> findAllByCategory(final TransactionCategory category) {
        return transactions.stream()
                .filter(transactionEntity -> Objects.equals(transactionEntity.getCategory(), category))
                .toList();
    }

    @Override
    public void save(final TransactionEntity entity) {
        transactions.add(entity);
    }

    @Override
    public List<TransactionEntity> findAllByCategoryAndYear(final TransactionCategory category, int year) {
        return transactions.stream()
                .filter(transactionEntity -> Objects.equals(transactionEntity.getCategory(), category)
                        && Objects.equals(transactionEntity.getDate().getYear(), year))
                .toList();
    }
}
