package com.example.bookstorespring.services;

import com.example.bookstorespring.repositories.BookRepository;

public class DeleteBookFromStoreUseCase {

    public static void deleteBook(String bookID){
        BookRepository.deleteBookByID(bookID);
    }
}
