package org.virginmoney.model.entity;

import lombok.Builder;
import lombok.Data;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionType;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
public class TransactionEntity {
    private LocalDate date;
    private String vendor;
    private TransactionType type;
    private double amount;
    private TransactionCategory category;
}
