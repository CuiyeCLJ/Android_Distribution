package com.liongjfuan.android_distribution.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.protobuf.InvalidProtocolBufferException;
import com.liongjfuan.android_distribution.GlobalVariables;
import com.liongjfuan.android_distribution.MainActivity;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.base.BaseActivity;
import com.liongjfuan.android_distribution.bean.LoginReqOuterClass;
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
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.liongjfuan.android_distribution.util.encryptUtil.decipherBase64ToByte;
import static com.liongjfuan.android_distribution.util.encryptUtil.encryptBase64;
import static com.liongjfuan.android_distribution.util.encryptUtil.encryptMD5;

/**
 * @author Administrator
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private CircleImageView profileImage;
    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnBack;
    private TextView tvToRegister;

    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");

    }

    @Override
    protected int getLayoutId() {
        Log.i(TAG, "getLayoutId: ");
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Log.i(TAG, "init: ");
        pref = getSharedPreferences("userinfo_data", MODE_PRIVATE);
        long defLong = 0;
        userId = String.valueOf(pref.getLong("userid", defLong));
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView: ");
        profileImage = (CircleImageView)findViewById(R.id.profile_image);
        etAccount = (EditText)findViewById(R.id.et_account);
        etPassword = (EditText)findViewById(R.id.et_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnBack = (Button)findViewById(R.id.btn_back);
        tvToRegister = (TextView)findViewById(R.id.tv_to_register);
        if (userId != null && !userId.equals("") && !userId.equals("0")) {
            etAccount.setText(userId);
        }

        initListener();
    }

    private void initListener() {
        Log.i(TAG, "initListener: ");
        btnLogin.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        tvToRegister.setOnClickListener(this);
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (userId != null && !userId.equals("")) {
                    String input = editable.toString();
                    if (userId.contains(input)) {
                        String url = pref.getString("portrait", "");
                        if (url != null && !url.equals("")) {
                            Glide.with(LoginActivity.this)
                                    .load(url)

                                    .into(profileImage);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                clickLogin();
                break;

            case R.id.btn_back:
                finish();
                break;

            case R.id.tv_to_register:
                enterRegister();
                break;

            default:
                break;
        }
    }

    private void clickLogin() {
        Log.i(TAG, "clickLogin: ");
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        if (checkInput(account, password)) {
            Toast.makeText(this, "你点击了登录按钮", Toast.LENGTH_LONG).show();
            String pwCiphertext = encryptMD5(encryptBase64(password));
//            login(account, pwCiphertext);
            loginCopy(account, password);
        }
    }

    private void login(String account, String password) {

        ArrayList<RequestParameter> parameters = new ArrayList<>();
        RequestParameter rp1 = new RequestParameter("account", account);
        RequestParameter rp2 = new RequestParameter("password", password);
        parameters.add(rp1);
        parameters.add(rp2);

        final URLData urlData = UrlConfigManager.findURL(this, "login");

        Log.i(TAG, "login: " + urlData.getUrl());

        HttpRequest httpRequest = new HttpRequest(urlData, parameters, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.i(TAG, "onResponse: " + responseData);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        new Thread(httpRequest).start();

    }

    private LoginReqOuterClass.LoginReq getLoginReqInstance(String account, String password) {
        Log.i(TAG, "getLoginReqInstance: ");

        long accountLong = Long.parseLong(account);

        LoginReqOuterClass.LoginReq loginReq = LoginReqOuterClass.LoginReq.newBuilder()
                .setTelephone(accountLong)
                .setPassword(password)
                .setOs("android")
                .build();
        return loginReq;
    }

    private void loginCopy(String account, String password) {
        Log.i(TAG, "loginCopy: ");
        final URLData urlData = UrlConfigManager.findURL(this, "login");

        LoginReqOuterClass.LoginReq loginReq = getLoginReqInstance(account, password);

        String encodedString = encryptBase64(loginReq.toByteArray());

        Log.i(TAG, "loginCopy: " + encodedString);

        RequestParams params = new RequestParams();
        params.put("body", encodedString);

        HttpRequestClient.get(urlData, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(TAG, "onSuccess: " + statusCode + response.toString());
                List<Cookie> cookies = HttpRequestClient.cookieStore.getCookies();
                Log.i(TAG, "onSuccess: " + cookies.size());
                for (Cookie cookie : cookies) {
                    Log.i(TAG, "onSuccess: Cookie: " + cookie.getName() + " = " + cookie.getValue());
                }

                HttpRequestClient.setCookies(HttpRequestClient.getCookie());
                Log.i(TAG, "onSuccess: 3: " + getCookieText());

                parseJSON(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure: 1：" + statusCode + errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "onFailure: 2：" + statusCode + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure: 3：" + statusCode + errorResponse.toString());
            }
        });
    }

    private String getCookieText() {
        Log.i(TAG, "getCookieText: ");
//        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
//        List<Cookie> cookies = cookieStore.getCookies();
        List<Cookie> cookies = HttpRequestClient.getCookies();
        Log.i(TAG, "getCookieText: cookies.size() = " + cookies.size());
        for (Cookie cookie : cookies) {
            Log.i(TAG, "getCookieText: " + cookie.getName() + " = " + cookie.getValue());
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (!cookieName.isEmpty() && !cookieValue.isEmpty()) {
                stringBuffer.append(cookieName + " = ");
                stringBuffer.append(cookieValue + ";");
            }
        }
        Log.i(TAG, "getCookieText: " + stringBuffer.toString());
        return stringBuffer.toString();

    }

    private void parseJSON(JSONObject jsonObject) {
        Log.i(TAG, "parseJSON: ");
        try {
            String body = jsonObject.getString("body");
            String ret = jsonObject.getString("ret");
            if ("0".equals(ret)) {
                Log.i(TAG, "parseJSON: 登陆成功");
            } else {
                String retInfo = CacheManager.getInstance().getCache(ret);
                Log.i(TAG, "parseJSON: " + retInfo);
            }

            byte[] decodedBody = decipherBase64ToByte(body);
            ReturnData.LoginRsp loginRsp = ReturnData.LoginRsp.parseFrom(decodedBody);

            ReturnData.UserInfo userInfo = loginRsp.getUserInfo();
            if (userInfo == null) {
                Log.i(TAG, "parseJSON: userInfo = null");
            } else {
                Log.i(TAG, "parseJSON: userInfo != null");
            }
            Log.i(TAG, "parseJSON: 登录返回的用户: " + userInfo.getUserId());


            sharedPrefSaveOther(loginRsp);
            //使用SharedPreferences保存UserInfo
            sharedPrefSaveUser(userInfo);

//            Utils.save(loginRsp);
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
//            intent.putExtra("login_response", loginRsp);

            GlobalVariables.getInstance().setLoginRsp(loginRsp);

            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

    private void sharedPrefSaveUser(ReturnData.UserInfo userInfo) {
        Log.i(TAG, "sharedPrefSaveUser: ");
        SharedPreferences.Editor editor = getSharedPreferences("userinfo_data", MODE_PRIVATE).edit();
        editor.clear();
        editor.putInt("status", userInfo.getStatus());
        editor.putLong("userid", userInfo.getUserId());
        editor.putString("nick", userInfo.getNick());
        editor.putString("portrait", userInfo.getPortrait());
        editor.putString("signature", userInfo.getSignature());
        editor.apply();
    }

    private void sharedPrefSaveOther(ReturnData.LoginRsp loginRsp) {
        Log.i(TAG, "sharedPrefSaveOther: ");
        ReturnData.BalanceInfo balanceInfo = loginRsp.getBalanceInfo();
        ReturnData.WelfareInfo welfareInfo = loginRsp.getWelfareInfo();
        ReturnData.FanInfo fanInfo = loginRsp.getFanInfo();
        SharedPreferences.Editor editor = getSharedPreferences("otherinfo_data", MODE_PRIVATE).edit();
        editor.clear();
        editor.putLong("fixedmoney", balanceInfo.getFixedMoney());
        Log.i(TAG, "sharedPrefSaveOther: fixedmoney: " + balanceInfo.getFixedMoney());
        editor.putLong("withdrawmoney", balanceInfo.getWithdrawMoney());
        Log.i(TAG, "sharedPrefSaveOther: withdraw_money: " + balanceInfo.getWithdrawMoney());
        editor.putLong("welfare", welfareInfo.getWelfare());
        Log.i(TAG, "sharedPrefSaveOther: welfare: " + welfareInfo.getWelfare());
        editor.putLong("rank", welfareInfo.getRank());
        Log.i(TAG, "sharedPrefSaveOther: rank: " + welfareInfo.getRank());
        editor.putLong("fanlevel1", fanInfo.getFanLevel1());
        Log.i(TAG, "sharedPrefSaveOther: fanlevel1: " + fanInfo.getFanLevel1());
        editor.putLong("fanlevel2", fanInfo.getFanLevel2());
        Log.i(TAG, "sharedPrefSaveOther: fanlevel2: " + fanInfo.getFanLevel2());
        editor.apply();

    }

    private boolean checkInput(String account, String password) {
        Log.i(TAG, "checkInput: ");
        if (account == null || account.trim().equals("")) {
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            if (!RegexUtils.checkMobile(account)) {
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_LONG).show();
            } else if (password == null || password.trim().equals("")) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            } else if (!password.matches("[a-zA-Z0-9_]*")) {
                Toast.makeText(this, "密码只能含有字母、数字和下划线_", Toast.LENGTH_LONG).show();
            } else if (password.length() < 6 || password.length() > 16) {
                Toast.makeText(this, "密码长度要求在6~16位", Toast.LENGTH_LONG).show();
            } else {
                return true;
            }
        }
        return false;
    }

    private void enterRegister() {
        Log.i(TAG, "enterRegister: ");
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
