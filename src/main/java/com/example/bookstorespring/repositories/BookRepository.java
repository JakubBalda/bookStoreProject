package com.example.bookstorespring.repositories;

import com.example.bookstorespring.DatabaseConnection;
import org.springframework.lang.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookRepository {

    public static ResultSet getAllBooks(String sortType) throws Exception{
        String query = new String();
        if(sortType.equals("ASC")) {
             query = "Select Book_ID, Title, Author_name, Author_surname, Price, Amount FROM books ORDER BY Book_ID ASC";
        }else{
             query = "Select Book_ID, Title, Author_name, Author_surname, Price, Amount FROM books ORDER BY Book_ID DESC";
        }
        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            ResultSet result = preparedStatement.executeQuery();

            return result;
        }catch (Exception ex){
            System.out.println(ex);

            throw ex;
        }

    }

    public static ResultSet getBookDetails(String bookID) throws Exception{

        String query = "Select Book_ID, Title, Price, Author_name, Author_surname, Description, Page_number, ISBN, Publisher, Amount " +
                "FROM books WHERE Book_ID = ?";

        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            preparedStatement.setString(1, bookID);
            ResultSet result = preparedStatement.executeQuery();

            return result;
        }catch (Exception ex){
            System.out.println(ex);

            throw ex;
        }
    }
}
