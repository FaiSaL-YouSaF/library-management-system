package com.faisalyousaf777.entity;


import com.faisalyousaf777.enums.CardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "library_card_table")
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false)
    private Long cardId;

    @Column(name = "card_number", nullable = false, unique = true)
    @Size(min = 6, max = 16)
    private String cardNumber;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.ACTIVE;

    @Column(name = "books_issued", nullable = false)
    @PositiveOrZero
    private int booksIssued;        //Number of books currently issued to the user

    @Column(name = "total_fine", nullable = false)
    @ColumnDefault("0.0")
    @PositiveOrZero
    private BigDecimal totalFine = BigDecimal.ZERO;           //Total fine due on the card

    @ElementCollection
    @CollectionTable(name = "books_due_date", joinColumns = @JoinColumn(name = "card_id"))
    @MapKeyJoinColumn(name = "book_id")
    @Column(name = "due_date")
    private Map<Long, LocalDate> booksDueDate = new HashMap<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public LibraryCard(String cardNumber,
                       LocalDate issueDate,
                       LocalDate expiryDate,
                       CardStatus status,
                       int booksIssued,
                       BigDecimal totalFine,
                       Map<Long, LocalDate> booksDueDate,
                       User user) {
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.booksIssued = booksIssued;
        this.totalFine = totalFine;
        this.booksDueDate = booksDueDate;
        this.user = user;
    }


    public String toString() {
        return "LibraryCard [cardId=" + cardId + ", " +
                "cardNumber=" + cardNumber + ", " +
                "issueDate=" + issueDate + ", " +
                "expiryDate=" + expiryDate + ", " +
                "status=" + status + ", " +
                "booksIssued=" + booksIssued + ", " +
                "totalFine=" + totalFine + "]";
    }
}
