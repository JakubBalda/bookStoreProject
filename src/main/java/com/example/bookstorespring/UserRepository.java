package com.example.bookstorespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static ResultSet databaseResults(String query, String firstQueryParameter){

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greatbook", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, firstQueryParameter);
            ResultSet rs = preparedStatement.executeQuery();

            return rs;
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

        return null;
    }


    public static String[] loginData(String login){
        String[] userLoginData = new String[4];
        String query = "SELECT User_ID, Login, Password, Role FROM Users WHERE Login = ?";

        ResultSet result = databaseResults(query, login);
        try{
            if(result != null) {
                while (result.next()) {
                    userLoginData[0] = result.getString(1);
                    userLoginData[1] = result.getString(2);
                    userLoginData[2] = result.getString(3);
                    userLoginData[3] = result.getString(4);
                }

                return userLoginData;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public static ResultSet userData(String userID){
        String query = "SELECT Name, Surname, Phone_number, Email, City, Street, House_number, Flat_number, " +
                "Postal_code, Login FROM Users WHERE User_ID = ?";

        ResultSet result = databaseResults(query, userID);
        if(result != null){
            return result;
        }
        return null;
    }
}
