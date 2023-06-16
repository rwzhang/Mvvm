package com.example.mvvm.viewmodel;


import androidx.lifecycle.LiveData;

import com.example.mvvm.bean.ApiRespond;
import com.example.mvvm.bean.BaseResponse;
import com.example.mvvm.bean.GetBean;

import java.util.List;

public class HttpsViewModel extends BaseViewModel {

    public LiveData<ApiRespond<BaseResponse<List<GetBean>>>> getHttps(){
        return mainApi.getHttps("6666666",190000);
    }
}
