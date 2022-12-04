
    package com.example.bookstorespring;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

    @Controller
    public class UserController {

        @GetMapping("/userPanel")
        public String userPanelPage(HttpSession session, Model model){
            UserModel userModel = GetUserDataUseCase.getData(session.getAttribute("userID").toString());
            model.addAttribute("userModel", userModel);

            return "userPanel";
        }

        @PostMapping("/editUserPassword")
        public String editUserPassword(Model model, HttpSession session, @RequestParam("newPassword") String newPassword, @RequestParam("checkPassword") String checkPassword){
            if(EditUserPasswordUseCase.editPassword(newPassword, checkPassword, (String) session.getAttribute("userID"))) {
                model.addAttribute("message", "Password changed");
            }else{
                model.addAttribute("message", "Password not changed, password must be strong");
            }
            return "userPanel";
        }

        @PostMapping("/editUserData")
        public String editUserData(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult result, HttpSession session){
            if(result.hasErrors()){
                System.out.println(result);
                return "redirect:userPanel";
            }
            UserModel user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());



            if (EditUserDataUseCase.editData(user, userModel, session.getAttribute("userID").toString())) {
                session.setAttribute("message", "Changes saved");
            }else{
                session.setAttribute("message", "Changes cannot be saved or any data got changed");
            }
            return "redirect:userPanel";
        }

        @GetMapping("/deleteUserAccount")
        public String deleteUserAccount(HttpSession session, Model model){
            try {
                UserRepository.deleteAccount(session.getAttribute("userID").toString());
            }catch (Exception ex){
                model.addAttribute("deleteError", "Deleting account failed, try again later!");
                return "redirect:userPanel";
            }
            return "redirect:logout";
        }
    }


