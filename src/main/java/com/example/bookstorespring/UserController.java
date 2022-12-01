package com.example.bookstorespring;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/userPanel")
    public String userPanelPage(HttpSession session, Model model){
        UserModel user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());
        model.addAttribute("userModelForm", user);

        return "userPanel";
    }

    @PostMapping("/editUserPassword")
    public String editUserPassword(Model model,HttpSession session ,@RequestParam("newPassword") String newPassword, @RequestParam("checkPassword") String checkPassword){
        if(EditUserPasswordUseCase.editPassword(newPassword, checkPassword, (String) session.getAttribute("userID"))) {
            model.addAttribute("message", "Password changed");
        }else{
            model.addAttribute("message", "Password not changed, password must be strong");
        }
        return "userPanel";
    }

    @PostMapping("/editUserData")
    public String editUserData(@Valid UserModel userModelForm, HttpSession session){

        UserModel user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());

        if (EditUserDataUseCase.editData(user, userModelForm, session.getAttribute("userID").toString())) {
            session.setAttribute("message", "Changes saved");
        }else{
            session.setAttribute("message", "Changes cannot be saved or any data got changed");
        }
        return "redirect:userPanel";
    }
}
