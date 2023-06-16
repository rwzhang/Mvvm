package com.example.mvvm.bean;

import java.io.Serializable;

public class UrlsBean  extends BaseResponse<UrlsBean> implements Serializable {
    public String authorizations_url;
    public String code_search_url;
    public String commit_search_url;
}