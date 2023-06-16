package com.example.mvvm.network;


import android.util.Log;

import com.example.mvvm.bean.BaseResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 数据返回统一处理
 * @param <E>
 */
public abstract class BaseObserver<E> implements Observer<BaseResponse<E>> {
    private static final String TAG = "BaseObserver";
    Disposable mDisposable;

    public Disposable getDisposable() {
        return mDisposable;
    }


    @Override
    public void onNext(BaseResponse<E> response) {
        Log.d("zrw", "onNext: ");
        //在这边对 基础数据 进行统一处理
        if(response!=null){
            onSuccess(response.getData());
        }
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
        Log.d("zrw", "onError: ");
    }

    @Override
    public void onComplete() {
        Log.d("zrw", "onComplete: ");
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        Log.d("zrw", "onSubscribe: ");
    }

    public abstract void onSuccess(E result);

    public abstract void onFailure(Throwable e,String errorMsg);
    public void onNeedEmail(){};

}

