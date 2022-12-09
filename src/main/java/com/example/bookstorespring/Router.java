package com.example.bookstorespring;

import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class Router {

    @GetMapping("/myCart")
        public String cart(HttpSession session){

            return "cart";
        }

    @PostMapping("/addToCart")
    public String addToCart(HttpSession session, @ModelAttribute("cartBook") BooksModel bookToCart){
        if(session.getAttribute("booksInCart") == null){
            ArrayList<BooksModel> booksInCart = new ArrayList<BooksModel>();
            booksInCart.add(bookToCart);
            session.setAttribute("booksInCart", booksInCart);

        }else {
            Object booksInCartSession = session.getAttribute("booksInCart");
            ArrayList <BooksModel> booksInCart = (ArrayList) booksInCartSession;
            booksInCart.add(bookToCart);
            session.setAttribute("booksInCart", booksInCart);
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        UserModel userModel = new UserModel();
        model.addAttribute("userModel", userModel);

        return "register";
    }



}
