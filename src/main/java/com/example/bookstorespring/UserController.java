package com.example.bookstorespring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping("/userPanel")
    public String userPanelPage(Model model, HttpSession session){
        UserDTO user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());
        model.addAttribute("user", user);
        return "userPanel";
    }
}
