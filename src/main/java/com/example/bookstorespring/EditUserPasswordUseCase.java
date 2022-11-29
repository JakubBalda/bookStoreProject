package com.example.bookstorespring;

public class EditUserPasswordUseCase {
    public static boolean editPassword(String newPassword, String checkPassword, int userID){
        if(newPassword.equals(checkPassword)){
            if(UserRepository.changePassword(newPassword, userID)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
