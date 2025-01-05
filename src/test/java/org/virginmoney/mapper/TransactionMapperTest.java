package org.virginmoney.mapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.virginmoney.TransactionTestFixtures.TRANSACTION;
import static org.virginmoney.TransactionTestFixtures.TRANSACTION_ENTITY;

class TransactionMapperTest {

    @Test
    void shouldMapTransactionEntityToDto() {
        // When
        final var result = TransactionMapper.mapEntityToDto(TRANSACTION_ENTITY);

        // Then
        assertThat(result).isEqualTo(TRANSACTION);
    }

    @Test
    void shouldMapDtoToEntity() {
        // When
        final var result = TransactionMapper.mapDtoToEntity(TRANSACTION);

        // Then
        assertThat(result).isEqualTo(TRANSACTION_ENTITY);
    }

    @Test
    void shouldMapNullEntityToDto() {
        // When
        final var result = TransactionMapper.mapEntityToDto(null);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void shouldMapNullDtoToEntity() {
        // When
        final var result = TransactionMapper.mapDtoToEntity(null);

        // Then
        assertThat(result).isNull();
    }
}