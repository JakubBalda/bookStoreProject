package com.example.bookstorespring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditUserPasswordUseCase {
    public static boolean editPassword(String newPassword, String checkPassword, String userID){

        try {
            if (newPassword.equals(checkPassword)) {
                if (Validator.passwordIsValid(newPassword)){
                    if (UserRepository.changePassword(newPassword, userID)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }catch (NullPointerException ex){
            System.out.println("null");
        }
        return false;
    }


}
