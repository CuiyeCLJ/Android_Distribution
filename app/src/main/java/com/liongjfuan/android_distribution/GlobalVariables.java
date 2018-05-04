package com.liongjfuan.android_distribution;

import android.nfc.Tag;

import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.engine.AppConstants;
import com.liongjfuan.android_distribution.util.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.21
 */

public class GlobalVariables implements Serializable, Cloneable {

    private static final String TAG = "GlobalVariables";

    private static final long serialVersionUID = -9030443403147565963L;

    private static GlobalVariables instance;

    public GlobalVariables() {
    }

    public static GlobalVariables getInstance() {
        if (instance == null) {
            Object object = Utils.restoreObject(AppConstants.CACHEDIR + TAG);
            if (object == null) {
                object = new GlobalVariables();
                Utils.saveObject(AppConstants.CACHEDIR + TAG, object);
            }
            instance = (GlobalVariables)object;
        }
        return instance;
    }

    private ReturnData.LoginRsp loginRsp;

    public ReturnData.LoginRsp getLoginRsp() {
        return loginRsp;
    }

    public void setLoginRsp(ReturnData.LoginRsp loginRsp) {
        this.loginRsp = loginRsp;
        Utils.saveObject(AppConstants.CACHEDIR + TAG, this);
    }

    /**
     * 以下方法用于序列化
     */
    public GlobalVariables readResolve() throws ObjectStreamException,
            CloneNotSupportedException{
        instance = (GlobalVariables) this.clone();
        return instance;
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
    }

    public Object Clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void reset() {
        loginRsp = null;
        Utils.saveObject(AppConstants.CACHEDIR + TAG, this);
    }
}




















