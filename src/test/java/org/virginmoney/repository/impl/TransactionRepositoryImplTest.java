package org.virginmoney.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.repository.TransactionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.virginmoney.TransactionTestFixtures.*;

class TransactionRepositoryImplTest {
    private TransactionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TransactionRepositoryImpl(TRANSACTION_ENTITY_LIST);
    }

    @Test
    void shouldFindAll() {
        // When
        final var result = repository.findAll();

        // Then
        assertThat(result).isNotNull().containsExactlyElementsOf(TRANSACTION_ENTITY_LIST);
    }

    @Test
    void shouldFindAllByCategory() {
        // Given
        final var category = TransactionCategory.GROCERIES;
        final var expectedResult = List.of(TRANSACTION_ENTITY, TRANSACTION_ENTITY_2, TRANSACTION_ENTITY_3);

        // When
        final var result = repository.findAllByCategory(category);

        assertThat(result).isNotNull().containsExactlyElementsOf(expectedResult);
    }

    @Test
    void shouldSave() {
        // Given
        final var entity = TRANSACTION_ENTITY.toBuilder().amount(1.99).build();

        // When
        repository.save(entity);

        // Then
        final var findAllResult = repository.findAll();
        assertThat(findAllResult).contains(entity);
    }

    @Test
    void shouldFindAllByCategoryAndYear() {
        // Given
        final var category = TransactionCategory.GROCERIES;
        final var year = 2024;
        final var expectedResult = List.of(TRANSACTION_ENTITY, TRANSACTION_ENTITY_3);

        // When
        final var result = repository.findAllByCategoryAndYear(category, year);

        // Then
        assertThat(result).isNotNull().containsExactlyElementsOf(expectedResult);
    }
}