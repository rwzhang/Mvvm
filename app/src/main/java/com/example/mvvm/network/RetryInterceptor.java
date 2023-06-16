package com.example.mvvm.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 重试过滤器
 * @author zhangrenwei
 */
public class RetryInterceptor implements Interceptor {
    public int maxRetryCount;
    private int count = 0;

    public RetryInterceptor(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        return retry(chain);
    }

    public Response retry(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        try {
            while (response.code()!=200 &&response.code()!=201&&response.code()!=401&&response.code()!=403&&response.code()!=404&&response.code()!=400&&response.code()!=500&& count < maxRetryCount) {
                Log.d("重试", "retry: 1");
                count++;
                response = retry(chain);
            }
        } catch (Exception e) {
            while (response!=null&&response.code()!=200 &&response.code()!=201&&response.code()!=401&&response.code()!=403&&response.code()!=404&&response.code()!=400&&response.code()!=500&&count < maxRetryCount) {
                Log.d("重试", "retry: 2");
                count++;
                response = retry(chain);
            }
        }
        return response;
    }
}
