package com.liongjfuan.android_distribution.net;

import android.app.Activity;
import android.content.res.XmlResourceParser;

import com.liongjfuan.android_distribution.CustomApplication;
import com.liongjfuan.android_distribution.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.10
 */

public class UrlConfigManager {
    private static ArrayList<URLData> urlList;

    public static URLData findURL(final Activity activity, final String findKey) {
        //如果urlList还没有数据（可能是第一次），或者被回收了，那么重新加载xml
        if (urlList == null || urlList.isEmpty()) {
            fetchUrlDataFromXml(activity);
        }
        for (URLData urlData : urlList) {
            if (findKey.equals(urlData.getKey())) {
                return urlData;
            }
        }
        return null;
    }

    public static URLData findURL(final String findKey) {
        //如果urlList还没有数据（可能是第一次），或者被回收了，那么重新加载xml
        if (urlList == null || urlList.isEmpty()) {
            fetchUrlDataFromXml();
        }
        for (URLData urlData : urlList) {
            if (findKey.equals(urlData.getKey())) {
                return urlData;
            }
        }
        return null;
    }

    private static void fetchUrlDataFromXml(final Activity activity) {
        urlList = new ArrayList<>();

        final XmlResourceParser xmlParser = activity.getApplication().getResources().getXml(R.xml.url);

        int eventCode;
        try {
            eventCode = xmlParser.getEventType();
            while (eventCode != XmlPullParser.END_DOCUMENT) {
                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("Node".equals(xmlParser.getName())) {
                            final String key = xmlParser.getAttributeValue(null, "Key");
                            final URLData urlData = new URLData();
                            urlData.setKey(key);
                            urlData.setExpires(Long.parseLong(xmlParser.getAttributeValue(null, "Expires")));
                            urlData.setNetType(xmlParser.getAttributeValue(null, "NetType"));
                            urlData.setUrl(xmlParser.getAttributeValue(null, "Url"));
                            urlList.add(urlData);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventCode = xmlParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            xmlParser.close();
        }
    }

    private static void fetchUrlDataFromXml() {
        urlList = new ArrayList<>();

        final XmlResourceParser xmlParser = CustomApplication.getContext().getResources().getXml(R.xml.url);

        int eventCode;
        try {
            eventCode = xmlParser.getEventType();
            while (eventCode != XmlPullParser.END_DOCUMENT) {
                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("Node".equals(xmlParser.getName())) {
                            final String key = xmlParser.getAttributeValue(null, "Key");
                            final URLData urlData = new URLData();
                            urlData.setKey(key);
                            urlData.setExpires(Long.parseLong(xmlParser.getAttributeValue(null, "Expires")));
                            urlData.setNetType(xmlParser.getAttributeValue(null, "NetType"));
                            urlData.setUrl(xmlParser.getAttributeValue(null, "Url"));
                            urlList.add(urlData);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventCode = xmlParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            xmlParser.close();
        }
    }

}
