package com.liongjfuan.android_distribution.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.11
 */

public class textUtils {

    private static final String TAG = "textUtils";

    private boolean checkInput(Context context, String account, String password) {
        Log.i(TAG, "checkInput: ");
        if (account == null || account.trim().equals("")) {
            Toast.makeText(context, "账号或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            if (!RegexUtils.checkMobile(account)) {
                Toast.makeText(context, "手机号码格式不正确", Toast.LENGTH_LONG).show();
            } else if (password == null || password.trim().equals("")) {
                Toast.makeText(context, "密码不能为空", Toast.LENGTH_LONG).show();
            } else if (!password.matches("[a-zA-Z0-9_]*")) {
                Toast.makeText(context, "密码只能含有字母、数字和下划线_", Toast.LENGTH_LONG).show();
            } else if (password.length() < 6 || password.length() > 16) {
                Toast.makeText(context, "密码长度要求在6~16位", Toast.LENGTH_LONG).show();
            } else {
                return true;
            }
        }
        return false;
    }

}
