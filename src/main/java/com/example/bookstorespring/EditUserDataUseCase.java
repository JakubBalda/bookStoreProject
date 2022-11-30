package com.example.bookstorespring;

import java.util.HashMap;
import java.util.Map;

public class EditUserDataUseCase {

    public static boolean editData(UserModel user, Map<String, String> allData, String userID){
        Map<String, String> newData = findDifferences(user, allData);
        if(newData.size() != 0) {
            String query = makeQuery(newData, userID);
            UserRepository.updateUserData(query);
            return true;
        }
        return false;
    }

    public static Map<String, String> findDifferences(UserModel user, Map<String, String> allData){
        Map<String, String> newUserData = new HashMap<>();
        if(allData.get("flat") == ""){
            allData.put("flat", null);
        }

        if(!user.getName().equals(allData.get("name"))){
            newUserData.put("Name", allData.get("name"));
        }

        if(!user.getSurname().equals(allData.get("surname"))){
            newUserData.put("Surname", allData.get("surname"));
        }

        if(!user.getPhoneNumber().equals(allData.get("phone"))){
            newUserData.put("Phone_number", allData.get("phone"));
        }

        if(!user.getEmail().equals(allData.get("mail"))){
            newUserData.put("Email", allData.get("mail"));
        }

        if(!user.getCity().equals(allData.get("town"))){
            newUserData.put("City", allData.get("town"));
        }

        if(!user.getStreet().equals(allData.get("street"))){
            newUserData.put("Street", allData.get("street"));
        }

        if(!user.getHouseNumber().equals(allData.get("house"))){
            newUserData.put("House_number", allData.get("house"));
        }

        if(user.getFlatNumber() != null) {
            if (!user.getFlatNumber().equals(allData.get("flat"))) {
                newUserData.put("Flat_number", allData.get("flat"));
            }
        }else{
            if (user.getFlatNumber() != allData.get("flat")) {
                newUserData.put("Flat_number", allData.get("flat"));
            }
        }

        if(!user.getPostalCode().equals(allData.get("postal"))){
            newUserData.put("Postal_code", allData.get("postal"));
        }

        if(!user.getLogin().equals(allData.get("login"))){
            newUserData.put("Login", allData.get("login"));
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
