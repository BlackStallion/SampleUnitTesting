package utils;

import android.content.Context;
import android.preference.PreferenceManager;

import org.json.JSONObject;

/**
 * Created by maidulislam on 27/06/16.
 */

public class TokenSavedData {
    static String TAG = TokenSavedData.class.getSimpleName();
    public static void savePreferencesToken(String Key, String value, Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, value);
        editor.commit();

    }

    public static String loadSavedPreferenceToken(String Key, Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        String Value = sharedPreferences.getString(Key, "");
        return Value;

    }


    public static void savePreferencesForAxis(String Key, boolean value, Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key, value);
        editor.commit();

    }

    public static Boolean loadSavedPreferenceForAxis(String Key, Context context) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        Boolean Value = sharedPreferences.getBoolean(Key, false);
        return Value;

    }

    public static void deleteSavedPreferenceData(Context context) {
     /*  SharedPreferencesToken sharedPreferences = context.getSharedPreferences("MyPref", 0);
       sharedPreferences.edit().clear().apply();*/

        android.content.SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("token");
        editor.apply();

    }

    public static JSONObject SetAccessTokenToJsonObject(Context context, JSONObject params) {

        String savedToken = TokenSavedData.loadSavedPreferenceToken("token", context);
        try {
            if (!savedToken.equalsIgnoreCase("")) {
                JSONObject JOsavedToken = new JSONObject(savedToken);
                params.put("token", JOsavedToken);
                android.util.Log.d(TAG, "params used for request ==> " + params.toString());
            } else {
                //JSONObject JOsavedToken = new JSONObject(savedToken);
                params.put("token", JSONObject.NULL);
                android.util.Log.d(TAG, "params was null. params used for request ==> " + params.toString());
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return params;
    }

}
