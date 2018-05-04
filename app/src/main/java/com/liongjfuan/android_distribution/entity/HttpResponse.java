package com.liongjfuan.android_distribution.entity;

/**
 * Created by Lifu.Zheng on 2018.02.11.
 */

public class HttpResponse<T> {

    private int ret;

    private T body;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
