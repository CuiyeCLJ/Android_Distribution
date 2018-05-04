package com.liongjfuan.android_distribution.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.11.19
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
