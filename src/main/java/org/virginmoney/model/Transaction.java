package org.virginmoney.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record Transaction(
        LocalDate date,
        String vendor,
        TransactionType type,
        double amount,
        TransactionCategory category) {
}
