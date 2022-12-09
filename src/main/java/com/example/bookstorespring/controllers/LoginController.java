package com.example.bookstorespring.controllers;

import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.repositories.UserRepository;
import com.example.bookstorespring.models.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {


    @PostMapping("/signIn")
    public String loggingIn(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session, Model model) throws NullPointerException{

        LoginModel user = UserRepository.loginData(login);
        try {
            if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)) {
                session.setAttribute("role", user.getRole());
                session.setAttribute("userID", user.getUserID());
                model.addAttribute("cartBook", new BooksModel());

                return "redirect:/";
            } else {
                model.addAttribute("message", "Unable to login, wrong password or login");
                return "login";
            }
        }catch (NullPointerException ex){
            model.addAttribute("message", "Incorrect login");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("role");
        return "redirect:/";
    }
}
