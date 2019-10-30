package com.demo.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClientInstance {
	
	private OkHttpClientInstance() {}
	
	private static OkHttpClient  instance = null;

	 public static OkHttpClient getInstance() {
	        if (instance == null) {
	            synchronized (OkHttpClientInstance.class) {
	                if (instance == null) {
	                //配置网络请求的超时时间
	                instance = new OkHttpClient().newBuilder()
	                        .connectTimeout(10, TimeUnit.SECONDS)
	                        .readTimeout(10, TimeUnit.SECONDS)
	                        .writeTimeout(10, TimeUnit.SECONDS)
	                        .build();
	            }
	        }
	    }
	    return instance;
	}

}
