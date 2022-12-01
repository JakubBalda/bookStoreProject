package com.example.bookstorespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public static ResultSet select(String query, String[] parameters){

        try {
            PreparedStatement preparedStatement = dbConnection(query);
            for(int i = 0; i<parameters.length; i++) {
                preparedStatement.setString(i+1, parameters[i]);
            }
            ResultSet rs = preparedStatement.executeQuery();

            return rs;
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

        return null;
    }

    public static Boolean update(String query, String[] parameters){
        try{
            PreparedStatement preparedStatement = dbConnection(query);
            for(int i = 0; i<parameters.length; i++) {
                preparedStatement.setString(i+1, parameters[i]);
            }
            preparedStatement.executeUpdate();

            return true;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public static LoginModel loginData(String login){
        String query = "SELECT User_ID, Login, Password, Role FROM Users WHERE Login = ?";
        String[] parameters = new String[]{login};
        LoginModel loginData = new LoginModel();

        ResultSet result = select(query, parameters);
        try{
            if(result != null) {
                while (result.next()) {
                    loginData.setUserID(result.getString(1));
                    loginData.setLogin(result.getString(2));
                    loginData.setPassword(result.getString(3));
                    loginData.setRole(result.getString(4));
                }

                return loginData;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public static ResultSet userData(String userID){
        String query = "SELECT Name, Surname, Phone_number, Email, City, Street, House_number, Flat_number, " +
                "Postal_code, Login FROM Users WHERE User_ID = ?";
        String[] parameters = new String[]{userID};

        ResultSet result = select(query, parameters);
        if(result != null){
            return result;
        }
        return null;
    }

    public static boolean changePassword(String newPassword, String userID) throws NullPointerException{
        String query = "UPDATE users SET Password = ? WHERE User_ID = ?";
        String[] parameters = new String[]{newPassword, userID};

        if(update(query, parameters)){
            return true;
        }
        return false;
    }

    public static boolean updateUserData(String query){
        PreparedStatement preparedStatement = dbConnection(query);
        try {
            preparedStatement.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
