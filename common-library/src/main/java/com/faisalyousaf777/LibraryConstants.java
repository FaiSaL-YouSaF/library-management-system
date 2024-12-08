package com.faisalyousaf777;

import java.math.BigDecimal;

public class LibraryConstants {
    public static final int BOOK_ISSUE_LIMIT = 5;
    public static final int DAYS_PER_BOOK = 14;
    public static final BigDecimal FINE_PER_DAY = BigDecimal.valueOf(2.50);

    private LibraryConstants() {
    }
}
