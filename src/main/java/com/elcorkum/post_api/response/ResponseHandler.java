package com.elcorkum.post_api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder (String message, HttpStatus code, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", code.value());
        response.put("data", responseObject);
        return new ResponseEntity<>(response, code);
    }

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus code){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", code.value());
        return new ResponseEntity<>(response, code);
    }
}
