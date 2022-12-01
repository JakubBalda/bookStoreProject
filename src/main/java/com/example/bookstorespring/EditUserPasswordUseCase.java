package com.example.bookstorespring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditUserPasswordUseCase {
    public static boolean editPassword(String newPassword, String checkPassword, String userID){

        try {
            if (newPassword.equals(checkPassword)) {
                if (passwordIsValid(newPassword)){
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

    public static boolean passwordIsValid(String password){
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        final Pattern pattern  = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
