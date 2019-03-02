package com.hussir.micromall.view;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private int code;

    private String message;

    private Object result;

    public static Response error(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response;
    }

    public static Response success(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("result", this.result);
        result.put("message", this.message);
        result.put("code", this.code);

        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
