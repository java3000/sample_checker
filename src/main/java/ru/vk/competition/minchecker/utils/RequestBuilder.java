package ru.vk.competition.minchecker.utils;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {

    public static Request getRequestBuilder(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }
    
    public static Request postRequestBuilder(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    public static Request putRequestBuilder(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
    }

    public static Request deleteRequestBuilder(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();
    }
}
