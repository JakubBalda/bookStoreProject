package com.example.bookstorespring;

import org.springframework.lang.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookRepository {

    public static ResultSet getAllBooks() throws Exception{
        String query = "Select Title, Author_name, Author_surname, Price, Amount FROM books";

        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(query);
            ResultSet result = preparedStatement.executeQuery();

            return result;
        }catch (Exception ex){
            System.out.println(ex);

            throw ex;
        }

    }
}
