package com.liongjfuan.android_distribution.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.ajguan.library.EasyRefreshLayout;
import com.kevin.loopview.AdLoopView;
import com.kevin.loopview.internal.LoopData;
import com.kevin.wraprecyclerview.WrapAdapter;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.adapter.ShopDetRecyclerAdapter;
import com.liongjfuan.android_distribution.base.BaseActivity;
import com.liongjfuan.android_distribution.entity.Body;

import java.util.ArrayList;

import static com.kevin.loopview.internal.LoopData.*;

/**
 * @author Administrator
 */
public class ShopParActivity extends BaseActivity implements ShopParContract.View {

    private EasyRefreshLayout mEasyRefreshLayout;
    private RecyclerView mRecyclerView;
    private ShopDetRecyclerAdapter mShopDetRecyclerAdapter;
    private WrapAdapter<ShopDetRecyclerAdapter> mWrapAdapter;
    private LoopData mLoopData;
    private ShopParPresenter mShopParPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_par);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_par;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initViews();

        mEasyRefreshLayout = (EasyRefreshLayout)findViewById(R.id.easylayout);
        mEasyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                //上拉加载更多
            }

            @Override
            public void onRefreshing() {
                //下拉刷新
            }
        });

        mShopParPresenter = new ShopParPresenter(this);
        setPresenter(mShopParPresenter);

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mShopDetRecyclerAdapter = new ShopDetRecyclerAdapter(this);
        mWrapAdapter = new WrapAdapter<>(mShopDetRecyclerAdapter);
        mWrapAdapter.adjustSpanSize(mRecyclerView);
        mRecyclerView.setAdapter(mWrapAdapter);
        initDatas();
        addHeaderView();
    }

    private void initDatas() {
        mLoopData = new LoopData();
        mLoopData.items = new ArrayList<>();
        LoopData.ItemData itemData = null;
        itemData.imgUrl = "https://wx4.sinaimg.cn/mw690/00654QmXgy1fl9oxtpjhhj310w1kwe81.jpg";
        LoopData.ItemData itemData1 = null;
        itemData1.imgUrl = "https://wx1.sinaimg.cn/mw690/00654QmXgy1fl6eo2sywcj31kw11x1g2.jpg";
        LoopData.ItemData itemData2 = null;
        itemData2.imgUrl = "https://wx1.sinaimg.cn/mw690/00654QmXgy1fnexzi1a0xj30zk0k045u.jpg";
        mLoopData.items.add(itemData);
        mLoopData.items.add(itemData1);
        mLoopData.items.add(itemData2);
        mShopParPresenter.requestData();
    }

    private void addHeaderView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.recycler_header, null);
        AdLoopView mAdLoopView = (AdLoopView) layout.findViewById(R.id.alv_rotation);
        mWrapAdapter.addHeaderView(layout);

        //初始化LoopView数据
        mAdLoopView.refreshData(mLoopData);
        mAdLoopView.startAutoLoop();
    }

    private void initEvent() {

    }

    @Override
    public void setPresenter(ShopParContract.Presenter presenter) {
        mShopParPresenter = (ShopParPresenter) presenter;
    }

    @Override
    public void showParList(Body body) {

    }
}
