package com.example.bookstorespring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    public static PreparedStatement dbConnection(String query){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greatbook", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            return preparedStatement;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
