package org.virginmoney.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.virginmoney.model.TransactionCategory.GROCERIES;

class TransactionCategoryTest {

    @Test
    void shouldFindByName() {
        // When
        final var result = TransactionCategory.findByName("Groceries");

        // Then
        assertThat(result).isEqualTo(GROCERIES);
    }

    @Test
    void shouldHandleUnknownName() {
        // When
        assertThatThrownBy(() -> {
            TransactionCategory.findByName("Cloths");
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Unsupported category name Cloths.");
    }
}