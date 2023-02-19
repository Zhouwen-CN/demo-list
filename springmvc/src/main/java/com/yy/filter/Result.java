package com.yy.filter;

/**
 * @Author: chen
 * @Date: 2023/2/13 19:41
 * @Desc:
 */
public class Result {
    private Integer code;
    private Object message;

    public Result() {
    }

    public Result(Integer code, Object message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message=" + message +
                '}';
    }
}
