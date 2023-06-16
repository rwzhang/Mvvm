package com.example.mvvm.bean;

/**
 * date:2020/3/31 0031
 * time:下午 2:46
 * author:zhaoyang.cheng
 */
public class ApiRespond<R> {
    public enum Status {
        SUCCESS,//数据请求成功且有数据
        ERROR,//数据请求失败
        EMPTY,//数据请求成功但是数据为null
        NOTLOGIN,//未登录
        LOGINEXPIRED,//登录过期
        SERVERERROR//服务器错误
    }

    //数据
    public R data;
    //状态
    public Status status;
    //说明
    public String exp;
    public int code;

    public ApiRespond(R data, Status status, int code, String exp) {
        this.data = data;
        this.status = status;
        this.exp = exp;
        this.code=code;
    }
}
