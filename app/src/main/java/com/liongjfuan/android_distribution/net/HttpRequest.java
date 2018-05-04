package com.liongjfuan.android_distribution.net;

import android.os.Handler;
import android.util.Log;

import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.10
 */

public class HttpRequest implements Runnable {

    private static final String TAG = "HttpRequest";

    public static final String REQUEST_GET = "get";
    public static final String REQUEST_POST = "post";
    private final URLData urlData;
    private final String url;
    private String newUrl;
    private final List<RequestParameter> parameterList;
    private final Handler handler;
    private Callback callback;
    private Request request;

    public HttpRequest(final URLData data, final List<RequestParameter> parameters,
                       final Callback callback) {

        urlData = data;
        url = urlData.getUrl();
        this.parameterList = parameters;
        this.callback = callback;
        handler = new Handler();
    }

    @Override
    public void run() {
        if (urlData.getNetType().equals(REQUEST_GET)) {
            Log.i(TAG, "run: " + url);
            get(url, callback, parameterList);
        } else if (urlData.getNetType().equals(REQUEST_POST)) {
            post(url, callback, parameterList);
            Log.i(TAG, "run: " + url);

        } else {
            return;
        }

    }

    private void post(String url, okhttp3.Callback callback, List<RequestParameter> parameters) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder requestBody = new FormBody.Builder();
        if (parameters != null && parameters.size() > 0) {
            for (final RequestParameter p : parameters) {
                requestBody.add(p.getName(), p.getValue());
            }
            FormBody formBody = requestBody.build();
            request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        client.newCall(request).enqueue(callback);
    }

    private void get(String url, okhttp3.Callback callback, List<RequestParameter> parameters) {

        OkHttpClient client = new OkHttpClient();
        final StringBuffer paramBuffer = new StringBuffer();
        if (parameters != null && parameters.size() > 0) {
            for (final RequestParameter p : parameters) {
                if (paramBuffer.length() == 0) {
                    paramBuffer.append(p.getName() + "=" + p.getValue());
                } else {
                    paramBuffer.append("&" + p.getName() + "=" + p.getValue());
                }

            }
            newUrl = url + "?" + paramBuffer.toString();
        } else {
            newUrl = url;
        }
        Log.i(TAG, "get: " + newUrl);
        Request request = new Request.Builder()
                .url(newUrl)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
