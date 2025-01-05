package org.virginmoney.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.repository.TransactionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.virginmoney.TransactionCategoryTotalAmountTestFixtures.TRANSACTION_CATEGORY_TOTAL_LIST;
import static org.virginmoney.TransactionMonthlyAverageAmountTestFixtures.TRANSACTION_MONTHLY_AVERAGE_AMOUNT;
import static org.virginmoney.TransactionTestFixtures.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    public static final int YEAR_SPEND = 2024;
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl service;

    @Test
    void shouldGetAllByCategory() {
        // Given
        final var category = TransactionCategory.GROCERIES;
        final var transactionEntities = List.of(TRANSACTION_ENTITY_2, TRANSACTION_ENTITY_3, TRANSACTION_ENTITY);
        given(transactionRepository.findAllByCategory(category)).willReturn(transactionEntities);

        // When
        final var result = service.getAllByCategory(category);

        // Then
        assertThat(result).isNotNull().size().isEqualTo(3);
        assertThat(result).first().isEqualTo(TRANSACTION);
        assertThat(result).last().isEqualTo(TRANSACTION_2);
        assertThat(result).element(1).isEqualTo(TRANSACTION_3);
    }

    @Test
    void shouldGetTotalAmountPerCategory() {
        // Given
        given(transactionRepository.findAll()).willReturn(TRANSACTION_ENTITY_LIST);

        // When
        final var result = service.getTotalAmountPerCategory();

        // Then
        assertThat(result).isNotNull().containsAll(TRANSACTION_CATEGORY_TOTAL_LIST);
    }

    @Test
    void shouldGetMonthlyAmountAverageByCategory() {
        // Given;
        final var transactionEntities = List.of(
                TRANSACTION_ENTITY_2.toBuilder().amount(1.11).date(DATE_2.minusDays(1)).build(),
                TRANSACTION_ENTITY_2.toBuilder().amount(88.76).date(DATE_2.minusDays(2)).build(),
                TRANSACTION_ENTITY_2.toBuilder().amount(23.43).date(DATE_2.plusDays(1)).build(),
                TRANSACTION_ENTITY_2.toBuilder().amount(89.12).date(DATE_2.plusDays(1)).build(),
                TRANSACTION_ENTITY.toBuilder().amount(17.26).date(DATE.minusDays(1)).build(),
                TRANSACTION_ENTITY.toBuilder().amount(4.99).date(DATE.minusDays(2)).build(),
                TRANSACTION_ENTITY.toBuilder().amount(201.99).date(DATE.minusDays(3)).build(),
                TRANSACTION_ENTITY.toBuilder().amount(243.43).date(DATE.minusDays(4)).build());
        final var expectedResult = List.of(TRANSACTION_MONTHLY_AVERAGE_AMOUNT.toBuilder()
                        .category(CATEGORY)
                        .totalAmount(202.42)
                        .numberOfTransactions(4)
                        .month(DATE_2.getMonthValue())
                        .yeah(DATE_2.getYear())
                        .build(),
                TRANSACTION_MONTHLY_AVERAGE_AMOUNT.toBuilder()
                        .category(CATEGORY)
                        .totalAmount(467.67)
                        .numberOfTransactions(4)
                        .month(DATE.getMonthValue())
                        .yeah(DATE.getYear())
                        .build());
        given(transactionRepository.findAllByCategory(CATEGORY)).willReturn(transactionEntities);

        // When
        final var result = service.getMonthlyAmountAverageByCategory(CATEGORY);

        // Then
        assertThat(result).isNotNull().containsAll(expectedResult);
    }

    @Test
    void shouldGetHighestSpendByCategoryAndYear() {
        // Given
        given(transactionRepository.findAllByCategoryAndYear(CATEGORY, YEAR_SPEND)).willReturn(TRANSACTION_ENTITY_LIST);

        // When
        final var result = service.getSpendByCategoryAndYear(CATEGORY, YEAR_SPEND, true);

        // Then
        assertThat(result).isNotNull().isEqualTo(TRANSACTION_2);
    }

    @Test
    void shouldGetLowestSpendByCategoryAndYear() {
        // Given
        given(transactionRepository.findAllByCategoryAndYear(CATEGORY, YEAR_SPEND)).willReturn(TRANSACTION_ENTITY_LIST);

        // When
        final var result = service.getSpendByCategoryAndYear(CATEGORY, YEAR_SPEND, false);

        // Then
        assertThat(result).isNotNull().isEqualTo(TRANSACTION_4);
    }
}