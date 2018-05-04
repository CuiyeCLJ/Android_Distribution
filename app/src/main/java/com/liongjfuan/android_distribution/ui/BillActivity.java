package com.liongjfuan.android_distribution.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.base.BaseActivity;

/**
 * @author Administrator
 */
public class BillActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, BillActivity.class);
        activity.startActivity(intent);
    }
}
