package com.example.mvvm.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.Objects;

public class NetWorkUtils {
    enum NetWorkType {
        // wifi
        NETWORK_WIFI,
        // 4G 网
        NETWORK_4G,
        // 3G 网
        NETWORK_3G,
        // 2G 网
        NETWORK_2G,
        // 未知网络
        NETWORK_UNKNOWN,
        // 没有网络
        NETWORK_NO,
        //网络断开或关闭
        NETWORK_OFFLINE,
        //以太网网络
        NETWORK_ETHERNET
    }

    public NetWorkType getNetWorkType(Context context) {
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        NetWorkType netWorkType= NetWorkType.NETWORK_NO;
        if(!Objects.requireNonNull(networkInfo).isConnected()){
            /** 网络断开或关闭  */
            netWorkType= NetWorkType.NETWORK_OFFLINE;
        }
        if(networkInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
            /** 以太网网络  */
            netWorkType= NetWorkType.NETWORK_ETHERNET;
        }else if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            /** wifi网络，当激活时，默认情况下，所有的数据流量将使用此连接  */
            netWorkType= NetWorkType.NETWORK_WIFI;
        }else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            /** 移动数据连接,不能与连接共存,如果wifi打开，则自动关闭  */
            switch (networkInfo.getSubtype()){
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case  TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    /** 2G网络  */
                    netWorkType = NetWorkType.NETWORK_2G;
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    /** 3G网络  */
                    netWorkType = NetWorkType.NETWORK_3G;
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    /** 4G网络  */
                    netWorkType = NetWorkType.NETWORK_4G;
                    break;
            }
        }
        return netWorkType;
    }
}
