package org.virginmoney.model;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionMonthlyAverageAmountTest {

    @Test
    void shouldGetMonthlyAverageAmountRoundedHalfUpAndScaledUpToFivePlaces() {
        // Given
        final var transactionMonthlyAverageAmount = TransactionMonthlyAverageAmount.builder()
                .totalAmount(100.00)
                .numberOfTransactions(6)
                .build();
        // When
        final var result = transactionMonthlyAverageAmount.getMonthlyAverageAmount();

        // Then
        assertThat(result).isEqualTo(16.66667);
    }

    @Test
    void shouldGetMonthlyAverageAmountScaledUpToTwoPlaces() {
        // Given
        final var transactionMonthlyAverageAmount = TransactionMonthlyAverageAmount.builder()
                .totalAmount(99.00)
                .numberOfTransactions(4)
                .build();
        // When
        final var result = transactionMonthlyAverageAmount.getMonthlyAverageAmount();

        // Then
        assertThat(result).isEqualTo(24.75);
    }

    @Test
    void shouldGetMonthlyAverageAmountScaledUpToFivePlacesAndNotRoundedHalfUp() {
        // Given
        final var transactionMonthlyAverageAmount = TransactionMonthlyAverageAmount.builder()
                .totalAmount(100.00)
                .numberOfTransactions(3)
                .build();
        // When
        final var result = transactionMonthlyAverageAmount.getMonthlyAverageAmount();

        // Then
        assertThat(result).isEqualTo(33.33333);
    }
}