package com.example.bookstorespring.controllers;

import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.models.CartBookModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CartController {


    @PostMapping("/addToCart")
    public String addToCart(HttpSession session, @ModelAttribute("cartBook") CartBookModel bookToCart){

        if(session.getAttribute("booksInCart") == null){
            ArrayList<CartBookModel> booksInCart = new ArrayList<CartBookModel>();
            bookToCart = calculateFullPrice(bookToCart);
            booksInCart.add(bookToCart);
            session.setAttribute("booksInCart", booksInCart);
            session.setAttribute("fullOrderPrice", bookToCart.getFullPrice());

        }else {
            if(!isBookInCart(session.getAttribute("booksInCart"), bookToCart)) {

                Object booksInCartSession = session.getAttribute("booksInCart");
                ArrayList<CartBookModel> booksInCart = (ArrayList) booksInCartSession;
                bookToCart = calculateFullPrice(bookToCart);
                booksInCart.add(bookToCart);
                session.setAttribute("booksInCart", booksInCart);

                session.setAttribute("fullOrderPrice", fullOrderPrice(booksInCart));
            }else{
                ArrayList<CartBookModel> booksInCart = changeBookAmountInCart(session.getAttribute("booksInCart"), bookToCart);
                session.setAttribute("booksInCart", booksInCart);
                session.setAttribute("fullOrderPrice", fullOrderPrice(booksInCart));

            }
        }

        return "redirect:/";
    }

    @PostMapping("/deleteFromCart")
    public String newAmount(HttpSession session, @RequestParam("bookID") String bookID){
        Object booksInCartSession = session.getAttribute("booksInCart");
        ArrayList<CartBookModel> booksInCart = (ArrayList) booksInCartSession;

        for (Object book:booksInCart) {
            CartBookModel bookFromCart = (CartBookModel) book;

            if(bookFromCart.getBookID().equals(bookID)){
                Object fullPrice = session.getAttribute("fullOrderPrice");
                float fullOrderPrice = (float) fullPrice;
                fullOrderPrice -= bookFromCart.getFullPrice();

                if(fullOrderPrice == 0.0){
                    session.setAttribute("fullOrderPrice", null);
                }
                booksInCart.remove(bookFromCart);

                if(booksInCart.size() == 0){
                    session.setAttribute("booksInCart", null);
                }

                break;
            }
        }
        return "redirect:/myCart";
    }

    public static CartBookModel calculateFullPrice(CartBookModel bookToCart){
        bookToCart.setFullPrice(bookToCart.getPrice() * bookToCart.getAmount());

        return bookToCart;
    }

    public static float fullOrderPrice(ArrayList booksInCart){
        float fullOrderPrice = 0;

        for (Object book:booksInCart){
            CartBookModel bookToCart = (CartBookModel) book;
            fullOrderPrice += bookToCart.getFullPrice();
        }

        return fullOrderPrice;
    }

    public static boolean isBookInCart(Object booksInCart, CartBookModel bookToCart){
        ArrayList<CartBookModel> allBooksInCart = (ArrayList) booksInCart;

        for (Object book:allBooksInCart){
            CartBookModel bookFromCart = (CartBookModel) book;

            if(bookFromCart.getBookID().equals(bookToCart.getBookID())){
                return true;
            }
        }
        return false;
    }

    public static ArrayList <CartBookModel> changeBookAmountInCart(Object booksInCart, CartBookModel bookToCart){
        ArrayList<CartBookModel> allBooksInCart = (ArrayList) booksInCart;

        for (Object book:allBooksInCart){
            CartBookModel bookFromCart = (CartBookModel) book;

            if(bookFromCart.getBookID().equals(bookToCart.getBookID())){
                allBooksInCart.remove(bookFromCart);
                bookFromCart.setAmount(bookFromCart.getAmount() +1);
                calculateFullPrice(bookFromCart);
                allBooksInCart.add(bookFromCart);

                break;
            }
        }
        return allBooksInCart;
    }

    public static void resetCart(HttpSession session){

        session.setAttribute("booksInCart", null);
        session.setAttribute("fullOrderPrice", null);
    }
}
