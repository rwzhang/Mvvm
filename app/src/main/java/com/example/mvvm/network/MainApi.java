package com.example.mvvm.network;

import androidx.lifecycle.LiveData;

import com.example.mvvm.bean.ApiRespond;
import com.example.mvvm.bean.BaseResponse;
import com.example.mvvm.bean.GetBean;
import com.example.mvvm.bean.PostBean;
import com.example.mvvm.bean.UrlsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * API接口
 *
 * @author zhangrenwei
 */
public interface MainApi {

    @GET("://api.github.com/")
    LiveData<BaseResponse<UrlsBean>> getGithubHttp();

    @GET("://api.github.com/")
    Observable<BaseResponse<UrlsBean>> getGithubHttps();

    @GET("wxarticle/chapters/json")
    LiveData<ApiRespond<BaseResponse<List<GetBean>>>> getHttps(@Query ("token") String token, @Query("id") int id);
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<GetBean>>> getHttp(@Query ("token") String token, @Query("id") int id);
    @POST("article/query/0/json")
    @FormUrlEncoded
    LiveData<ApiRespond<BaseResponse<PostBean>>> postJson(@Field ("token") String token, @Field("k") String keyword);

    @POST("article/query/0/json")
    @FormUrlEncoded
    Observable<BaseResponse<PostBean>> postJsons(@Field("token") String token,@Field("k") String keyword);

    @POST("article/query/0/json")
    @FormUrlEncoded
    Call<BaseResponse<PostBean>> postJsonser(@Field("token") String token, @Field("k") String keyword);
}
