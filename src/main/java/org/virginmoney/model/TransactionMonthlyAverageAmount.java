package org.virginmoney.model;

import lombok.Builder;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Builder(toBuilder = true)
public record TransactionMonthlyAverageAmount(
        TransactionCategory category,
        Integer month,
        Integer yeah,
        double totalAmount,
        int numberOfTransactions) {
    public double getMonthlyAverageAmount() {
        return BigDecimal.valueOf(totalAmount)
                .divide(BigDecimal.valueOf(numberOfTransactions), 5, HALF_UP)
                .doubleValue();
    }
}
