package com.example.bookstorespring.services;

import com.example.bookstorespring.models.EditBookModel;
import com.example.bookstorespring.repositories.BookRepository;

public class EditBookDataUseCase {

    public static void editBookDetails(EditBookModel book){
        BookRepository.editBookDetails(book);
    }
}
