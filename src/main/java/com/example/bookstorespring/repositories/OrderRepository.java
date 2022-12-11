package com.example.bookstorespring.repositories;

import com.example.bookstorespring.DatabaseConnection;
import com.example.bookstorespring.models.CartBookModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRepository {

    public static void addNewOrder(ArrayList booksToOrder, String userID, float fullOrderPrice) throws SQLException {
        String insertOrderQuery = "INSERT INTO orders (Price, Order_status, User) VALUES (?,?,?)";
        String selectOrderIDQuery = "SELECT Order_ID from ORDERS ORDER BY Order_ID DESC LIMIT 1";
        String insertBookOrderQuery = "INSERT INTO orders_books (Book_ID, Order_ID, Amount) VALUES (?,?,?)";
        String updateQuery = "UPDATE books SET Amount = (Amount - ?) WHERE Book_ID = ?";
        String orderID = new String();

        try {
            PreparedStatement preparedStatement = DatabaseConnection.dbConnection(insertOrderQuery);
            preparedStatement.setFloat(1, fullOrderPrice);
            preparedStatement.setString(2, "Ordered");
            preparedStatement.setString(3, userID);
            preparedStatement.executeUpdate();

            preparedStatement = DatabaseConnection.dbConnection(selectOrderIDQuery);
            ResultSet result = preparedStatement.executeQuery();

            if(result != null) {
                while (result.next()) {
                    orderID = result.getString(1);
                }
            }


            for (Object book:booksToOrder) {
                CartBookModel bookFromCart = (CartBookModel) book;

                preparedStatement = DatabaseConnection.dbConnection(insertBookOrderQuery);
                preparedStatement.setString(1,bookFromCart.getBookID());
                preparedStatement.setString(2, orderID);
                preparedStatement.setInt(3,bookFromCart.getAmount());
                preparedStatement.executeUpdate();

                preparedStatement = DatabaseConnection.dbConnection(updateQuery);
                preparedStatement.setInt(1, bookFromCart.getAmount());
                preparedStatement.setString(2, bookFromCart.getBookID());
                preparedStatement.executeUpdate();
            }



        }catch (Exception ex){
            System.out.println(ex);

            throw ex;
        }

    }
}
