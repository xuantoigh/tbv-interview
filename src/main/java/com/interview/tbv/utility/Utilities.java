package com.interview.tbv.utility;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.interview.tbv.utility.Constants.ResponseKey.MESSAGE;
import static com.interview.tbv.utility.Constants.ResponseKey.SUCCESS;

/**
 * Create by Jm on 2021-01-18
 */
public class Utilities {
    private static Map<String, Object> generateSuccessMap(){
        Map<String, Object> response = new HashMap<>();
        response.put(SUCCESS, true);
        return response;
    }

    public static Map<String, Object> generateSuccessResponse(){
        return generateSuccessMap();
    }

    public static Map<String, Object> generateSuccessResponse(String key, Object param){
        Map<String, Object> response = generateSuccessMap();
        response.put(key, param);
        return response;
    }

    private static Map<String, Object> generateErrorMap(){
        Map<String, Object> response = new HashMap<>();
        response.put(SUCCESS, false);
        return response;
    }

    public static Map<String, Object> generateErrorResponse(Exception e){
        Map<String, Object> response = generateErrorMap();
        response.putIfAbsent(MESSAGE, e.getMessage());
        return response;
    }
}
