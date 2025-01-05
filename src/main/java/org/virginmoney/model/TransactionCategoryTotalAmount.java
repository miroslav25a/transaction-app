package org.virginmoney.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record TransactionCategoryTotalAmount(
        TransactionCategory category,
        double totalAmount) {
}
