package com.example.bookstorespring;

import java.sql.ResultSet;

public class GetUserDataUseCase {

    public static UserModel getData(String userID){
        ResultSet userData = UserRepository.userData(userID);

        UserModel user = new UserModel();

        try{
            if(userData != null){
                while(userData.next()){
                    user.setName(userData.getString(1));
                    user.setSurname(userData.getString(2));
                    user.setPhoneNumber(userData.getString(3));
                    user.setEmail(userData.getString(4));
                    user.setCity(userData.getString(5));
                    user.setStreet(userData.getString(6));
                    user.setHouseNumber(userData.getString(7));
                    user.setFlatNumber(userData.getString(8));
                    user.setPostalCode(userData.getString(9));
                    user.setLogin(userData.getString(10));
                }
            }

            return user;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
