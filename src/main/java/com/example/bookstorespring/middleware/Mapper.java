package com.example.bookstorespring.middleware;

import com.example.bookstorespring.models.BookModel;
import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.models.EditBookModel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Mapper {

    public static ArrayList<BooksModel> mapToBooksModel(ResultSet result, ArrayList<BooksModel> books) throws Exception{

        try {
            if(result != null){
                while (result.next()){
                    BooksModel booksModel = new BooksModel();

                    booksModel.setBookID(result.getString(1));
                    booksModel.setTitle(result.getString(2));
                    booksModel.setAuthor(result.getString(3) + " " +result.getString(4));
                    booksModel.setPrice(Float.parseFloat(result.getString(5)));
                    booksModel.setAmount(Integer.parseInt(result.getString(6)));

                    books.add(booksModel);
                }
            }

            return books;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public static BookModel mapToBookModel(ResultSet result, BookModel book){

        try {
            if(result != null){
                while (result.next()){
                    book.setBookID(result.getString(1));
                    book.setTitle(result.getString(2));
                    book.setPrice(Float.parseFloat(result.getString(3)));
                    book.setAuthor(result.getString(4) + " " +result.getString(5));
                    book.setDescription(result.getString(6));
                    book.setNumberOfPages(Integer.parseInt(result.getString(7)));
                    book.setIsbn(result.getString(8));
                    book.setPublisher(result.getString(9));
                    book.setAmount(Integer.parseInt(result.getString(10)));
                }
            }

            return book;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public static EditBookModel mapToEditBookModel(ResultSet result, EditBookModel book){

        try {
            if(result != null){
                while (result.next()){
                    book.setBookID(result.getString(1));
                    book.setTitle(result.getString(2));
                    book.setPrice(Float.parseFloat(result.getString(3)));
                    book.setAuthorName(result.getString(4));
                    book.setAuthorSurname(result.getString(5));
                    book.setDescription(result.getString(6));
                    book.setNumberOfPages(Integer.parseInt(result.getString(7)));
                    book.setIsbn(result.getString(8));
                    book.setPublisher(result.getString(9));
                    book.setAmount(Integer.parseInt(result.getString(10)));
                }
            }

            return book;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
