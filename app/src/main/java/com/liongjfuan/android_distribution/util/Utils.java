package com.liongjfuan.android_distribution.util;

import android.os.Environment;
import android.util.Log;

import com.liongjfuan.android_distribution.bean.ReturnData;
import com.liongjfuan.android_distribution.engine.AppConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.09
 */

public class Utils {

    private static final String TAG = "Utils";

    public static void save(ReturnData.LoginRsp loginRsp) {
        Log.i(TAG, "save: ");
        File file = new File(AppConstants.CACHEDIR, "loginRsp");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            loginRsp.writeTo(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ReturnData.LoginRsp read() {
        Log.i(TAG, "read: ");
        ReturnData.LoginRsp loginRsp;
        File file = new File(AppConstants.CACHEDIR, "loginRsp");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
                outputStream.flush();
            }
            loginRsp = ReturnData.LoginRsp.parseFrom(outputStream.toByteArray());
            outputStream.close();
            inputStream.close();

            return loginRsp;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static final void saveObject(String path, Object saveObject) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File f = new File(path);
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final Object restoreObject(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        File f = new File(path);
        if (!f.exists()) {
            return null;
        }
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
            return object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

}
