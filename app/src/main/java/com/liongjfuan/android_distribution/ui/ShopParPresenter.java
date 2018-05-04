package com.liongjfuan.android_distribution.ui;

import android.util.Log;

import com.liongjfuan.android_distribution.entity.Body;
import com.liongjfuan.android_distribution.entity.HttpResponse;
import com.liongjfuan.android_distribution.http.ShopParService;
import com.liongjfuan.android_distribution.net.CustomObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.functions.Func1;


/**
 *
 * @author Lifu.Zheng
 * @date 2018.01.20
 */

public class ShopParPresenter implements ShopParContract.Presenter {

    private static final String TAG = "ShopParPresenter";

    public static final String URL = "http://120.78.142.93:80/shop_detail";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private ShopParService shopParService;
    private ShopParActivity shopParActivity;

    public ShopParPresenter(ShopParActivity shopParActivity) {
        Log.i(TAG, "ShopParPresenter: ");

        shopParActivity = shopParActivity;

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL)
                .build();

        shopParService = retrofit.create(ShopParService.class);
    }

    @Override
    public void start() {

    }

    protected void requestData(Subscriber<Body> subscriber, int start, int count) {
        shopParService.getShopList(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<HttpResponse<Body>>() {

                    @Override
                    public void onSuccess(HttpResponse<Body> response) {
                        Body body = response.getBody();
                        shopParActivity.showParList(body);
                    }
                });
    }

//    在访问ShopParPresenter时创建单例
    private static class SingletonHolder {
        private static final ShopParPresenter INSTANCE = new ShopParPresenter();
    }

//    获取ShopParPresenter单例
    public static ShopParPresenter getInstance() {
        Log.i(TAG, "getInstance: ");
        return SingletonHolder.INSTANCE;
    }

//    获取ShopParService实例
    public static ShopParService getShopParService() {
        Log.i(TAG, "getShopParService: ");
        return SingletonHolder.INSTANCE.shopParService;
    }

//    private class HttpResultFunc<T> implements Func1<HttpResponse<T>, T> {
//
//        @Override
//        public T call(HttpResponse<T> tHttpResponse) {
//            if (tHttpResponse.getRet() != 0) {
//                throw new ApiException(tHttpResponse.getRet());
//            }
//            return tHttpResponse.getBody();
//        }
//    }

}
