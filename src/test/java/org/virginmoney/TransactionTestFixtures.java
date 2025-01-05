package org.virginmoney;

import org.virginmoney.model.Transaction;
import org.virginmoney.model.TransactionCategory;
import org.virginmoney.model.TransactionType;
import org.virginmoney.model.entity.TransactionEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.virginmoney.model.TransactionCategory.GROCERIES;
import static org.virginmoney.model.TransactionCategory.MY_MONTHLY_DIRECT_DEBIT;
import static org.virginmoney.model.TransactionType.*;

public enum TransactionTestFixtures {;
    public static final LocalDate DATE = LocalDate.of(2024, Month.DECEMBER, 15);
    public static final double AMOUNT = 99.99;
    public static final TransactionType TYPE = DIRECT_DEBIT;
    public static final TransactionCategory CATEGORY = GROCERIES;
    public static final String VENDOR = "M&S";
    public static final LocalDate DATE_2 = LocalDate.of(2023, Month.DECEMBER, 15);
    public static final double AMOUNT_2 = 150.11;
    public static final TransactionType TYPE_2 = CARD;
    public static final String VENDOR_2 = "Tesco";
    public static final LocalDate DATE_3 = LocalDate.of(2024, Month.NOVEMBER, 15);
    public static final double AMOUNT_3 = 50.50;
    public static final String VENDOR_3 = "Ocado";
    public static final TransactionType TYPE_3 = INTERNET;
    public static final TransactionCategory CATEGORY_4 = MY_MONTHLY_DIRECT_DEBIT;
    public static final double AMOUNT_4 = 25.97;
    public static final String VENDOR_4 = "Sky";
    public static final LocalDate DATE_4 = LocalDate.of(2024, Month.DECEMBER, 10);

    public static final TransactionEntity TRANSACTION_ENTITY = TransactionEntity.builder()
            .date(DATE)
            .amount(AMOUNT)
            .type(TYPE)
            .category(CATEGORY)
            .vendor(VENDOR)
            .build();

    public static final TransactionEntity TRANSACTION_ENTITY_2 = TransactionEntity.builder()
            .date(DATE_2)
            .amount(AMOUNT_2)
            .type(TYPE_2)
            .category(CATEGORY)
            .vendor(VENDOR_2)
            .build();

    public static final TransactionEntity TRANSACTION_ENTITY_3 = TransactionEntity.builder()
            .date(DATE_3)
            .amount(AMOUNT_3)
            .type(TYPE_3)
            .category(CATEGORY)
            .vendor(VENDOR_3)
            .build();

    public static final TransactionEntity TRANSACTION_ENTITY_4 = TransactionEntity.builder()
            .date(DATE_4)
            .amount(AMOUNT_4)
            .type(DIRECT_DEBIT)
            .category(CATEGORY_4)
            .vendor(VENDOR_4)
            .build();

    public static final List<TransactionEntity> TRANSACTION_ENTITY_LIST = List.of(
            TRANSACTION_ENTITY, TRANSACTION_ENTITY_2, TRANSACTION_ENTITY_3, TRANSACTION_ENTITY_4);

    public static final Transaction TRANSACTION = Transaction.builder()
            .date(DATE)
            .amount(AMOUNT)
            .type(TYPE)
            .category(CATEGORY)
            .vendor(VENDOR)
            .build();

    public static final Transaction TRANSACTION_2 = Transaction.builder()
            .date(DATE_2)
            .amount(AMOUNT_2)
            .type(TYPE_2)
            .category(CATEGORY)
            .vendor(VENDOR_2)
            .build();

    public static final Transaction TRANSACTION_3 = Transaction.builder()
            .date(DATE_3)
            .amount(AMOUNT_3)
            .type(TYPE_3)
            .category(CATEGORY)
            .vendor(VENDOR_3)
            .build();

    public static final Transaction TRANSACTION_4 = Transaction.builder()
            .date(DATE_4)
            .amount(AMOUNT_4)
            .type(DIRECT_DEBIT)
            .category(CATEGORY_4)
            .vendor(VENDOR_4)
            .build();
}
