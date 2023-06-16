package com.example.mvvm.network;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 过滤器
 * @author zhangrenwei
 */
public class MyInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        Charset charset;
        charset = Charset.forName("UTF-8");
        ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
        Reader jsonReader = new InputStreamReader(responseBody.byteStream(), charset);
        BufferedReader reader = new BufferedReader(jsonReader);
        StringBuilder sbJson = new StringBuilder();
        String line = reader.readLine();
        do {
            sbJson.append(line);
            line = reader.readLine();
        } while (line != null);
//        Log.e("本次请求", "response: " + sbJson.toString());
        if (sbJson.toString().contains("\"status\"") && sbJson.toString().contains(",")) {

            String jsonString = new String(sbJson);
            Gson gson = new Gson();
//            BaseResult result = gson.fromJson(jsonString, BaseResult.class);
//            if (result != null) {
//                InterceptorUtil.InterceptorCallback callBack = InterceptorUtil.getInstance().getCallBack();
//                switch (result.getStatus()) {
//                    case 400:
//                        if (callBack != null) {
//                            callBack.on400(result);
//                        }
//                        break;
//                    case 401:
//                        DbVal.clearLastLogin();
//                        if (callBack != null) {
//                            callBack.on401();
//                        }
//                        break;
//                    case 500:
//                        if (callBack != null) {
//                            callBack.on500(result);
//                        }
//                        break;
//                }
//            }
        }
        /*if (code == 410) {
            InterceptorUtil.InterceptorCallback callBack = InterceptorUtil.getInstance().getCallBack();
            if (callBack != null) {
                callBack.on410();
            }
        }*/
        return response;
    }
}
