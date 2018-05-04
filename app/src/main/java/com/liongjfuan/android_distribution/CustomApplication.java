package com.liongjfuan.android_distribution;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.webkit.RenderProcessGoneDetail;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liongjfuan.android_distribution.cache.CacheManager;
import com.liongjfuan.android_distribution.net.ErrorCode;
import com.liongjfuan.android_distribution.net.HttpRequest;
import com.liongjfuan.android_distribution.net.URLData;
import com.liongjfuan.android_distribution.net.UrlConfigManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.12
 */

public class CustomApplication extends Application {

    private static final String TAG = "CustomApplication";

    private static Context context;

    CacheManager cacheManager;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
        context = getApplicationContext();
        initServerConfig();
        cacheManager = CacheManager.getInstance();
    }

    public static Context getContext() {
        Log.i(TAG, "getContext: ");
        return context;
    }

    private void initServerConfig() {

        Log.i(TAG, "initServerConfig: ");

        URLData urlData = UrlConfigManager.findURL("server_conf");

        HttpRequest httpRequest = new HttpRequest(urlData, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.i(TAG, "onResponse: " + responseData);
                parseJSONWithJSONObject(responseData);
            }
        });
        new Thread(httpRequest).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        Log.i(TAG, "parseJSONWithJSONObject: ");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String body = jsonObject.getString("body");
            String ret = jsonObject.getString("ret");
            Log.i(TAG, "parseJSONWithJSONObject: " + body);
            Log.i(TAG, "parseJSONWithJSONObject: " + ret);
            JSONObject jsonObject1 = new JSONObject(body);
            String errorCode = jsonObject1.getString("error_code");
            Log.i(TAG, "parseJSONWithJSONObject: " + errorCode);
            JSONObject jsonObject2 = new JSONObject(errorCode);

            cacheManager.putCache("10000001", jsonObject2.getString("10000001"));
            cacheManager.putCache("10000002", jsonObject2.getString("10000002"));
            cacheManager.putCache("10000003", jsonObject2.getString("10000003"));
            cacheManager.putCache("10000004", jsonObject2.getString("10000004"));
            cacheManager.putCache("10000005", jsonObject2.getString("10000005"));
            cacheManager.putCache("10000006", jsonObject2.getString("10000006"));
            cacheManager.putCache("10000007", jsonObject2.getString("10000007"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000001"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000002"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000003"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000004"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000005"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000006"));
            Log.i(TAG, "parseJSONWithJSONObject: " + cacheManager.getCache("10000007"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
