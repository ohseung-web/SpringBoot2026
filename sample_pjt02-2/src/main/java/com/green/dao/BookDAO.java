package com.green.dao;


import org.springframework.stereotype.Repository;

import com.green.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAO {

    private static List<BookDTO> books = new ArrayList<>();

    public void addBook(BookDTO book) {
        books.add(book);
    }

    public List<BookDTO> findAll() {
        return books;
    }

    public BookDTO findByIsbn(String isbn) {
        for (BookDTO book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}