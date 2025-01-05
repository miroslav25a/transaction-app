package org.virginmoney.service.impl;

import lombok.RequiredArgsConstructor;
import org.virginmoney.mapper.TransactionMapper;
import org.virginmoney.model.Transaction;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionCategoryTotalAmount;
import org.virginmoney.model.TransactionMonthlyAverageAmount;
import org.virginmoney.repository.TransactionRepository;
import org.virginmoney.service.TransactionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.virginmoney.mapper.TransactionMapper.mapDtoToEntity;
import static org.virginmoney.mapper.TransactionMapper.mapEntityToDto;

@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllByCategory(final TransactionCategory category) {
        final var categoryTransactions = transactionRepository.findAllByCategory(category);
        final var sortedCategoryTransactions = new ArrayList<>(categoryTransactions);
        sortedCategoryTransactions.sort((transaction1, transaction2) -> transaction2.getDate()
                .compareTo(transaction1.getDate()));

        return sortedCategoryTransactions.stream()
                .map(TransactionMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public List<TransactionCategoryTotalAmount> getTotalAmountPerCategory() {
        final var transactions = transactionRepository.findAll();
        final var categoryTotalAmounts = new HashMap<TransactionCategory, TransactionCategoryTotalAmount>();

        transactions.forEach(transactionEntity -> {
            if (categoryTotalAmounts.containsKey(transactionEntity.getCategory())) {
                final var categoryTotalAmount = categoryTotalAmounts.get(transactionEntity.getCategory());
                final var totalAmount = BigDecimal.valueOf(categoryTotalAmount.totalAmount())
                        .add(BigDecimal.valueOf(transactionEntity.getAmount()));
                final var newCategoryTotalAmount = categoryTotalAmount.toBuilder()
                        .totalAmount(totalAmount.doubleValue())
                        .build();
                categoryTotalAmounts.put(transactionEntity.getCategory(), newCategoryTotalAmount);
            } else {
                categoryTotalAmounts.put(transactionEntity.getCategory(), TransactionCategoryTotalAmount.builder()
                                .category(transactionEntity.getCategory())
                                .totalAmount(transactionEntity.getAmount())
                        .build());
            }
        });

        return categoryTotalAmounts.values().stream().toList();
    }

    @Override
    public List<TransactionMonthlyAverageAmount> getMonthlyAmountAverageByCategory(final TransactionCategory category) {
        final var transactions = transactionRepository.findAllByCategory(category);
        final var monthlyAverageAmounts = new HashMap<String, TransactionMonthlyAverageAmount>();
        transactions.forEach(transactionEntity -> {
            final var monthKey = transactionEntity.getDate().getMonthValue() + "/"
                    + transactionEntity.getDate().getYear();
            if (monthlyAverageAmounts.containsKey(monthKey)) {
                final var monthlyAverageAmount = monthlyAverageAmounts.get(monthKey);
                final var newMonthlyAverageAmount = monthlyAverageAmount.toBuilder()
                        .totalAmount(BigDecimal.valueOf(monthlyAverageAmount.totalAmount())
                                .add(BigDecimal.valueOf(transactionEntity.getAmount()))
                                .doubleValue())
                        .numberOfTransactions(monthlyAverageAmount.numberOfTransactions() + 1)
                        .build();
                monthlyAverageAmounts.put(monthKey, newMonthlyAverageAmount);
            } else {
                monthlyAverageAmounts.put(monthKey, TransactionMonthlyAverageAmount.builder()
                        .totalAmount(transactionEntity.getAmount())
                        .numberOfTransactions(1)
                        .category(transactionEntity.getCategory())
                        .yeah(transactionEntity.getDate().getYear())
                        .month(transactionEntity.getDate().getMonthValue())
                        .build());
            }
        });

        return monthlyAverageAmounts.values().stream().toList();
    }

    @Override
    public Transaction getSpendByCategoryAndYear(final TransactionCategory category, int year, boolean highest) {
        final var transactions = transactionRepository.findAllByCategoryAndYear(category, year);
        final var sortedCategoryTransactions = new ArrayList<>(transactions);
        sortedCategoryTransactions.sort((transaction1, transaction2) -> Double.compare(
                transaction2.getAmount(), transaction1.getAmount()));

        if (highest) {
            return mapEntityToDto(sortedCategoryTransactions.getFirst());
        }

        return mapEntityToDto(sortedCategoryTransactions.getLast());
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(mapDtoToEntity(transaction));
    }
}
