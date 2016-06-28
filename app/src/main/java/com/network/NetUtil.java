package com.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NetUtil {
    static String TAG = NetUtil.class.getSimpleName();
    int id;
    static long time;
    static long totalTime = 1;
    static int count = 1;
    static List<String> requestList = new ArrayList<>();

    public static void start(String url, long startTime) {
        time = startTime;
        requestList.add(url);
        Log.d(TAG, ">Added("+url+")request Queue Size is " + requestList.size());
    }

    public static void finish(String url, long startTime) {
        long timeTaken = (startTime - time);
        Log.d(TAG, "total time taken by request is " + timeTaken);
        totalTime += timeTaken;
        count++;
        Log.d(TAG, "avg request time is > " + (totalTime / count));
        requestList.remove(url);
        Log.d(TAG, ">Removed("+url+") request Queue Size is " + requestList.size());
    }

    public static void success(String url) {

    }

    public static void failed(String url) {

    }

}
