package com.example.bookstorespring.services;

import com.example.bookstorespring.middleware.Mapper;
import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.repositories.BookRepository;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerchBookUseCase {

    public static ArrayList<BooksModel> getBookByTitle(String title){
        ArrayList<BooksModel> book = new ArrayList<BooksModel>();

        try {
            ResultSet result = BookRepository.getBookByTitle(title);
            book = Mapper.mapToBooksModel(result, book);

            return book;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
