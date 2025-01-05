package org.virginmoney.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.virginmoney.model.Transaction;
import org.virginmoney.model.entity.TransactionEntity;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static Transaction mapEntityToDto(final TransactionEntity entity) {
        return Optional.ofNullable(entity)
                .map(transactionEntity -> Transaction.builder()
                        .amount(transactionEntity.getAmount())
                        .type(transactionEntity.getType())
                        .date(transactionEntity.getDate())
                        .vendor(transactionEntity.getVendor())
                        .category(transactionEntity.getCategory())
                        .build())
                .orElse(null);
    }

    public static TransactionEntity mapDtoToEntity(final Transaction dto) {
        return Optional.ofNullable(dto)
                .map(transaction -> TransactionEntity.builder()
                        .category(transaction.category())
                        .amount(transaction.amount())
                        .type(transaction.type())
                        .date(transaction.date())
                        .vendor(transaction.vendor())
                        .build())
                .orElse(null);
    }
}
