package com.liongjfuan.android_distribution.util;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

/**
 * Created by Lifu.Zheng on 2017.11.25.
 */

public class LocationUtil {

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }
}
