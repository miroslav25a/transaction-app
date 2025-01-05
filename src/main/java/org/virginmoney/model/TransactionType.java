package org.virginmoney.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    CARD("card"),
    DIRECT_DEBIT("direct debit"),
    INTERNET("internet");

    private final String name;

    public static TransactionType findByName(final String name) {
        return Arrays.stream(TransactionType.values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type name %s.", name)));

    }
}
