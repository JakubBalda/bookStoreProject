package com.example.bookstorespring.controllers;

import com.example.bookstorespring.models.CartBookModel;
import com.example.bookstorespring.repositories.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class OrderController {

    @PostMapping("/newOrder")
    public String addNewOrder(HttpSession session){
        Object booksInCartSession = session.getAttribute("booksInCart");
        ArrayList<CartBookModel> booksInCart = (ArrayList) booksInCartSession;

        Object fullPrice = session.getAttribute("fullOrderPrice");
        float fullOrderPrice = (float) fullPrice;

        try {
            OrderRepository.addNewOrder(booksInCart, session.getAttribute("userID").toString(), fullOrderPrice);
            CartController.resetCart(session);
        }catch (SQLException ex){
            System.out.println(ex);
        }


        return "redirect:/";
    }
}
