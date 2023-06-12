package com.raqtpie.springsecuritydemo.common;

import com.raqtpie.springsecuritydemo.constant.ResponseResultCode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.SUCCESS.code)
                .data(data)
                .build();
    }

    public static <T> ResponseResult<T> success(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.SUCCESS.code)
                .msg(msg)
                .build();
    }

    public static <T> ResponseResult<T> success(T data,String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.SUCCESS.code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> ResponseResult<T> fail() {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.ERROR.code)
                .msg("fail")
                .build();
    }

    public static <T> ResponseResult<T> fail(Throwable e) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.ERROR.code)
                .msg(e.getMessage())
                .build();
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.ERROR.code)
                .msg(msg)
                .build();
    }

    public static <T> ResponseResult<T> unauthorized(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.UNAUTHORIZED.code)
                .msg(msg)
                .build();
    }

    public static <T> ResponseResult<T> badRequest(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.BAD_REQUEST.code)
                .msg(msg)
                .build();
    }

    public static <T> ResponseResult<T> notFound(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.NOT_FOUND.code)
                .msg(msg)
                .build();
    }

    public static <T> ResponseResult<T> forbidden(String msg) {
        return ResponseResult.<T>builder()
                .code(ResponseResultCode.FORBIDDEN.code)
                .msg(msg)
                .build();
    }
}
