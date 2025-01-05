package org.virginmoney;

import org.virginmoney.model.*;
import org.virginmoney.repository.impl.TransactionRepositoryImpl;
import org.virginmoney.service.TransactionService;
import org.virginmoney.service.impl.TransactionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.virginmoney.model.TransactionCategory.GROCERIES;
import static org.virginmoney.model.TransactionCategory.MY_MONTHLY_DIRECT_DEBIT;

public class Main {
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionServiceImpl(
                new TransactionRepositoryImpl(new ArrayList<>()));
        var transactions = createTransactions();

        for (Transaction transaction : transactions) {
            transactionService.save(transaction);
        }

        var categoryTransactions = transactionService.getAllByCategory(GROCERIES);
        displayAllTransactionsByCategory(categoryTransactions);

        var totalAmountsPerTransaction = transactionService.getTotalAmountPerCategory();
        displayTotalAmountPerCategory(totalAmountsPerTransaction);

        var monthlyAverageAmounts = transactionService.getMonthlyAmountAverageByCategory(GROCERIES);
        displayMonthlyAmountAverageByCategory(monthlyAverageAmounts);

        var highestSpendTransaction = transactionService.getSpendByCategoryAndYear(
                MY_MONTHLY_DIRECT_DEBIT, 2020, true);
        displaySpendTransaction(highestSpendTransaction, "Highest spend in MyMonthlyDD category, for 2020 year:");

        var lowestSpendTransaction = transactionService.getSpendByCategoryAndYear(
                MY_MONTHLY_DIRECT_DEBIT, 2020, false);
        displaySpendTransaction(lowestSpendTransaction, "Lowest spend in MyMonthlyDD category, for 2020 year:");
    }

    private static void displayAllTransactionsByCategory(final List<Transaction> categoryTransactions) {
        System.out.println("All transactions for Groceries category - latest first:");
        for (Transaction transaction : categoryTransactions) {
            System.out.println(transaction.toString());
        }
    }

    private static void displayTotalAmountPerCategory(
            final List<TransactionCategoryTotalAmount> totalAmountsPerTransaction) {
        System.out.println("Total outgoing per category:");
        for (TransactionCategoryTotalAmount totalAmountPerTransaction: totalAmountsPerTransaction) {
            System.out.println(totalAmountPerTransaction.toString());
        }
    }

    private static void displayMonthlyAmountAverageByCategory(
            final List<TransactionMonthlyAverageAmount> monthlyAverageAmounts) {
        System.out.println("Monthly average spend in Groceries category:");
        for (TransactionMonthlyAverageAmount monthlyAverageAmount : monthlyAverageAmounts) {
            System.out.println(monthlyAverageAmount.toString());
        }
    }

    private static void displaySpendTransaction(final Transaction transaction, final String title) {
        System.out.println(title);
        System.out.println(transaction.toString());
    }

    private static List<Transaction> createTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.builder()
                .date(LocalDate.of(2020, 11,1))
                .vendor("Morrisons")
                .type(TransactionType.findByName("card"))
                .amount(10.40)
                .category(TransactionCategory.findByName("Groceries"))
                .build());
        transactions.add(Transaction.builder()
                .date(LocalDate.of(2020, 10,28))
                .vendor("CYBG")
                .type(TransactionType.findByName("direct debit"))
                .amount(600.00)
                .category(TransactionCategory.findByName("MyMonthlyDD"))
                .build());
        transactions.add(Transaction.builder()
                .date(LocalDate.of(2020, 10,28))
                .vendor("PureGym")
                .type(TransactionType.findByName("direct debit"))
                .amount(40.00)
                .category(TransactionCategory.findByName("MyMonthlyDD"))
                .build());
        transactions.add(Transaction.builder()
                .date(LocalDate.of(2020, 10,1))
                .vendor("M&S")
                .type(TransactionType.findByName("card"))
                .amount(5.99)
                .category(TransactionCategory.findByName("Groceries"))
                .build());
        transactions.add(Transaction.builder()
                .date(LocalDate.of(2020, 9,30))
                .vendor("McMillan")
                .type(TransactionType.findByName("internet"))
                .amount(10.00)
                .build());

        return transactions;
    }
}