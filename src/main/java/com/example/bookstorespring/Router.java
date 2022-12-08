package com.example.bookstorespring;

import com.example.bookstorespring.models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

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
