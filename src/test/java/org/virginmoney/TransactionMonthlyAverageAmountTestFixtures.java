package org.virginmoney;

import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionMonthlyAverageAmount;

public enum TransactionMonthlyAverageAmountTestFixtures {;
    public static final int NUMBER_OF_TRANSACTIONS = 6;
    public static final double TOTAL_AMOUNT = 100.00;
    public static final int YEAR = 2020;
    public static final int MONTH = 8;
    public static final TransactionCategory CATEGORY = TransactionCategory.MY_MONTHLY_DIRECT_DEBIT;

    public static final TransactionMonthlyAverageAmount TRANSACTION_MONTHLY_AVERAGE_AMOUNT =
            TransactionMonthlyAverageAmount.builder()
                    .numberOfTransactions(NUMBER_OF_TRANSACTIONS)
                    .totalAmount(TOTAL_AMOUNT)
                    .month(YEAR)
                    .month(MONTH)
                    .category(CATEGORY)
                    .build();
}
