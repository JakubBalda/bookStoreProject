package com.example.bookstorespring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/userPanel")
    public String userPanelPage(HttpSession session){
        UserModel user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());
        session.setAttribute("user", user);

        return "userPanel";
    }

    @PostMapping("/editUserPassword")
    public String editUserPassword(Model model,HttpSession session ,@RequestParam("newPassword") String newPassword, @RequestParam("checkPassword") String checkPassword){
        if(EditUserPasswordUseCase.editPassword(newPassword, checkPassword, (String) session.getAttribute("userID"))) {
            model.addAttribute("message", "Hasło poprawnie zmienione");
        }else{
            model.addAttribute("message", "Hasło nie zostało zmienione, sprawdź poprawność danych");
        }
        return "userPanel";
    }

    @PostMapping("/editUserData")
    public String editUserData(@RequestParam Map<String, String> allRequestedParams, HttpSession session){

        UserModel user = GetUserDataUseCase.getData(session.getAttribute("userID").toString());

        if (EditUserDataUseCase.editData(user, allRequestedParams, session.getAttribute("userID").toString())) {
            session.setAttribute("message", "Zmiany zostały zapisane");
        }else{
            session.setAttribute("message", "Zmiany nie zostały zapisane");
        }
        return "redirect:userPanel";
    }
}
