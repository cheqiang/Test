package com.demo.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;

import okhttp3.*;
import okhttp3.Request.Builder;
import org.apache.log4j.Logger;

/**
 * @desc okhttp3发送Post/Get请求
 * @author cheqiang
 * @date 2019年10月26日
 * @version 1.0
 */
public class OkHttpClientUtil {

	private static Logger log = Logger.getLogger(OkHttpClientUtil.class);

	//由于okhttp header 中的 value 不支持 null, \n 和 中文这样的特殊字符,所以这里
	//会首先替换 \n ,然后使用 okhttp 的校验方式,校验不通过的话,就返回 encode 后的字符串
	private static String getValueEncoded(String value) throws UnsupportedEncodingException {
		if (value == null) {
			return "null";
		}
		String newValue = value.replace("\n", "");
		for (int i = 0, length = newValue.length(); i < length; i++) {
			char c = newValue.charAt(i);
			if (c <= '\u001f' || c >= '\u007f') {
				return URLEncoder.encode(newValue, "UTF-8");
			}
		}
		return newValue;
	}

	/**
	　* <p>Title: post<／p>
	　* <p>Description: 同步发送Post请求<／p>
	　* @param url
	　* @param map
	　* @param headers
	　* @param mediaType
	　* @return
	　* @throws IOException
	　*/
	public static Response post( String url, Map<Object, Object> map, Map<String,String> headers, String mediaType) throws IOException {
		Gson mGson = new Gson();
        String params = mGson.toJson(map);
        //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        MediaType mediaTypeObj = MediaType.parse(mediaType);
        RequestBody body = RequestBody.create(mediaTypeObj, params);
        Builder build = new Builder().url(url);
        if(headers != null && !headers.isEmpty()) {
        	headers.forEach((key,value) -> {
        		build.addHeader(key, value);
        	});
        }
        Request request = build.post(body).build();
        Response response = OkHttpClientInstance.getInstance().newCall(request).execute();
		return response;

    }

    public static Response formPost(String url, Map<String, String> paramsMap, Map<String,String> headers, String mediaType) throws IOException{
		Builder build = new Builder().url(url);
		StringBuilder tempParams = new StringBuilder();
		int pos = 0;
		for (String key : paramsMap.keySet()) {
			if (pos > 0) {
				tempParams.append("&");
			}
			//对参数进行URLEncoder
			tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
			pos++;
		}
		MediaType mediaTypeObj = MediaType.parse(mediaType);
		RequestBody body = RequestBody.create(mediaTypeObj, tempParams.toString());
		if(headers != null && !headers.isEmpty()) {
			headers.forEach((key,value) -> {
				build.addHeader(key, value);
			});
		}
		Request request = build.post(body).build();
		Response response = OkHttpClientInstance.getInstance().newCall(request).execute();
		return response;
	}

	public static Response postFileRequest(String url, File file,String fileName, Map<String, String> maps,Map<String,String> headers) throws IOException {
		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		if (maps != null) {
			for (String key : maps.keySet()) {
				builder.addFormDataPart(key, maps.get(key));
			}
		}

		builder.addFormDataPart("file", fileName,
				RequestBody.create(MediaType.parse("multipart/form-data"),file))
				.build();
		RequestBody body = builder.build();

		Builder requestBuild = new Builder().url(url);
		if(headers!=null && !headers.isEmpty()){
			headers.forEach((key,val)->{
				try {
					requestBuild.addHeader(key,getValueEncoded(val));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			});
		}
		Request request = requestBuild.post(body).build();

		Response response = OkHttpClientInstance.getInstance().newCall(request).execute();
		return response;
	}
	
	
	public static Response postByFormData( String url, Map<Object, Object> map, Map<String,String> headers) throws IOException {
		Gson mGson = new Gson();
        String params = mGson.toJson(map);
        

        FormBody.Builder builder = new FormBody.Builder();
        if(map != null && !map.isEmpty()) {
        	map.forEach((key,value) -> {
        		builder.addEncoded(key.toString(), value.toString());
        	});
        }
        FormBody formBody = builder.build();
        Builder build = new Builder().url(url);
        if(headers != null && !headers.isEmpty()) {
        	headers.forEach((key,value) -> {
        		build.addHeader(key, value);
        	});
        	
        }
        Request request = build.post(formBody).build();
        Response response = OkHttpClientInstance.getInstance().newCall(request).execute();
		return response;
    }
	
	
	/**
	　* <p>Title: get<／p>
	　* <p>Description: 同步发送Get请求<／p>
	　* @param url
	　* @param headers
	　* @return
	　* @throws IOException
	　*/
	public static Response get(String url, Map<String,String> headers) throws IOException {
        Builder build = new Builder().url(url);
        if(headers != null && !headers.isEmpty()) {
        	headers.forEach((key,value) -> {
				try {
					build.addHeader(key, getValueEncoded(value));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			});
        	
        }

        Request request = build.build();
        Response response = OkHttpClientInstance.getInstance().newCall(request).execute();
		return response;

    }
}
