/**
 * 
 */
package com.liongjfuan.android_distribution.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.liongjfuan.android_distribution.CustomApplication;

/**
 * @author Administrator
 */
public class ToastUtil {

	private static Context context = CustomApplication.getContext();

	public static void show(String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(int info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	


}
