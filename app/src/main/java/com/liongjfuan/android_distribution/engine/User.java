package com.liongjfuan.android_distribution.engine;

import com.liongjfuan.android_distribution.util.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.09
 */

public class User implements Serializable, Cloneable {

    private static final String TAG = "User";

    private static final long serialVersionUID = 8879597827859520334L;

    private static User instance;

    private int status;
    private int userId;
    private String nick;
    private int sex;
    private String portrait;
    private String signature;

    public User() {
    }

    public static User getInstance() {
        if (instance == null) {
            Object object = Utils.restoreObject(AppConstants.CACHEDIR + TAG);
            if (object == null) {
                object = new User();
                Utils.saveObject(AppConstants.CACHEDIR + TAG, object);
            }
            instance = (User)object;
        }
        return instance;
    }

    public void reset() {
        status = 2;
        userId = 0;
        nick = null;
        sex = 1;
        portrait = null;
        signature = null;
    }

    public void save() {
        Utils.saveObject(AppConstants.CACHEDIR + TAG, this);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User readResolve() throws ObjectStreamException, CloneNotSupportedException {
        instance = (User)this.clone();
        return instance;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    public Object Clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
