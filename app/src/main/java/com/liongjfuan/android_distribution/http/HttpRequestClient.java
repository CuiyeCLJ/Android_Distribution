package com.liongjfuan.android_distribution.http;

import android.content.Context;
import android.util.Log;

import com.liongjfuan.android_distribution.CustomApplication;
import com.liongjfuan.android_distribution.net.RequestParameter;
import com.liongjfuan.android_distribution.net.URLData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.cookie.Cookie;

import static com.liongjfuan.android_distribution.CustomApplication.getContext;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.17
 */

public class HttpRequestClient {

    private static final String TAG = "HttpRequestClient";

    private static AsyncHttpClient client = new AsyncHttpClient();
//    private static PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
    public static PersistentCookieStore cookieStore;
    private static List<Cookie> cookies;

    public HttpRequestClient() {
        Log.i(TAG, "HttpRequestClient: ");
    }

    public static void get(URLData urlData, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "get: ");
        cookieStore = new PersistentCookieStore(getContext());
        client.setCookieStore(cookieStore);
//        saveCookie();
        String url = getAbsoluteUrl(urlData);
        client.get(url, params, responseHandler);
        List<Cookie> cookies = cookieStore.getCookies();
        Log.i(TAG, "get: " + cookies.size());
        for (Cookie cookie : cookies) {
            Log.i(TAG, "get: Cookie: "+ cookie.getName() + " = " + cookie.getValue());
        }

    }

    public static void post(URLData urlData, RequestParameter params,
                            AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "post: 1: ");
        String url = getAbsoluteUrl(urlData, params);
        Log.i(TAG, "post: " + url);
//        client.post(url, responseHandler);
    }

    public static void post(URLData urlData, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "post: 2: ");
        String url = getAbsoluteUrl(urlData);
        Log.i(TAG, "post: 2: " + url);
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(URLData urlData) {
        Log.i(TAG, "getAbsoluteUrl: ");
        return urlData.getUrl();
    }

    private static String getAbsoluteUrl(URLData urlData, RequestParameter params) {
        Log.i(TAG, "getAbsoluteUrl: URLData urlData, RequestParams params");
        String newUrl;
        newUrl = urlData.getUrl() + "?" + params.getName() + "=" + params.getValue();
        return newUrl;
    }

    public void addCookie() {

    }

    private static void saveCookie() {
        Log.i(TAG, "saveCookie: ");
        client.setCookieStore(cookieStore);
    }

    public static List<Cookie> getCookies() {
        Log.i(TAG, "getCookies: ");
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public static void setCookies(List<Cookie> cookies) {
        Log.i(TAG, "setCookies: ");
        HttpRequestClient.cookies = cookies;
    }

    public static List<Cookie> getCookie() {
        Log.i(TAG, "getCookie: ");
        saveCookie();
        cookies = cookieStore.getCookies();
        return cookies;
    }
}
