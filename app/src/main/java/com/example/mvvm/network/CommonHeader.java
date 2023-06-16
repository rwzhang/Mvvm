package com.example.mvvm.network;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonHeader implements Interceptor {
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        String language = Locale.getDefault().getLanguage();
        String url=chain.request().url().toString();
        Request request = chain.request().newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .addHeader("phone-type", "Android")
                .build();
        return chain.proceed(request);
    }
}
