package com.example.mvvm.network;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvvm.bean.ApiRespond;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * 请求结果返回处理
 * @author zhangrenwei
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiRespond<R>>> {
    private Type respondtype;

    public LiveDataCallAdapter(Type respondtype) {
        this.respondtype = respondtype;
    }

    @Override
    public Type responseType() {
        return respondtype;
    }

    @Override
    public LiveData<ApiRespond<R>> adapt(final Call<R> call) {
        return new LiveData<ApiRespond<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            Log.d("zrw", "onResponse:请求耗时:" + (response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis()) + " ms");
                            if (response.isSuccessful()) {
//                                if (response.code() == 204 || response == null) {
//                                    postValue(new ApiRespond<>(response.body(), ApiRespond.Status.EMPTY, null));
//                                } else {
                                postValue(new ApiRespond<>(response.body(), ApiRespond.Status.SUCCESS, response.code(), null));
//                                }
                            } else {
                                if (response.code() == 500) {
                                    postValue(new ApiRespond<>(null, ApiRespond.Status.SERVERERROR, response.code(), "サーバーに接続できませんでした"));
                                } else if (response.code() == 403) {
                                    postValue(new ApiRespond<>(null, ApiRespond.Status.LOGINEXPIRED, response.code(), "ログイン情報の有効期限が切れました"));
                                } else if (response.code() == 401) {
                                    postValue(new ApiRespond<>(null, ApiRespond.Status.NOTLOGIN, response.code(), "ログインしていない"));
                                } else {
                                    try {
                                        if (response.errorBody() != null) {
                                            postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, response.code(), response.errorBody().string()));
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            if (t instanceof ConnectException) {
//                                AppUtils.showToast("サーバーに接続できませんでした");
                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, -201, "サーバーに接続できませんでした"));
//                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, "サーバーに接続できませんでした"));
                            } else if (t instanceof SocketTimeoutException) {
//                                AppUtils.showToast("ネットワーク接続の状態を確認してください");
                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, -202, "ネットワーク接続の状態を確認してください"));
//                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, "ネットワーク接続の状態を確認してください"));
                            } else if (t instanceof InterruptedIOException) {
//                                AppUtils.showToast("サーバーに接続できませんでした");
                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, -203, "サーバーに接続できませんでした"));
//                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, "サーバーに接続できませんでした"));
                            } else {
//                                AppUtils.showToast("エラーが発生しました");
                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, -200, "エラーが発生しました"));
                                //                                postValue(new ApiRespond<>(null, ApiRespond.Status.ERROR, "エラーが発生しました"));
                            }
                        }
                    });
                }
            }
        };
    }


}