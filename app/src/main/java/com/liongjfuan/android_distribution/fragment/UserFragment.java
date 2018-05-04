package com.liongjfuan.android_distribution.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.liongjfuan.android_distribution.GlobalVariables;
import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.ui.BankcardActivity;
import com.liongjfuan.android_distribution.ui.BillActivity;
import com.liongjfuan.android_distribution.ui.CommonwealActivity;
import com.liongjfuan.android_distribution.ui.FollowerActivity;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.ui.LoginActivity;
import com.liongjfuan.android_distribution.ui.RegisterActivity;
import com.liongjfuan.android_distribution.ui.RemainSumActivity;
import com.liongjfuan.android_distribution.ui.SettingActivity;
import com.liongjfuan.android_distribution.base.BaseFragment;
import com.liongjfuan.android_distribution.util.Utils;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.11.13
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "UserFragment";

    private RelativeLayout rlBill;
    private RelativeLayout rlBankcard;
    private LinearLayout llRemaining;
    private LinearLayout llCommonweal;
    private LinearLayout llFollower;
    private RelativeLayout rlSetting;

    private CircleImageView ivAvatar;
    private TextView tvNick;
    private TextView tvSignature;

    private TextView tvFixedMoney;
    private TextView tvWithdrawMoney;
    private TextView tvWelfare;
    private TextView tvWelfareRank;
    private TextView tvFanLevelFirst;
    private TextView tvFanLevelSecond;

    private View mView;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        mView = inflater.inflate(R.layout.fragment_user, container, false);
        rlBill = mView.findViewById(R.id.rl_bill);
        rlBankcard = mView.findViewById(R.id.rl_bankcard);
        llRemaining = mView.findViewById(R.id.ll_remaining_sum);
        llCommonweal = mView.findViewById(R.id.ll_commonweal);
        llFollower = mView.findViewById(R.id.ll_follower);
        rlSetting = mView.findViewById(R.id.rl_setting);

        ivAvatar = mView.findViewById(R.id.iv_avatar);
        tvNick = mView.findViewById(R.id.tv_nickname);
        tvSignature = mView.findViewById(R.id.tv_signature);

        tvFixedMoney = mView.findViewById(R.id.tv_available_cash);
        tvWithdrawMoney = mView.findViewById(R.id.tv_fixedbalance);

        tvWelfare = mView.findViewById(R.id.tv_welfare);
        tvWelfareRank = mView.findViewById(R.id.tv_welfare_rank);

        tvFanLevelFirst = mView.findViewById(R.id.tv_first_follower);
        tvFanLevelSecond = mView.findViewById(R.id.tv_second_follower);

        ivAvatar.setOnClickListener(this);

        rlBill.setOnClickListener(this);
        rlBankcard.setOnClickListener(this);
        llRemaining.setOnClickListener(this);
        llCommonweal.setOnClickListener(this);
        llFollower.setOnClickListener(this);
        rlSetting.setOnClickListener(this);

        initVariables();
        return mView;
    }

    private void loadData() {
        Log.i(TAG, "loadData: ");
        SharedPreferences pref = getActivity().getSharedPreferences("userinfo_data", MODE_PRIVATE);
        tvNick.setText(pref.getString("nick", ""));
        Log.i(TAG, "loadData: " + pref.getString("nick", ""));
        tvSignature.setText(pref.getString("signature", ""));
        Log.i(TAG, "loadData: " + pref.getString("signature", ""));
        String ivUrl = pref.getString("portrait", "");
        if (ivUrl != null && !ivUrl.equals("")) {
            Glide.with(this)
                    .load(ivUrl)
                    .into(ivAvatar);
        }

        SharedPreferences prefOth = getActivity().getSharedPreferences("otherinfo_data", MODE_PRIVATE);
        long defVal = 0;
        tvFixedMoney.setText(String.valueOf(prefOth.getLong("fixedmoney", defVal)));
        tvWithdrawMoney.setText(String.valueOf(prefOth.getLong("withdrawmoney", defVal)));
        tvWelfare.setText(String.valueOf(prefOth.getLong("welfare", defVal)));
        tvWelfareRank.setText(String.valueOf(prefOth.getLong("rank", defVal)));
        tvFanLevelFirst.setText(String.valueOf(prefOth.getLong("fanlevel1", defVal)));
        tvFanLevelSecond.setText(String.valueOf(prefOth.getLong("fanlevel2", defVal)));


    }

    protected void initVariables() {
        Log.i(TAG, "initVariables: ");
        loginRsp = GlobalVariables.getInstance().getLoginRsp();
        if (loginRsp == null) {
            Log.i(TAG, "initVariables: loginRsp = null");
            loadData();
        } else {
            Log.i(TAG, "initVariables: loginRsp != null");
            parseLoginRsp(loginRsp);
        }
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: ");
        switch (view.getId()) {
            case R.id.rl_bill:
                BillActivity.startAction(getActivity());
                break;
            case R.id.rl_bankcard:
                startAction(BankcardActivity.class);
                break;
            case R.id.ll_remaining_sum:
                startAction(RemainSumActivity.class);
                break;
            case R.id.ll_commonweal:
                startAction(CommonwealActivity.class);
                break;
            case R.id.ll_follower:
                startAction(FollowerActivity.class);
                break;
            case R.id.rl_setting:
                startAction(SettingActivity.class);
                break;

            case R.id.iv_avatar:
                startAction(LoginActivity.class);
                break;
            default:
                break;

        }
    }

    private void startAction(Class clav) {
        Intent intent = new Intent(getActivity(), clav);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    ReturnData.LoginRsp loginRsp;

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
//        loginRsp = Utils.read();
//        parseLoginRsp(loginRsp);
    }

    private void parseLoginRsp(ReturnData.LoginRsp loginRsp) {
        Log.i(TAG, "parseLoginRsp: ");

        if (loginRsp != null) {
            ReturnData.UserInfo userInfo = loginRsp.getUserInfo();
            Log.i(TAG, "parseLoginRsp: 电话号码: " + userInfo.getUserId());

            tvNick.setText(userInfo.getNick());
            Log.i(TAG, "parseLoginRsp: 昵称: " + userInfo.getNick());
            tvSignature.setText(userInfo.getSignature());
            Log.i(TAG, "parseLoginRsp: 签名: " + userInfo.getSignature());
            String ivUrl = userInfo.getPortrait();
            Log.i(TAG, "parseLoginRsp: 头像的地址: " + ivUrl);
            if (ivUrl != null && !ivUrl.equals("")) {
                Glide.with(this)
                        .load(ivUrl)
                        .into(ivAvatar);

//                GlideApp.with(this)
//                        .load(ivUrl)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(ivAvatar);
            }
        } else {
            return;
        }

    }


}
