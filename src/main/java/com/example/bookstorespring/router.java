package com.example.bookstorespring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class router {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/userPanel")
    public String userPanelPage(){ return "userPanel"; }

}
