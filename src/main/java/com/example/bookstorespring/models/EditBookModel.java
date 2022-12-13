package com.example.bookstorespring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditBookModel {

    private String bookID;
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 40, message = "Title must be between 5 - 40 signs length")
    private String title;
    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 2, max = 40, message = "Author name must be between 2 - 40 signs length")
    private String authorName;
    @NotBlank(message = "Author surname cannot be blank")
    @Size(min = 3, max = 40, message = "Author surname must be between 5 - 40 signs length")
    private String authorSurname;
    private float price;
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 20, max = 1000, message = "Description must be between 20 - 1000 signs length")
    private String description;
    @Min(10)
    private int numberOfPages;
    @NotBlank(message = "ISBN cannot be blank")
    @Size(min = 17, max = 17, message = "ISBN must be 17 signs length")
    private String isbn;
    @NotBlank(message = "Publisher cannot be blank")
    @Size(min = 5, max = 40, message = "Publisher must be between 5 - 40 signs length")
    private String publisher;
    @Min(0)
    private int amount;

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getAmount() {
        return amount;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String author) {
        this.authorName = author;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
