package com.faisalyousaf777.service;

import com.faisalyousaf777.LibraryConstants;
import com.faisalyousaf777.client.LendingBookClient;
import com.faisalyousaf777.client.LendingUserClient;
import com.faisalyousaf777.dto.BookDTO;
import com.faisalyousaf777.dto.UserDTO;
import com.faisalyousaf777.enums.BookStatus;
import com.faisalyousaf777.enums.CardStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LendingService {

    @Autowired
    private final LendingBookClient lendingBookClient;

    @Autowired
    private final LendingUserClient lendingUserClient;

    public LendingService(LendingBookClient lendingBookClient, LendingUserClient lendingUserClient) {
        this.lendingBookClient = lendingBookClient;
        this.lendingUserClient = lendingUserClient;
    }

    public BookDTO issueBook(final Long userId, final Long bookId) {
        log.info("Issuing book with id: {} to user with id: {}", bookId, userId);
        ResponseEntity<UserDTO> user = lendingUserClient.getUserById(userId);
        log.info("User: {}", user);
        assert user != null;
        return null;
//        if (isEligibleToBorrow(user)) {
//            user.getLibraryCard().setBooksDueDate(new HashMap<>() {{    // Placeholder
//                put(bookId, LocalDate.now().plusDays(LibraryConstants.DAYS_PER_BOOK));
//            }});
//            user.getLibraryCard().setBooksIssued(user.getLibraryCard().getBooksIssued() + 1);
//            lendingBookClient.updateBookStatusById(bookId, BookStatus.ISSUED);       // Update book status to ISSUED
//            return lendingBookClient.getBookById(bookId);
//        } else {
//            // Throw exceptions for cases
//            return null;
//        }
    }

    public String returnBook(final Long userId, final Long bookId) {
        UserDTO user = lendingUserClient.getUserById(userId).getBody();
        assert user != null;
        if (user.getLibraryCard().getTotalFine().compareTo(BigDecimal.ZERO) > 0) {
            return "Please pay the fine of " + user.getLibraryCard().getTotalFine();
        }
        if (user.getLibraryCard().getBooksDueDate().containsKey(bookId)) {
            user.getLibraryCard().setTotalFine(calculateFine(user));
            user.getLibraryCard().getBooksDueDate().remove(bookId);
            lendingBookClient.updateBookStatusById(bookId, BookStatus.AVAILABLE);    // Update book status to AVAILABLE
            return "Book returned successfully";
        } else {
            // Throw exceptions for invalid bookId
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
        return user.getLibraryCard().getBooksIssued() < LibraryConstants.BOOK_ISSUE_LIMIT;
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
