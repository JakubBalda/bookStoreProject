package com.example.bookstorespring.services;

import com.example.bookstorespring.repositories.UserRepository;
import com.example.bookstorespring.models.UserModel;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

public class EditUserDataUseCase {

    public static boolean editData(UserModel user, @ModelAttribute("userModelForm") UserModel userModelForm, String userID){
        Map<String, String> newData = findDifferences(user, userModelForm);
        if(newData.size() != 0) {
            String query = makeQuery(newData, userID);
            UserRepository.updateUserData(query);
            return true;
        }
        return false;
    }

    public static Map<String, String> findDifferences(UserModel user, UserModel userModelForm){
        Map<String, String> newUserData = new HashMap<>();
       /* if(allData.get("flat") == ""){
            allData.put("flat", null);
        }*/

        if(!user.getName().equals(userModelForm.getName())){
            newUserData.put("Name", userModelForm.getName());
        }

        if(!user.getSurname().equals(userModelForm.getSurname())){
            newUserData.put("Surname", userModelForm.getSurname());
        }

        if(!user.getPhoneNumber().equals(userModelForm.getPhoneNumber())){
            newUserData.put("Phone_number", userModelForm.getPhoneNumber());
        }

        if(!user.getEmail().equals(userModelForm.getEmail())){
            newUserData.put("Email", userModelForm.getEmail());
        }

        if(!user.getCity().equals(userModelForm.getCity())){
            newUserData.put("City", userModelForm.getCity());
        }

        if(!user.getStreet().equals(userModelForm.getStreet())){
            newUserData.put("Street", userModelForm.getStreet());
        }

        if(!user.getHouseNumber().equals(userModelForm.getHouseNumber())){
            newUserData.put("House_number", userModelForm.getHouseNumber());
        }

        if(user.getFlatNumber() != null) {
            if (!user.getFlatNumber().equals(userModelForm.getFlatNumber())){
                newUserData.put("Flat_number", userModelForm.getFlatNumber());
            }
        }else{
            if (user.getFlatNumber() != userModelForm.getFlatNumber()){
                newUserData.put("Flat_number", userModelForm.getFlatNumber());
            }
        }

        if(!user.getPostalCode().equals(userModelForm.getPostalCode())){
            newUserData.put("Postal_code", userModelForm.getPostalCode());
        }

        if(!user.getLogin().equals(userModelForm.getLogin())){
            newUserData.put("Login", userModelForm.getLogin());
        }

        return newUserData;
    }

    public static String makeQuery(Map<String, String> newData, String userID){

        StringBuilder query = new StringBuilder("UPDATE users SET ");
        newData.forEach((k, v) -> query.append(k + " = '" + v + "', "));
        query.setLength(query.length() -2);
        query.append(" WHERE User_ID = " + userID);

        return query.toString();
    }
}
