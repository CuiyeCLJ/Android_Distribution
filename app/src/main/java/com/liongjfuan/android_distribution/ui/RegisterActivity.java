package com.liongjfuan.android_distribution.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.liongjfuan.android_distribution.MainActivity;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.base.BaseActivity;
import com.liongjfuan.android_distribution.bean.LoginReqOuterClass;
import com.liongjfuan.android_distribution.bean.RegisterReqOuterClass.RegisterReq;
import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.cache.CacheManager;
import com.liongjfuan.android_distribution.http.HttpRequestClient;
import com.liongjfuan.android_distribution.net.HttpRequest;
import com.liongjfuan.android_distribution.net.RequestParameter;
import com.liongjfuan.android_distribution.net.URLData;
import com.liongjfuan.android_distribution.net.UrlConfigManager;
import com.liongjfuan.android_distribution.util.RegexUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.liongjfuan.android_distribution.util.encryptUtil.decipherBase64ToByte;
import static com.liongjfuan.android_distribution.util.encryptUtil.encryptBase64;
import static com.liongjfuan.android_distribution.util.encryptUtil.encryptMD5;


/**
 * @author Administrator
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText etRegAccount;
    private EditText etRegPassword;
    private EditText etRegConfirmPassword;
    private Button btnRegister;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        Log.i(TAG, "getLayoutId: ");
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Log.i(TAG, "init: ");
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView: ");
        initTitleBarBack();
        etRegAccount = (EditText)findViewById(R.id.et_register_account);
        etRegPassword = (EditText)findViewById(R.id.et_register_password);
        etRegConfirmPassword = (EditText)findViewById(R.id.et_register_confirm_password);
        btnRegister = (Button)findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initTitleBarBack() {
        Log.i(TAG, "initTitleBarBack: ");
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //设置标题栏返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "点击了返回箭头", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                clickRegister();
                break;

            default:
                break;
        }

    }

    private void clickRegister() {
        Log.i(TAG, "clickRegister: ");
        String account = etRegAccount.getText().toString();
        String password = etRegPassword.getText().toString();
        String confirmPassword = etRegConfirmPassword.getText().toString();
        if (checkInput(account, password, confirmPassword)) {
            Toast.makeText(this, "你点击了注册按钮", Toast.LENGTH_LONG).show();
            String ciphertext = encryptMD5(encryptBase64(password));
//            register(account, ciphertext);
            registerCopy(account, password);
        }

    }

    private boolean checkInput(String account, String password, String confirmPassword) {
        if (account == null || account.trim().equals("")) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_LONG).show();
        } else {
            if (!RegexUtils.checkMobile(account)) {
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_LONG).show();
            } else if (password == null || confirmPassword == null || password.trim().equals("") || confirmPassword.trim().equals("")) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "密码不一致", Toast.LENGTH_LONG).show();
            } else if (!password.matches("[a-zA-Z0-9_]*")) {
                Toast.makeText(this, "密码只能含有字母、数字和下划线_", Toast.LENGTH_LONG).show();
            } else if (password.length() < 6 || password.length() > 16) {
                Toast.makeText(this, "密码长度要求在6~16位", Toast.LENGTH_LONG).show();
            }else {
                return true;
            }
        }
        return false;
    }

    private void register(String account, String password) {

        ArrayList<RequestParameter> parameters = new ArrayList<>();
        RequestParameter rp1 = new RequestParameter("account", account);
        RequestParameter rp2 = new RequestParameter("password", password);
        parameters.add(rp1);
        parameters.add(rp2);

        final URLData urlData = UrlConfigManager.findURL(this, "register");

        HttpRequest httpRequest = new HttpRequest(urlData, parameters, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        new Thread(httpRequest).start();
    }

    private RegisterReq getRegisterReqInstance(String account, String password) {
        Log.i(TAG, "registerReqInstance: ");

        long accountLong = Long.parseLong(account);

        RegisterReq registerReq = RegisterReq.newBuilder()
                .setTelephone(accountLong)
                .setPassword(password)
                .setRePassword(password)
                .setOs("android")
                .build();

        return registerReq;
    }

    private void registerCopy(String account, String password) {
        Log.i(TAG, "registerCopy: ");
        final URLData urlData = UrlConfigManager.findURL(this, "register");

        RegisterReq registerReq = getRegisterReqInstance(account, password);

        String encodedString = encryptBase64(registerReq.toByteArray());

        Log.i(TAG, "registerCopy: " + encodedString);

        RequestParams params = new RequestParams();
        params.put("body", encodedString);

//        RequestParameter params = new RequestParameter("body", encodeString);

        HttpRequestClient.post(urlData, params, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure: 1：" + statusCode + errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "onFailure: 2：" + statusCode + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure: 3：" + statusCode + errorResponse.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(TAG, "onSuccess: 1：" + statusCode + response.toString());
                parseJSON(response);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(TAG, "onSuccess: 2：" + statusCode + response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Log.i(TAG, "onSuccess: 3：" + statusCode + responseString);
            }
        });

    }

    private void parseJSON(JSONObject jsonObject) {
        try {
            String body = jsonObject.getString("body");
            String ret = jsonObject.getString("ret");
            if ("0".equals(ret)) {
                Log.i(TAG, "parseJSON: 注册成功");
            } else {
                String retInfo = CacheManager.getInstance().getCache(ret);
                Log.i(TAG, "parseJSON: " + retInfo);
            }
            byte[] decodedBody = decipherBase64ToByte(body);
            ReturnData.LoginRsp loginRsp = ReturnData.LoginRsp.parseFrom(decodedBody);

            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            intent.putExtra("id", 1);

            intent.putExtra("login_response", loginRsp);

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


}
