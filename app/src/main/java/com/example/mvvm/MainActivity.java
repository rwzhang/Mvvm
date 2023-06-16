package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvm.bean.ApiRespond;
import com.example.mvvm.bean.BaseResponse;
import com.example.mvvm.bean.GetBean;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.viewmodel.HttpsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HttpsViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(HttpsViewModel.class);
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getHttps().observe(this, new Observer<ApiRespond<BaseResponse<List<GetBean>>>>() {
            @Override
            public void onChanged(ApiRespond<BaseResponse<List<GetBean>>> baseResponseApiRespond) {
                if(baseResponseApiRespond!=null){
                    binding.setListBean(baseResponseApiRespond.data.getData().get(0));
                    binding.setActivity(MainActivity.this);
                }
            }
        });
    }

    public void onClick(){
        Toast.makeText(this,"点击了按钮",Toast.LENGTH_LONG).show();
    }
}