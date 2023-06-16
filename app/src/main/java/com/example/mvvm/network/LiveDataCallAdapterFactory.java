package com.example.mvvm.network;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.mvvm.bean.ApiRespond;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;


/**
 * 请求结果类型转换匹配
 * @author zhangrenwei
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory{
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        //判断数据类型是否是LiveData
        if (getRawType(returnType) != LiveData.class){
            return null;
        }

        Type observable = getParameterUpperBound(0, (ParameterizedType) returnType);

        Class<?> rawType = getRawType(observable);

        if (rawType != ApiRespond.class){
            throw new IllegalArgumentException("type must be a resource");
        }
        if (!(observable instanceof ParameterizedType)) {
            throw new IllegalArgumentException("resource must be parameterized");
        }

        Type respondtype = getParameterUpperBound(0, (ParameterizedType) observable);

        return new LiveDataCallAdapter<>(respondtype);
    }
}
