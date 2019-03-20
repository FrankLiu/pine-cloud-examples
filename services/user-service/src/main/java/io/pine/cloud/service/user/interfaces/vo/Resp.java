package io.pine.cloud.service.user.interfaces.vo;

import lombok.Data;

@Data
public class Resp<T> {
    private int code;
    private String message;
    private T body;

    public Resp(int code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public static <T> Resp of(int code, String message, T body) {
        return new Resp(code, message, body);
    }

    public static <T> Resp success(T body) {
        return of(200, "OK", body);
    }

    public static <T> Resp fail(int code, String message, T body) {
        return of(code, message, body);
    }

    public static <T> Resp fail(int code, T body) {
        return fail(code, "Failed", body);
    }
}
