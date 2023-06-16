package com.example.mvvm.network;

/**
 * 接口回调请求结果
 * @author zhangrenwei
 */
public interface ResultListener<T> {
    void onResult(T result);
    void onError(String message);
}
