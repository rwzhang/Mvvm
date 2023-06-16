package com.example.mvvm.network;


import androidx.lifecycle.Observer;

import com.example.mvvm.bean.ApiRespond;
import com.example.mvvm.bean.BaseResponse;


public abstract class BasseObserver<T> implements Observer<ApiRespond<BaseResponse<T>>> {

    @Override
    public void onChanged(ApiRespond<BaseResponse<T>> baseResponseApiRespond) {
        switch (baseResponseApiRespond.status){
            case SUCCESS:
                if(baseResponseApiRespond.data!=null&&baseResponseApiRespond.data.getData()!=null&&baseResponseApiRespond.code==200){
                    onResult(baseResponseApiRespond.data.getData());
                }
                break;
            case EMPTY:
                break;
            case ERROR:
                onError(baseResponseApiRespond.data);
                break;
            case NOTLOGIN:
                break;
            case SERVERERROR:
                break;
            case LOGINEXPIRED:
                break;
            default:break;


        }

    }
    public abstract void  onResult(T result);
    public abstract  void onError(BaseResponse error);
}
