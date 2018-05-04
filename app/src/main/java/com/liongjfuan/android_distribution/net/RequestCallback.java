package com.liongjfuan.android_distribution.net;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.09
 */

public interface RequestCallback {

    public void onSuccess(String content);

    public void onFail(String errorMessage);


}
