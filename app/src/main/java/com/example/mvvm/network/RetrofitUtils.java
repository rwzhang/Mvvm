package com.example.mvvm.network;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvvm.bean.GetBean;
import com.example.mvvm.bean.PostBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 请求封装
 * @author zhangrenwei
 */
public   class RetrofitUtils {

    public static  void getHttps(LifecycleOwner owner, BaseObserver<List<GetBean>> observer){
        RetrofitClient
                .getInstance("0")
                .getApi()
                .getHttp("666666", 190000)
                .compose(RxHelper.observableIO2Main(owner))
                .subscribe(observer);
    }

    public static  void getGsons(String keyword,LifecycleOwner owner, BaseObserver<PostBean> observer){
        RetrofitClient.getInstance("0")
                .getApi()
                .postJsons("6666666",keyword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
