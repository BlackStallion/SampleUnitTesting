package com.network;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import utils.Constants;

public class Networking {

    final static int DEFAULT_TIMEOUT = 40 * 100000;
    private static String TAG = "Networking";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        setUpClient();
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        setUpClient();

        try {
            StringEntity entity = new StringEntity(params.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            Log.d(TAG, "Absolute Url: " + getAbsoluteUrl(url) + " request data >> " + params.toString());
            client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static void postAbsURL(Context context, String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        try {
            Log.e(TAG,"url is"+url);
            StringEntity entity = new StringEntity(params.toString());
            client.addHeader("x-li-format", "json");
            client.post(context, url, entity, "application/json", responseHandler);

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static void getAbsURL(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        try {
            AsyncHttpClient client1 = new AsyncHttpClient();
            client1.addHeader("x-li-format", "json");
            client1.get(context, url, responseHandler);

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        return Constants.BaseUrl + relativeUrl;
    }

    private static void setUpClient() {
        client.setTimeout(DEFAULT_TIMEOUT);
    }
}
