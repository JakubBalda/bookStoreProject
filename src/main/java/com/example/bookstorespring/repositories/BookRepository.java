package com.example.bookstorespring.repositories;

import com.example.bookstorespring.DatabaseConnection;
import com.example.bookstorespring.models.EditBookModel;
import org.springframework.lang.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static ResultSet getBookByTitle(String title) throws SQLException {
        String query = "SELECT Book_ID, Title, Author_name, Author_surname, Price, Amount FROM books WHERE Title LIKE '%" + title + "%'";

        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            ResultSet result = preparedStatement.executeQuery();

            return result;
        }catch (Exception ex){
            System.out.println(ex);

            throw ex;
        }
    }

    public static void deleteBookByID(String bookID){
        String query = "DELETE FROM books WHERE Book_ID = ?";

        try{
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            preparedStatement.setString(1, bookID);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }

    }

    public static void editBookDetails(EditBookModel book){
        String query = "UPDATE books SET Title = ?, Price = ?, Author_name = ?, Author_surname = ?, Description = ?," +
                "Page_number = ?, ISBN = ?, Publisher = ?, Amount = ? WHERE Book_ID = ?";

        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setFloat(2, book.getPrice());
            preparedStatement.setString(3, book.getAuthorName());
            preparedStatement.setString(4, book.getAuthorSurname());
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setInt(6, book.getNumberOfPages());
            preparedStatement.setString(7, book.getIsbn());
            preparedStatement.setString(8, book.getPublisher());
            preparedStatement.setInt(9, book.getAmount());
            preparedStatement.setString(10, book.getBookID());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}
