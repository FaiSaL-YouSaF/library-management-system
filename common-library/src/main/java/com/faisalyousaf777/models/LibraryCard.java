package com.faisalyousaf777.models;


import com.faisalyousaf777.enums.CardStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class LibraryCard {

    private long cardId;
    private String cardNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private CardStatus status;
    private int booksIssued;
    private BigDecimal totalFine;
    private Map<Long, LocalDate> booksDueDate;

}
