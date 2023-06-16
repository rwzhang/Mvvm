package com.example.mvvm.network;




import com.example.mvvm.BuildConfig;
import com.hjq.gson.factory.GsonFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 两种请求方式线程安全的产生不同实例
 * @author zhaoyang.cheng
 */
public class RetrofitClient {
    private Retrofit retrofit;
    private static RetrofitClient instance;
    public static HttpLoggingInterceptor httpLoggingInterceptor;
    private static final int MAX_IDLE_CONNECTIONS = 30;// 空闲连接数
    private static final long KEEP_ALLIVE_TIME = 60000L;//保持连接时间
    public static NetWorkUtils mNetWorkUtils;
    public MainApi mainApi;

    public  String type;

    private RetrofitClient() {

    }

    public static RetrofitClient getInstance(String type) {
            if (instance == null) {
                synchronized (RetrofitClient.class) {
                    if (instance == null) {
                        instance = new RetrofitClient();
                        instance.type=type;
                        instance.init();
                    }
                }
            }else{
                if(!instance.type.equals(type)){
                    synchronized (RetrofitClient.class){
                        instance=new RetrofitClient();
                        instance.type=type;
                        instance.init();
                    }
                }
            }

        return instance;
    }


    /**
     * 初始化Retrofit
     */
    private void init() {
        switch (type) {
            case "1":
                //livedata+retrofit
                retrofit = new Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .client(buildOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
                        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                        .build();
                break;
            case "0":
                //retrofit+RxJava
                retrofit = new Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .client(buildOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                break;
            default:
                break;
        }


    }

    /**
     * 获取接口实例
     *
     * @return
     */
    public MainApi getApi() {
        if (mainApi == null) {
            synchronized (this) {
                if (mainApi == null) {
                    mainApi = retrofit.create(MainApi.class);
                }
            }
        }
        return mainApi;
    }

    /**
     * 创建OkHttpClient
     *
     * @return
     */
    private OkHttpClient buildOkHttpClient() {
        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        ConnectionPool connectionPool = new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALLIVE_TIME, TimeUnit.MILLISECONDS);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new RetryInterceptor(3))//重试拦截器3次
                .addInterceptor(new CommonHeader())
                .addInterceptor(new MyInterceptor())
//                            .addNetworkInterceptor(new NetCacheInterceptor())
//                            .addInterceptor(new OfflineCacheInterceptor())
                .retryOnConnectionFailure(false)//自动重连设置为false
                .connectionPool(connectionPool)//连接池 用来管理 HTTP 和 HTTP/2 连接的重用，以减少网络延迟。
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间:秒
                .readTimeout(30, TimeUnit.SECONDS)//设置读取时间:秒
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时：秒
                .build();
    }

    public static NetWorkUtils getmNetWorkUtils() {
        if (mNetWorkUtils == null) {
            synchronized (NetWorkUtils.class) {
                if (mNetWorkUtils == null) {
                    mNetWorkUtils = new NetWorkUtils();
                }
            }
        }
        return mNetWorkUtils;
    }
}
