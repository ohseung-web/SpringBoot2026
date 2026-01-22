package com.green.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.dto.BookDTO;
import com.green.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 도서 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "bookAdd";
    }

    // 도서 등록 처리
    @PostMapping("/add")
    public String add(BookDTO book, Model model) {
        String result = bookService.addBook(book);
        model.addAttribute("message", result);
        return "bookAdd";
    }

    // 도서 목록
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "bookList";
    }

    // 대여/반납 폼
    @GetMapping("/rent")
    public String rentForm() {
        return "bookRent";
    }

    // 대여
    @PostMapping("/rent")
    public String rent(@RequestParam String isbn, Model model) {
        model.addAttribute("message", bookService.rentBook(isbn));
        return "bookRent";
    }

    // 반납
    @PostMapping("/return")
    public String returnBook(@RequestParam String isbn, Model model) {
        model.addAttribute("message", bookService.returnBook(isbn));
        return "bookRent";
    }
}

