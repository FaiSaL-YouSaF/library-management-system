package com.faisalyousaf777.factory;

import com.faisalyousaf777.entity.LibraryCard;
import com.faisalyousaf777.enums.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class LibraryCardFactory {

    public static LibraryCard createLibraryCard() {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNumber(generateCardNumber());
        libraryCard.setIssueDate(LocalDate.now());
        libraryCard.setExpiryDate(LocalDate.now().plusYears(2));
        libraryCard.setStatus(CardStatus.ACTIVE);
        libraryCard.setBooksIssued(0);
        libraryCard.setTotalFine(BigDecimal.ZERO);
        libraryCard.setBooksDueDate(new HashMap<>());
        return libraryCard;
    }

    private static String generateCardNumber() {
        return "LC-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
