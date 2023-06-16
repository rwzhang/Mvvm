package com.example.mvvm.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.mvvm.network.MainApi;
import com.example.mvvm.network.RetrofitClient;

public class BaseViewModel extends ViewModel {
    protected MainApi mainApi = RetrofitClient.getInstance("1").getApi();

}
