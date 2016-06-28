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

public class NetworkingAuth {

    final static int DEFAULT_TIMEOUT = 40 * 1000;
    private static String TAG = "NetworkingAuth";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        setUpClient();
        /* if(!Constants.token.equals("")) {
             client.addHeader("Authorization", "Token " + Constants.token);
         }*/
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        setUpClient();
        try {

            StringEntity entity = new StringEntity(params.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            Log.d(TAG, "absolute URL used for request==> " + getAbsoluteUrl(url));
            client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        return Constants.BaseUrlAuth + relativeUrl;
    }

    private static void setUpClient() {
        client.setTimeout(DEFAULT_TIMEOUT);

    }
}
