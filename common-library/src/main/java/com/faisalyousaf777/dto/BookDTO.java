package com.faisalyousaf777.dto;

import com.faisalyousaf777.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDTO {

    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String publicationDate;
    private BookStatus status;

    public BookDTO() {
    }

}
