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

    public static void deleteAccount(String userID) throws Exception{
        String query = "DELETE FROM users WHERE User_ID = ?";

        try {
            PreparedStatement preparedStatement = dbConnection(query);
            preparedStatement.setString(1, userID);

            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);

        }
    }

    //Register

    public static void addUser(UserModel newUser) throws Exception{
        String query = "INSERT INTO users (Name, Surname, Phone_number, Email, City, " +
                "Street, House_number, Flat_Number, Postal_code, Role, Login, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConnection(query);
            preparedStatement = prepareInsertQuery(newUser, preparedStatement);
            preparedStatement.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }
    
    }

    public static PreparedStatement prepareInsertQuery(UserModel newUser, PreparedStatement preparedStatement){
        try {
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getSurname());
            preparedStatement.setString(3, newUser.getPhoneNumber());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getCity());
            preparedStatement.setString(6, newUser.getStreet());
            preparedStatement.setString(7, newUser.getHouseNumber());
            preparedStatement.setString(8, newUser.getFlatNumber());
            preparedStatement.setString(9, newUser.getPostalCode());
            preparedStatement.setString(10, "Client");
            preparedStatement.setString(11, newUser.getLogin());
            preparedStatement.setString(12, newUser.getPassword());
        }catch (Exception ex){
            System.out.println(ex);
        }

        return preparedStatement;
    }

    public static boolean isLoginTaken(String login){
        String query = "SELECT login FROM users WHERE login = ?";
        String[] parameters = new String[]{login};

        ResultSet loginExists = select(query, parameters);

        if(loginExists == null){
            return false;
        }

        return true;
    }
}
