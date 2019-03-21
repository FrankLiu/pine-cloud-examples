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
    public static <T> Resp of(Code code, T body) {
        return of(code.getCode(), code.getDescription(), body);
    }

    public static <T> Resp success(String message, T body) {
        return of(200, message, body);
    }

    public static <T> Resp success(T body) {
        return success("OK", body);
    }

    public static <T> Resp fail(int code, String message, T body) {
        return of(code, message, body);
    }

    public static <T> Resp fail(int code, T body) {
        return fail(code, "Failed", body);
    }

    public enum Code {
        SUCCESS(200, "OK"),
        CLIENT_ERROR(400, "Invalid Request"),
        SERVER_ERROR(500, "Internal Error");

        private int code;
        private String description;

        Code(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return this.code;
        }
        public String getDescription() {
            return this.description;
        }
    }
}
