package com.green.dto;

public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    public BookDTO() {}

    public BookDTO(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
