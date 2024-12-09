package com.faisalyousaf777.service;

import com.faisalyousaf777.LibraryConstants;
import com.faisalyousaf777.client.BookClient;
import com.faisalyousaf777.client.UserClient;
import com.faisalyousaf777.dto.BookDTO;
import com.faisalyousaf777.dto.UserDTO;
import com.faisalyousaf777.enums.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class LendingService {

    @Autowired
    private final UserClient userClient;

    @Autowired
    private final BookClient bookClient;

    public LendingService(UserClient userClient, BookClient bookClient) {
        this.userClient = userClient;
        this.bookClient = bookClient;
    }

    public BookDTO borrowBook(Long userId, Long bookId) {
        UserDTO user = userClient.getUserById(userId);
        if (isEligibleToBorrow(user)) {
            user.getLibraryCard().setBooksDueDate(new HashMap<>() {{    // Placeholder
                put(bookId, LocalDate.now().plusDays(LibraryConstants.DAYS_PER_BOOK));
            }});
            user.getLibraryCard().setBooksIssued(user.getLibraryCard().getBooksIssued() + 1);
            return bookClient.getBookById(bookId);
        } else {
            return null;
        }
    }

    public String returnBook(Long userId, Long bookId) {
        UserDTO user = userClient.getUserById(userId);
        user.getLibraryCard().setTotalFine(calculateFine(user));
        if (user.getLibraryCard().getTotalFine().compareTo(BigDecimal.ZERO) > 0) {
            return "Please pay the fine of " + user.getLibraryCard().getTotalFine();
        }
        if (user.getLibraryCard().getBooksDueDate().containsKey(bookId)) {
            user.getLibraryCard().getBooksDueDate().remove(bookId);
            return "Book returned successfully";
        } else {
            return "Book not issued to the user";
        }
    }


    private boolean isEligibleToBorrow(UserDTO user) {
        if (user.getLibraryCard() == null) {
            return false;
        }
        if (user.getLibraryCard().getStatus().equals(CardStatus.INACTIVE)) {
            return false;
        }
        return user.getLibraryCard().getBooksIssued() < LibraryConstants.BOOK_ISSUE_LIMIT;// Placeholder
    }

    private BigDecimal calculateFine(UserDTO user) {
        BigDecimal fine = BigDecimal.ZERO;
        LocalDate today = LocalDate.now();
        for (Map.Entry<Long, LocalDate> entry : user.getLibraryCard().getBooksDueDate().entrySet()) {
            LocalDate dueDate = entry.getValue();
            if (dueDate.isBefore(today)) {
                long daysLate = ChronoUnit.DAYS.between(dueDate, today);
                fine = fine.add(LibraryConstants.FINE_PER_DAY.multiply(BigDecimal.valueOf(daysLate)));
            }
        }
        return fine;
    }
}
