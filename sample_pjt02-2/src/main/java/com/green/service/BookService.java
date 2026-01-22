package com.green.service;

import com.green.dao.BookDAO;
import com.green.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public String addBook(BookDTO book) {
        bookDAO.addBook(book);
        return "도서 등록 완료";
    }

    public List<BookDTO> getBooks() {
        return bookDAO.findAll();
    }

    public String rentBook(String isbn) {
     BookDTO book = bookDAO.findByIsbn(isbn);

        if (book == null)
            return "존재하지 않는 도서";

        if (!book.isAvailable())
            return "이미 대여중";

        book.setAvailable(false);
        return "대여 완료";
    }

    public String returnBook(String isbn) {
        com.green.dto.BookDTO book = bookDAO.findByIsbn(isbn);

        if (book == null)
            return "존재하지 않는 도서";

        if (book.isAvailable())
            return "이미 반납됨";

        book.setAvailable(true);
        return "반납 완료";
    }
}