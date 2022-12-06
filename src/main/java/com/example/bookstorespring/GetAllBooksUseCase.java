package com.example.bookstorespring;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllBooksUseCase {

    public static ArrayList getBooks(){
        ArrayList <BooksModel> books = new ArrayList<BooksModel>();

        try {
            ResultSet result = BookRepository.getAllBooks();

            if(result != null){
                while (result.next()){
                    BooksModel booksModel = new BooksModel();

                    booksModel.setTitle(result.getString(1));
                    booksModel.setAuthor(result.getString(2) + " " +result.getString(3));
                    booksModel.setPrice(Float.parseFloat(result.getString(4)));
                    booksModel.setAmount(Integer.parseInt(result.getString(5)));

                    books.add(booksModel);
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }

        return books;
    }
}
