package com.example.bookstorespring.controllers;

import com.example.bookstorespring.repositories.UserRepository;
import com.example.bookstorespring.models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @PostMapping("/registerNewUser")
    public String registerNewUser(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult result, Model model){

        if(result.hasErrors()){
            return "register";
        }
        try {
            if(!UserRepository.isLoginTaken(userModel.getLogin())) {
                UserRepository.addUser(userModel);
            }else {
                model.addAttribute("loginErrorMessage", "Login is already taken.");

                return "register";
            }
        }catch (Exception ex){
            model.addAttribute("errorMessage", "Unable to register, try again");

            return "register";
        }

        return "redirect:login";
    }
}
