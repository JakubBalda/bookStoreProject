package com.example.bookstorespring.services;

import com.example.bookstorespring.middleware.Mapper;
import com.example.bookstorespring.repositories.BookRepository;
import com.example.bookstorespring.models.BooksModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllBooksUseCase {

    public static ArrayList getBooks(String sortType){
        ArrayList <BooksModel> books = new ArrayList<BooksModel>();

        try {
            ResultSet result = BookRepository.getAllBooks(sortType);
            books = Mapper.mapToBooksModel(result, books);

        }catch (Exception ex){
            System.out.println(ex);
        }

        return books;
    }
}
