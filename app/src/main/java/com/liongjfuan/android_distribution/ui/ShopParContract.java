package com.liongjfuan.android_distribution.ui;

import com.liongjfuan.android_distribution.base.BasePresenter;
import com.liongjfuan.android_distribution.base.BaseView;
import com.liongjfuan.android_distribution.entity.Body;

/**
 *
 * @author Lifu.Zheng
 * @date 2018.01.20
 */

public interface ShopParContract {

    interface View extends BaseView<Presenter> {

        void showParList(Body body);

    }

    interface Presenter extends BasePresenter {

    }
}
