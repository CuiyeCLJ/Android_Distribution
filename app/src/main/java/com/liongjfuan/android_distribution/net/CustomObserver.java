package com.liongjfuan.android_distribution.net;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.entity.HttpResponse;
import com.liongjfuan.android_distribution.ui.LoginActivity;
import com.liongjfuan.android_distribution.util.ToastUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 处理服务器响应 观察者
 * @author Lifu.Zheng
 * @date 2018.02.15
 */

public abstract class CustomObserver<T extends HttpResponse> implements Observer<T> {

    private static final String TAG = "CustomObserver";

    private Activity activity;
    private boolean isAddInStop = false;

    public CustomObserver() {
    }

    public CustomObserver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(T t) {
        Log.i(TAG, "onNext: ");
        if (t.getRet() == 0) {
            onSuccess(t);
        } else {
            onFail(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: ");
        if (e instanceof HttpException) {
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T response);

    public void onFail(T response) {
        Log.i(TAG, "onFail: ");
        String ret = String.valueOf(response.getRet());
        if ("10000001".equals(ret)) {
            ToastUtil.show("参数错误");
        }

    }

    /**
     * 请求异常
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        Log.i(TAG, "onException: ");
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtil.show(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtil.show(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtil.show(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtil.show(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtil.show(R.string.unknown_error);
                break;
        }
    }


    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

}






















