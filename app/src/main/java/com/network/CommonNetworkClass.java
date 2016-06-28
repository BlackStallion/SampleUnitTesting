package com.network;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import iNterface.ResponseData;
import utils.Constants;
import utils.Log;
import utils.TokenSavedData;

/**
 * Created by maidulislam on 27/06/16.
 */
public class CommonNetworkClass {
    ResponseData responseData;
    private  String data;

    public CommonNetworkClass(Context activity) {
        this.responseData=(ResponseData)activity;
    }

    public void NetworkHandlerResponseData(Context context, int flags, ProgressDialog pDialog, String url_links) {
        JSONObject params = new JSONObject();
        params = TokenSavedData.SetAccessTokenToJsonObject(context, params);
        NetworkingAuth.post(context, url_links, params,
                new MyAsyncHttpResponseHandler(pDialog, url_links, flags) {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {

                            data=new String(responseBody, "UTF-8");
                            Log.d(TAG,"data >"+data);
                            responseData.responseData(statusCode,data,Constants.url_initialize_access_token);

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        data="Failure";
                        Log.d(TAG,"data >"+data);
                        responseData.responseData(statusCode,data,Constants.url_initialize_access_token);
                    }
                }
        );

        }




//    NetworkingAuth.post(getApplicationContext(), Constants.url_initilize_access_token, params,
//            new MyAsyncHttpResponseHandler(pDialog, Constants.url_initilize_access_token, flags) {
//
//        @Override
//        public void onSuccess(String s) {
//            super.onSuccess(s);
//            try {
//                JSONObject a = new JSONObject(s);
//                JSONObject b = a.getJSONObject("token");
//                //JSONObject c=a.getJSONObject("data");
//                Utility.savePreferencesToken("token", b.toString(), StartActivity.this);
//                Cache.updateCache(startActivityContext);
//
//                String isLogined = Utility.loadSavedPreferenceToken("isLogined", startActivityContext);
//                if (isLogined.equalsIgnoreCase("true")) {
//                    Constants.isFirstTimeUser = false;
//                    Intent intent = new Intent(StartActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//            } catch (Exception ee) {
//                Crashlytics.logException(ee);
//                ee.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFailure(Throwable throwable, String s) {
//            super.onFailure(throwable, s);
//            showErrorAlert();
//        }
//    }
//    );
}
