package org.virginmoney.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.virginmoney.model.TransactionCategory.GROCERIES;
import static org.virginmoney.model.TransactionType.CARD;

class TransactionTypeTest {

    @Test
    void shouldFindByName() {
        // When
        final var result = TransactionType.findByName("card");

        // Then
        assertThat(result).isEqualTo(CARD);
    }

    @Test
    void shouldHandleUnknownName() {
        // When
        assertThatThrownBy(() -> {
            TransactionType.findByName("interest");
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Unsupported type name interest.");
    }
}