package com.liongjfuan.android_distribution.http;


import com.liongjfuan.android_distribution.entity.Body;
import com.liongjfuan.android_distribution.entity.HttpResponse;
import com.liongjfuan.android_distribution.net.HttpRequest;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Lifu.Zheng
 * @date 2018.02.01
 */

public interface ShopParService {

    @GET()
    Observable<HttpResponse<Body>> getShopList(@Query("page") int page, @Query("number") int number);

    @GET()


    Observable<HttpResponse<Body>> getShopList(@Query("page") int page);
}
