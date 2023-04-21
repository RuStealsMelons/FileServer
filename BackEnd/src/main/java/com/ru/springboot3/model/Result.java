package com.ru.springboot3.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Integer errno;
    private Map<String, String> data;
    private String message;

    public static Result success(String url, String alt, String href){
        Result result = new Result();
        result.errno = 0;
        result.data = new HashMap<>();
        result.data.put("url", url);
        result.data.put("alt", alt);
        result.data.put("href", href);
        return result;
    }

    public static Result fail(String message){
        Result result = new Result();
        result.errno = 1;
        result.message = message;
        return result;
    }
}

