package com.example.bookstorespring.models;

public class BooksModel {

    private String bookID;
    private String title;
    private String author;
    private float price;
    private int amount;

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
