package com.liongjfuan.android_distribution.ui;

import android.graphics.LightingColorFilter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.base.BaseActivity;

/**
 * @author Administrator
 */
public class NaviActivity extends BaseActivity implements AMapNaviViewListener, AMapNaviListener {

    private static final String TAG = "NaviActivity";

    private AMapNaviView mAMapNaviView;
    private AMapNavi mAMapNavi;

    private NaviLatLng mStartPoint = new NaviLatLng(23.039589, 113.392931);
    private NaviLatLng mEndPoint = new NaviLatLng(23.25676, 113.357662);

    private LatLng myLocaLatLng;
    private LatLng mDestinationLatLng;
    private NaviLatLng myLocaNaviLatLng;
    private NaviLatLng mDestinationNavLatLng;
    private boolean gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        myLocaLatLng = bundle.getParcelable("mylocation");
        mDestinationLatLng = bundle.getParcelable("mDestination");
        gps = bundle.getBoolean("gps");
        myLocaNaviLatLng = new NaviLatLng(myLocaLatLng.latitude, myLocaLatLng.longitude);
        mDestinationNavLatLng = new NaviLatLng(mDestinationLatLng.latitude, mDestinationLatLng.longitude);

        initView();
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
        //获取AMapNavi实例，并设置监听
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        mAMapNavi.addAMapNaviListener(this);
        mAMapNavi.setEmulatorNaviSpeed(60);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navi;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    private void initView() {
        Log.i(TAG, "initView: ");
        mAMapNaviView = (AMapNaviView)findViewById(R.id.navi_view);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        mAMapNaviView.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
        mAMapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
        mAMapNaviView.onDestroy();
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
    }


    @Override
    public void onNaviSetting() {
        Log.i(TAG, "onNaviSetting: ");

    }

    @Override
    public void onNaviCancel() {
        Log.i(TAG, "onNaviCancel: ");
        finish();

    }

    @Override
    public boolean onNaviBackClick() {
        Log.i(TAG, "onNaviBackClick: ");
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {
        Log.i(TAG, "onNaviMapMode: ");

    }

    @Override
    public void onNaviTurnClick() {
        Log.i(TAG, "onNaviTurnClick: ");

    }

    @Override
    public void onNextRoadClick() {
        Log.i(TAG, "onNextRoadClick: ");
    }

    @Override
    public void onScanViewButtonClick() {
        Log.i(TAG, "onScanViewButtonClick: ");
    }

    @Override
    public void onLockMap(boolean b) {
        Log.i(TAG, "onLockMap: ");
    }

    @Override
    public void onNaviViewLoaded() {
        Log.i(TAG, "onNaviViewLoaded: ");
    }

    //----------------------------------------

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {
        Log.i(TAG, "onInitNaviSuccess: ");
        mAMapNavi.calculateWalkRoute(myLocaNaviLatLng, mDestinationNavLatLng);
    }

    @Override
    public void onStartNavi(int i) {
        Log.i(TAG, "onStartNavi: ");
    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
        Log.i(TAG, "onCalculateRouteSuccess: ");
        if (gps) {
            mAMapNavi.startNavi(AMapNavi.GPSNaviMode);
        } else {
            mAMapNavi.startNavi(AMapNavi.EmulatorNaviMode);
        }
    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

}
