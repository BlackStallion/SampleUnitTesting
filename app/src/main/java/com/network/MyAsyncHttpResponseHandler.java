package com.network;

import android.app.ProgressDialog;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import utils.Constants;

public class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    String TAG = MyAsyncHttpResponseHandler.class.getSimpleName();
    private String requestUrl = "default";
    int flags = -1;
    boolean showLogs = false;
    ProgressDialog progressDialog = null;

    public MyAsyncHttpResponseHandler() {
        super();
    }

    public MyAsyncHttpResponseHandler(String requestName) {
        super();
        requestUrl = requestName;
    }

    public MyAsyncHttpResponseHandler(ProgressDialog pd, String requestName, int flags) {
        requestUrl = requestName;
        this.flags = flags;
        if ((flags & Constants.FLAG_SHOW_LOGS) > 0) {
            showLogs = true;
        }

        if ((flags & Constants.FLAG_SHOW_LOADER) > 0) {
            progressDialog = pd;
        } else {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        }

        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }

    }

    @Override
    public void onFinish() {
        super.onFinish();
        NetUtil.finish(requestUrl, System.currentTimeMillis());

        if (progressDialog != null && progressDialog.isShowing() && (flags & Constants.FLAG_DONT_STOP_LOADER) == 0) {
            progressDialog.dismiss();
        }

        if ((flags & Constants.FLAG_DONT_SHOW_FINISH_LOGS) == 0)
            log("finish request >> " + requestUrl);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }


    private void log(String data) {
        if (showLogs) {
            Log.d(TAG, data);
        }
    }
}
