package com.example.bookstorespring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean passwordIsValid(String password){
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        final Pattern pattern  = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
