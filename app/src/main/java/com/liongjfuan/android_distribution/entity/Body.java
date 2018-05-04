package com.liongjfuan.android_distribution.entity;



import com.liongjfuan.android_distribution.bean.ReturnData;

import java.util.List;

/**
 * Created by Lifu.Zheng on 2018.02.13.
 */

public class Body {

    private List<String> top_icon_urls;

    private List<ReturnData.ShopDetailRsp.CommodityInfo> commodityInfos;

    private String intro;

    public List<String> getTop_icon_urls() {
        return top_icon_urls;
    }

    public void setTop_icon_urls(List<String> top_icon_urls) {
        this.top_icon_urls = top_icon_urls;
    }

    public List<ReturnData.ShopDetailRsp.CommodityInfo> getCommodityInfos() {
        return commodityInfos;
    }

    public void setCommodityInfos(List<ReturnData.ShopDetailRsp.CommodityInfo> commodityInfos) {
        this.commodityInfos = commodityInfos;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
