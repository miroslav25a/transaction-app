package org.virginmoney;

import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionCategoryTotalAmount;
import org.virginmoney.model.TransactionMonthlyAverageAmount;

import java.util.List;

import static org.virginmoney.TransactionTestFixtures.*;

public enum TransactionCategoryTotalAmountTestFixtures {;
    public static final double TOTAL_AMOUNT = 300.60;
    public static final double TOTAL_AMOUNT_2 = AMOUNT_4;

    public static final TransactionCategoryTotalAmount TRANSACTION_CATEGORY_TOTAL_AMOUNT =
            TransactionCategoryTotalAmount.builder()
                    .totalAmount(TOTAL_AMOUNT)
                    .category(CATEGORY)
                    .build();

    public static final TransactionCategoryTotalAmount TRANSACTION_CATEGORY_TOTAL_AMOUNT_2 =
            TransactionCategoryTotalAmount.builder()
                    .totalAmount(TOTAL_AMOUNT_2)
                    .category(CATEGORY_4)
                    .build();

    public static final List<TransactionCategoryTotalAmount> TRANSACTION_CATEGORY_TOTAL_LIST = List.of(
            TRANSACTION_CATEGORY_TOTAL_AMOUNT, TRANSACTION_CATEGORY_TOTAL_AMOUNT_2);
}
