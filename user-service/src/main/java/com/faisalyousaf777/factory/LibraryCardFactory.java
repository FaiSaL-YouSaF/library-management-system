package com.faisalyousaf777.factory;

import com.faisalyousaf777.entity.LibraryCard;

import java.math.BigDecimal;
import java.util.UUID;

public class LibraryCardFactory {

    public static LibraryCard createLibraryCard() {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNumber(generateCardNumber());
        libraryCard.setIssueDate(java.time.LocalDate.now());
        libraryCard.setExpiryDate(java.time.LocalDate.now().plusYears(2));
        libraryCard.setStatus(com.faisalyousaf777.enums.CardStatus.ACTIVE);
        libraryCard.setBooksIssued(0);
        libraryCard.setTotalFine(BigDecimal.ZERO);
        libraryCard.setBooksDueDate(new java.util.HashMap<>());
        return libraryCard;
    }

    private static String generateCardNumber() {
        return "LC-" + UUID.randomUUID().toString().substring(0, 5) + "-" + UUID.randomUUID().toString().substring(0, 5);
    }
}
