package com.liongjfuan.android_distribution.engine;

import com.liongjfuan.android_distribution.base.BaseActivity;
import com.liongjfuan.android_distribution.net.RequestCallback;
import com.liongjfuan.android_distribution.net.RequestParameter;
import com.liongjfuan.android_distribution.net.URLData;
import com.liongjfuan.android_distribution.net.UrlConfigManager;

import java.util.List;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.10
 */

public class RemoteService {

    private static RemoteService service = null;

    public RemoteService() {
    }

    public static synchronized RemoteService getInstance() {
        if (RemoteService.service == null) {
            RemoteService.service = new RemoteService();
        }
        return RemoteService.service;
    }

    public void invoke(final BaseActivity activity,
                       final String apiKey,
                       final List<RequestParameter> parameters,
                       final RequestCallback callback) {

        final URLData urlData = UrlConfigManager.findURL(activity, apiKey);


    }
}




























