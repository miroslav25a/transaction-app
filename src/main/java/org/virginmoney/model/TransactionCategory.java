package org.virginmoney.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TransactionCategory {
    GROCERIES("Groceries"),
    MY_MONTHLY_DIRECT_DEBIT("MyMonthlyDD");

    private final String name;

    public static TransactionCategory findByName(final String name) {
        return Arrays.stream(TransactionCategory.values())
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported category name %s.", name)));

    }
}
