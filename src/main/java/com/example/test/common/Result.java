package com.example.test.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private Object data;
    private String msg;


    public static Result success () {
        return new Result(Constants.SUCCESS, null, "");
    }

    public static Result success (Object data) {
        return new Result(Constants.SUCCESS, data, "");
    }

    public static Result error () {
        return new Result(Constants.ERROR, null, "");
    }

    public static Result error (Object data) {
        return new Result(Constants.ERROR, data, "");
    }
}
