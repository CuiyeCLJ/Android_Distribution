package com.liongjfuan.android_distribution.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.protobuf.InvalidProtocolBufferException;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.base.BaseFragment;
import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.http.HttpRequestClient;
import com.liongjfuan.android_distribution.net.HttpRequest;
import com.liongjfuan.android_distribution.net.RequestParameter;
import com.liongjfuan.android_distribution.net.URLData;
import com.liongjfuan.android_distribution.net.UrlConfigManager;
import com.liongjfuan.android_distribution.ui.NaviActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.liongjfuan.android_distribution.util.encryptUtil.decipherBase64ToByte;
import static com.liongjfuan.android_distribution.util.encryptUtil.encryptBase64;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.11.13
 */

public class MapFragment extends BaseFragment implements AMapLocationListener,
        CompoundButton.OnCheckedChangeListener, LocationSource, GeocodeSearch.OnGeocodeSearchListener,
        View.OnClickListener, EasyPermissions.PermissionCallbacks,
        AMap.InfoWindowAdapter, AMap.OnMarkerClickListener {

    private static final String TAG = "MapFragment";

    private static MapFragment mapFragment = null;

    private Button btnLocation;
    private Button btnSearch;

    private View mView;

    private AMap mAMap;
    private MapView mMapView;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationClientOption;

    private Marker locMarker;
    private Marker mMarker;
    private Marker mMarker1;
    private List<Marker> mMarkerList;
    private ArrayList<MarkerOptions> mMarkerOptionsList;

    private String keyWord = "";

    private RelativeLayout mPoiDetail;
    private TextView mPoiName;
    private TextView mPoiAddress;
    private TextView mPoiInfo;

    private EditText mSearchText;

    private LatLng mLatLng;
    private LatLng mLatLng1;

    private LatLng myLocaLatLng;
    private LatLng mDestinationLatLng;
    private GeocodeSearch geocodeSearch;

    public static MapFragment newInstance() {
//        if (mapFragment == null) {
//            synchronized (MapFragment.class) {
//                if (mapFragment == null) {
//                    mapFragment = new MapFragment();
//                }
//            }
//        }
//        mapFragment = new MapFragment();
        Bundle args = new Bundle();

        mapFragment = new MapFragment();
        mapFragment.setArguments(args);
        return mapFragment;
    }


    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_map, null);
            mMapView = mView.findViewById(R.id.mv_map);
            mMapView.onCreate(savedInstanceState);
            initMap();
        }
        initLocation();

        return mView;
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {
        Log.i(TAG, "initMap: ");
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.setOnMarkerClickListener(this);
            mAMap.setInfoWindowAdapter(this);
            addMarkersToMap();
        }
        initView();
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        Log.i(TAG, "addMarkersToMap: ");
        mMarkerList = new ArrayList<>();
        mMarkerOptionsList = new ArrayList<>();
        mLatLng = new LatLng(23.018981,113.188835);
        mLatLng1 = new LatLng(23.059658,112.949367);
        mMarkerOptionsList.add(getMarkerOptions(mLatLng));
        mMarkerOptionsList.add(getMarkerOptions(mLatLng1));
        mMarkerList = mAMap.addMarkers(mMarkerOptionsList, true);
    }

    private MarkerOptions getMarkerOptions(LatLng latLng) {
        return new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                .position(latLng);
    }

    @Override
    public View getInfoContents(Marker marker) {
        Log.i(TAG, "getInfoContents: ");
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        Log.i(TAG, "getInfoWindow: ");
        View infoWindow = LayoutInflater.from(getActivity()).inflate(R.layout.custom_info_window, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    public void render(final Marker marker, View view) {
        Log.i(TAG, "render: ");
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvDetails = view.findViewById(R.id.tv_details);
        Button btnGoOverHere = view.findViewById(R.id.btn_go_over_here);
        btnGoOverHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: btn_go_over_here");
                Toast.makeText(getActivity(), "导航到这边", Toast.LENGTH_LONG).show();
                mDestinationLatLng = marker.getPosition();
                startNavi(mDestinationLatLng);
            }
        });

        tvTitle.setText(marker.getTitle());
        tvDetails.setText(marker.getSnippet());

    }

    private void initView() {
        Log.i(TAG, "setup: ");
        btnLocation = mView.findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(this);

        btnSearch = mView.findViewById(R.id.btn_search);
        mSearchText = mView.findViewById(R.id.et_input);
        btnSearch.setOnClickListener(this);

        mPoiDetail = mView.findViewById(R.id.poi_detail);
        mPoiName = mView.findViewById(R.id.poi_name);
        mPoiAddress = mView.findViewById(R.id.poi_address);
        mPoiInfo = mView.findViewById(R.id.poi_info);

    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationClient = new AMapLocationClient(getActivity());
        mLocationClient.setLocationListener(this);

        mLocationClientOption = new AMapLocationClientOption();
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClientOption.setOnceLocation(true);

        mLocationClient.setLocationOption(mLocationClientOption);
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
        destroyLocation();
        mMapView.onDestroy();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            double longitude = aMapLocation.getLongitude();
            double latitude = aMapLocation.getLatitude();
            LatLng location = new LatLng(latitude, longitude);
            myLocaLatLng = location;
            changeLocation(location);
        } else {
            String errText = "定位失败，" + aMapLocation.getErrorCode() + "：" + aMapLocation.getErrorInfo();
            Log.e(TAG, "onLocationChanged: " + errText);
            Toast.makeText(getActivity(), errText, Toast.LENGTH_LONG).show();
        }
    }

    private void changeLocation(LatLng location) {
        if (locMarker == null) {
            locMarker = mAMap.addMarker(new MarkerOptions().position(location));
        } else {
            locMarker.setPosition(location);
        }
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        Log.i(TAG, "activate: ");
    }

    @Override
    public void deactivate() {
        Log.i(TAG, "deactivate: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                checkLocationPermission();
                break;
            case R.id.btn_search:
                Log.i(TAG, "onClick: btn_search");
                mMarkerList.clear();
                clickSearch();
                break;
//            case R.id.btn_go_over_here:
//                Log.i(TAG, "onClick: btn_go_over_here");
//                Toast.makeText(getActivity(), "导航到这边", Toast.LENGTH_LONG).show();
//                startNavi();
//                break;
            default:
                break;
        }
    }

    private void startNavi(LatLng latLng) {
        Log.i(TAG, "startNavi: ");
        Intent intent = new Intent(getActivity(), NaviActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("mylocation", myLocaLatLng);
        bundle.putParcelable("mDestination", mDestinationLatLng);
        bundle.putBoolean("gps", false);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickSearch() {
        Log.i(TAG, "clickSearch: ");
        keyWord = mSearchText.getText().toString().trim();
        if ("".equals(keyWord)) {
            Toast.makeText(getActivity(), "请输入搜索关键字", Toast.LENGTH_LONG).show();
//            return;
        } else {
            Toast.makeText(getActivity(), "你点击了搜索按钮", Toast.LENGTH_LONG).show();
//            startSearch(keyWord);
            geocodeSearch = new GeocodeSearch(getActivity());
            geocodeSearch.setOnGeocodeSearchListener(this);
            GeocodeQuery query = new GeocodeQuery(keyWord, "广州市");
            geocodeSearch.getFromLocationNameAsyn(query);
        }
        addMarkersToMap();
    }

    public void startSearch(float longitude, float latitude) {
        Log.i(TAG, "startSearch: ");
        final URLData urlData = UrlConfigManager.findURL(getActivity(), "nearby_loc");
        String lon = String.valueOf(longitude);
        String lat = String.valueOf(latitude);

        ReturnData.ShopLocationReq shopLocReq = ReturnData.ShopLocationReq.newBuilder()
                .setLongitude(lon)
                .setLatitude(lat)
                .build();
        String encShopLocReq = encryptBase64(shopLocReq.toByteArray());
        RequestParams params = new RequestParams();
        params.put("body", encShopLocReq);

        HttpRequestClient.get(urlData, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(TAG, "onSuccess: 1");
                parseJSON(response);    //解析地点搜索后返回的商店信息
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(TAG, "onSuccess: 2");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    private void parseJSON(JSONObject jsonObject) {
        Log.i(TAG, "parseJSON: ");
        try {
            String body = jsonObject.getString("body");
            String ret = jsonObject.getString("ret");
            if ("0".equals(ret)) {
                Log.i(TAG, "parseJSON: 搜索成功");
                byte[] decBody = decipherBase64ToByte(body);
                List<ReturnData.ShopLocationRsp.ShopInfo> shopInfos = new ArrayList<ReturnData.ShopLocationRsp.ShopInfo>();
                ReturnData.ShopLocationRsp shopLocRsp = ReturnData.ShopLocationRsp.parseFrom(decBody);
                if (shopLocRsp.getShopInfoCount() > 0) {
                    shopInfos = shopLocRsp.getShopInfoList();
                    addShopLocMarkersToMap(shopInfos);  //把多个商店标记到地图上
                }
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void addShopLocMarkersToMap(List<ReturnData.ShopLocationRsp.ShopInfo> shopInfos) {
        Log.i(TAG, "addShopLocMarkersToMap: ");
        mMarkerList = new ArrayList<>();
        mMarkerOptionsList = new ArrayList<>();
        for (ReturnData.ShopLocationRsp.ShopInfo shopInfo : shopInfos) {
            mLatLng = new LatLng(Double.valueOf(shopInfo.getLatitude()), Double.valueOf(shopInfo.getLongitude()));
            mMarkerOptionsList.add(getMarkerOptions(mLatLng));
        }
        mMarkerList = mAMap.addMarkers(mMarkerOptionsList, true);
    }

    private void checkLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            startLocation();
        } else {
            EasyPermissions.requestPermissions(this, "因为定位需要使用相关权限，请允许", 100, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(), "您拒绝了相关权限，可能会导致定位功能不可用" , Toast.LENGTH_LONG).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
            new AppSettingsDialog.Builder(getActivity()).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Toast.makeText(getActivity(), "你再次拒绝了相关权限的申请！", Toast.LENGTH_LONG).show();
        }
    }

    private void startLocation() {
        mLocationClient.startLocation();
        Log.i(TAG, "startLocation: ");
    }

    private void destroyLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS) {
            if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                    && geocodeResult.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);

                //获取到的经纬度
                LatLonPoint latLonPoint = address.getLatLonPoint();
                //得到纬度
                float lat = (float) latLonPoint.getLatitude();
                //得到经度
                float lon = (float) latLonPoint.getLongitude();
                startSearch(lon, lat);

            }

        }
    }
}
