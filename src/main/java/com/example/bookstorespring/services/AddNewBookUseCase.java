package com.example.bookstorespring.services;

import com.example.bookstorespring.models.EditBookModel;
import com.example.bookstorespring.repositories.BookRepository;

public class AddNewBookUseCase {

    public static void addNewBookToStore(EditBookModel book){
        BookRepository.addNewBook(book);

    }
}
