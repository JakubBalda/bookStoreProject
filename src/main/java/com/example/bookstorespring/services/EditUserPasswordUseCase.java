package com.example.bookstorespring.services;

import com.example.bookstorespring.repositories.UserRepository;
import com.example.bookstorespring.middleware.Validator;

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
