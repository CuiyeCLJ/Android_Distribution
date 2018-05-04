package com.liongjfuan.android_distribution;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.liongjfuan.android_distribution.adapter.ViewPagerAdapter;
import com.liongjfuan.android_distribution.bean.LoginReqOuterClass;
import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.fragment.MapFragment;
import com.liongjfuan.android_distribution.fragment.UserFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "MainActivity";

    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mViewPagerAdapter;

    private List<Fragment> mFragmentList;
    private MapFragment mMapFragment;
    private UserFragment mUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViewPager();
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());

        initPermission();

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = mBottomBar.findPositionForTabWithId(tabId);
                mViewPager.setCurrentItem(position, false);
                mViewPagerAdapter.getItem(position);
            }
        });

    }

    private void initViewPager() {
        Log.i(TAG, "initViewPager: ");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(MapFragment.newInstance());
        mFragmentList.add(UserFragment.newInstance());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void initPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "因为功能需要，需要使用相关权限，请允许", 100, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "您拒绝了相关权限，可能会导致相关功能不可用", Toast.LENGTH_LONG).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
//        int id = getIntent().getIntExtra("id", 0);
//        ReturnData.LoginRsp loginRsp = (ReturnData.LoginRsp)getIntent().getSerializableExtra("login_response");
//        if (id == 1) {
//            Fragment fragment = new UserFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.view_pager, fragment);
//            transaction.commit();
//            mViewPager.setCurrentItem(1);
//            Intent intent = new Intent();
//            intent.setClass(this, UserFragment.class);
//            intent.putExtra("id", 1);
//            intent.putExtra("login_response", loginRsp);
//        }
        super.onResume();
    }
}
