package com.example.bookstorespring.services;

import com.example.bookstorespring.middleware.Mapper;
import com.example.bookstorespring.models.BookModel;
import com.example.bookstorespring.models.EditBookModel;
import com.example.bookstorespring.repositories.BookRepository;

import java.sql.ResultSet;
import java.util.Map;

public class GetBookDetailsUseCase {

    public static BookModel bookDetails(String bookID){
        BookModel book = new BookModel();

        try{
            ResultSet result = BookRepository.getBookDetails(bookID);
            book = Mapper.mapToBookModel(result, book);

        }catch (Exception ex){
            System.out.println(ex);
        }

        return book;
    }

    public static EditBookModel bookDetailsToEdit(String bookID){
        EditBookModel book = new EditBookModel();

        try{
            ResultSet result = BookRepository.getBookDetails(bookID);
            book = Mapper.mapToEditBookModel(result, book);

        }catch (Exception ex){
            System.out.println(ex);
        }

        return book;
    }
}
