package com.liongjfuan.android_distribution.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Lifu.Zheng on 2017.11.13.
 */

public class ViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, List<T> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
